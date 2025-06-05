package util;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
	private static final String url = "jdbc:mysql://localhost:3306/bikestores";
	private static final String user = "root";
	private static final String password = "Password@12";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
 
}
