package com.revature.utilities;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DOAUtilities {

	private static final String CONNECTION_USERNAME = "hdzcbsic";
	private static final String CONNECTION_PASSWORD = "1D9QBTNOvgih0ePfyF2UDlcgp9jpGCFs";
	private static final String URL = "jdbc:postgresql://lallah.db.elephantsql.com/hdzcbsic";
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws Exception {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			
			FileInputStream connectionProperties = new FileInputStream("connection.properties");
			Properties properties = new Properties();
			properties.load(connectionProperties);
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	
	static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	

}
