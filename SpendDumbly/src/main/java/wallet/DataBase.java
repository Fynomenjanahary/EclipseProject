package wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	public DataBase() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "20050412";
    return DriverManager.getConnection(url, username, password);
  }
}
