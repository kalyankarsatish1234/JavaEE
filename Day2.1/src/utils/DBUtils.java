package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	// How to ensure SINGLETON instance of DB fixed connectin, shared across
	// multiple DAOs?
	// It is not At all scallable solution, in case of multiple clints accesing the
	// app, deployed in remote db
	// so will be ultimetly replaced by : connection pool.

	private static Connection cn;

	// add a static method to return FIXED DB connection: will be called exactly
	// ONCE
	public static void openConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&allowPublicKeyRetrieval=true";
		// get cn from DriverManager : class

		cn = DriverManager.getConnection(url, "root", "root");

	}

	// close connection : once
	public static void closeConnection() throws SQLException {
		if (cn != null)

			cn.close();
	}
	
	//add a getter for getting FIXED DB connection
	public static Connection getCn() {
		return cn;
	}

}
