package com.anemoi.vendor.databaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class VendorDatabaseUtill {
	private static final Logger logger = LoggerFactory.getLogger(VendorDatabaseUtill.class);
	
	public static Connection getConnection() throws ClassNotFoundException {

		Connection connection = null;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost";
//		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String user = "Anemoi";
		String password = "Anemoi@123";

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {

			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}

		if (connection != null) {
			return connection;
		}
		return null;

	}
	public static void close(Statement statment, Connection connection) {
		if (statment != null) {
			try {
				statment.close();
			} catch (SQLException e) {
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}

	}

	
	public static void close(ResultSet resultset, PreparedStatement statment, Connection connectionclose) {
		if (resultset != null) {
			try {
				resultset.close();
			} catch (SQLException e) {
				logger.error("unable to close the result set  " + e.getMessage());
			}
		}
		if (statment != null) {
			try {
				statment.close();
			} catch (SQLException e) {
				logger.error("unable to close the statment " + e.getMessage());
			}
		}
		if (connectionclose != null) {
			try {
				connectionclose.close();
			} catch (SQLException e) {
				logger.error("unable to close the connection " + e.getMessage());
			}
		}
	}

}
