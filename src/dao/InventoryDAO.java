package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import util.DBConnection;
 
public class InventoryDAO {
    public int checkStock(int storeId, int productId) {
        String query = "SELECT quantity FROM production.stocks WHERE store_id = ? AND product_id = ?";
        int stockQuantity = 0;
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
 
            pstmt.setInt(1, storeId);
            pstmt.setInt(2, productId);
 
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    stockQuantity = rs.getInt("quantity");
                } else {
                    System.out.println("Product not found in store inventory.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking stock: " + e.getMessage());
        }
 
        return stockQuantity;
    }
 
    public void alertIfInsufficientStock(int storeId, int productId, int requestedQuantity) {
        int availableStock = checkStock(storeId, productId);
 
        if (availableStock >= requestedQuantity) {
            System.out.println("Stock available: " + availableStock + " units.");
        } else {
            System.out.println("Insufficient stock! Available: " + availableStock + ", Requested: " + requestedQuantity);
        }
    }
}
