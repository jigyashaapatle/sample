package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
 
import model.Customer;
import util.DBConnection;
 
public class CustomerDAO {
private boolean isValidEmail(String email) {
    String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    return Pattern.matches(emailRegex, email);
}
 
/**
* Adds a new customer to the database.
*/
public boolean addCustomer(Customer customer) {
    if (!isValidEmail(customer.getEmail())) {
        System.out.println("Invalid email format: " + customer.getEmail());
        return false;
    }
 
    String sql = "INSERT INTO sales.customers (first_name, last_name, phone, email, street, city, state, zip_code) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
 
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
 
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getPhone());
        ps.setString(4, customer.getEmail());
        ps.setString(5, customer.getStreet());
        ps.setString(6, customer.getCity());
        ps.setString(7, customer.getState());
        ps.setString(8, customer.getZipCode());
 
        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Customer added successfully!");
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
 
/**
* Searches for customers by name, email, or city.
*/
public List<Customer> searchCustomers(String keyword) {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * FROM sales.customers WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR city LIKE ?";
 
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
 
        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        ps.setString(4, searchPattern);
 
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zip_code")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return customers;
}
}
