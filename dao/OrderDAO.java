package dao;
import model.Order;
import model.OrderItem;
import util.DBConnection;
 
 
import java.sql.*;
import java.util.List;
public class OrderDAO {
    /**
     * Places a new order with products.
     */
    public boolean placeOrder(Order order, List<OrderItem> orderItems) {
        Connection con = null;
        boolean isSuccess = false;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);  // Begin Transaction
            // Step 1: Insert into sales.orders
            String orderSql = "INSERT INTO sales.orders (customer_id, order_status, order_date, required_date, store_id, staff_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement orderStmt = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getCustomerId());
                orderStmt.setInt(2, order.getOrderStatus());
                orderStmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
                orderStmt.setDate(4, new java.sql.Date(order.getRequiredDate().getTime()));
                orderStmt.setInt(5, order.getStoreId());
                orderStmt.setInt(6, order.getStaffId());
                int affectedRows = orderStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating order failed, no rows affected.");
                }
                // Retrieve generated order_id
                try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);
                        // Step 2: Insert order items
                        String itemSql = "INSERT INTO sales.order_items (order_id, item_id, product_id, quantity, list_price, discount) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement itemStmt = con.prepareStatement(itemSql)) {
                            int itemId = 1;  // Track item numbers
                            for (OrderItem item : orderItems) {
                                itemStmt.setInt(1, orderId);
                                itemStmt.setInt(2, itemId++);
                                itemStmt.setInt(3, item.getProductId());
                                itemStmt.setInt(4, item.getQuantity());
                                itemStmt.setDouble(5, item.getListPrice());
                                itemStmt.setDouble(6, item.getDiscount());
                                itemStmt.addBatch();
                            }
                            itemStmt.executeBatch();
                        }
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }
            con.commit(); // Commit Transaction
            isSuccess = true;
            System.out.println("Order placed successfully!");
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback if error occurs
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
        return isSuccess;
    }
}
