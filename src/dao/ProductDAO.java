package dao;
import model.Product;
import util.DBConnection;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
 
//Display all products with brand, category, model year, and price.
public class ProductDAO{
public  List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT p.product_id, p.product_name, b.brand_name AS brand, " +
                 "c.category_name AS category, p.model_year, p.list_price AS price " +
                 "FROM production.products p " +
                 "JOIN production.brands b ON p.brand_id = b.brand_id " +
                 "JOIN production.categories c ON p.category_id = c.category_id";
 
    try (Connection con =DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
 
        while (rs.next()) {
            products.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("brand"),
                rs.getString("category"),
                rs.getInt("model_year"),
                rs.getDouble("price")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return products;
}
 
/**
* Fetches products filtered by brand name.     * @param brand The brand name to filter by.
* @return List of filtered products.
*/
 
public List<Product> getFilteredProducts(String brand, String category, Double minPrice, Double maxPrice) {
	    List<Product> products = new ArrayList<>();
	    StringBuilder sql = new StringBuilder(
	        "SELECT p.product_id, p.product_name, b.brand_name AS brand, " +
	        "c.category_name AS category, p.model_year, p.list_price AS price " +
	        "FROM production.products p " +
	        "JOIN production.brands b ON p.brand_id = b.brand_id " +
	        "JOIN production.categories c ON p.category_id = c.category_id " +
	        "WHERE 1=1"
	    );
 
	    if (brand != null && !brand.isEmpty()) sql.append(" AND b.brand_name = ?");
	    if (category != null && !category.isEmpty()) sql.append(" AND c.category_name = ?");
	    if (minPrice != null) sql.append(" AND p.list_price >= ?");
	    if (maxPrice != null) sql.append(" AND p.list_price <= ?");
 
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql.toString())) {
 
	        int index = 1;
	        if (brand != null && !brand.isEmpty()) ps.setString(index++, brand);
	        if (category != null && !category.isEmpty()) ps.setString(index++, category);
	        if (minPrice != null) ps.setDouble(index++, minPrice);
	        if (maxPrice != null) ps.setDouble(index++, maxPrice);
 
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                products.add(new Product(
	                    rs.getInt("product_id"),
	                    rs.getString("product_name"),
	                    rs.getString("brand"),
	                    rs.getString("category"),
	                    rs.getInt("model_year"),
	                    rs.getDouble("price")
	                ));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return products;
	}
}
