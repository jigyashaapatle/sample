package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
 
import util.DBConnection;
 
public class SalesReportDAO {
 
    public Map<String, Double> getTotalSalesPerStore() {
        Map<String, Double> salesPerStore = new HashMap<>();
        String query = "SELECT stores.store_name, SUM(order_items.quantity * order_items.list_price) AS total_sales " +
                       "FROM sales.orders " +
                       "JOIN sales.stores ON orders.store_id = stores.store_id " +
                       "JOIN sales.order_items ON orders.order_id = order_items.order_id " +
                       "GROUP BY stores.store_name";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
 
            while (rs.next()) {
                salesPerStore.put(rs.getString("store_name"), rs.getDouble("total_sales"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching sales per store: " + e.getMessage());
        }
 
        return salesPerStore;
    }
 
    public List<String> getTopSellingProducts() {
        List<Map.Entry<String, Integer>> sortedProducts;
        Map<String, Integer> productSales = new HashMap<>();
        String query = "SELECT products.product_name, SUM(order_items.quantity) AS total_quantity " +
                       "FROM sales.order_items " +
                       "JOIN production.products ON order_items.product_id = products.product_id " +
                       "GROUP BY products.product_name";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
 
            while (rs.next()) {
                productSales.put(rs.getString("product_name"), rs.getInt("total_quantity"));
            }
 
            sortedProducts = productSales.entrySet()
                                         .stream()
                                         .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                         .limit(5)
                                         .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println("Error fetching top selling products: " + e.getMessage());
            return Collections.emptyList();
        }
 
        return sortedProducts.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }
 
    public Map<String, Double> getMonthlySalesTrends() {
        Map<String, Double> monthlySales = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
 
        String query = "SELECT FORMAT(order_date, 'yyyy-MM') AS order_month, SUM(order_items.quantity * order_items.list_price) AS total_sales " +
                       "FROM sales.orders " +
                       "JOIN sales.order_items ON orders.order_id = order_items.order_id " +
                       "GROUP BY FORMAT(order_date, 'yyyy-MM')";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
 
            while (rs.next()) {
                monthlySales.put(rs.getString("order_month"), rs.getDouble("total_sales"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching monthly sales trends: " + e.getMessage());
        }
 
        return monthlySales;
    }
}
 
