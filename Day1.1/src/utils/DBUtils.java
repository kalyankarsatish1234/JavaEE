package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	// add a static method to return FIXED DB connection
	public static Connection openConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&allowPublicKeyRetrieval=true";
		// get cn from DriverManager : class
		return DriverManager.getConnection(url, "root", "root");

	}

}
