package com.stockmanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Database {
	private static Connection conn;

	public static Connection connect() {
		try {
			
			
			String driver = "com.mysql.jdbc.Driver";
		    String connection = "jdbc:mysql://localhost:3306/stockmanager";
		    String user = "root";
		    String password = "Inverno2017";
		    Class.forName(driver);
		    conn = DriverManager.getConnection(connection, user, password);

			System.out.println(conn);
			
		    
//			Properties connectionProps = new Properties();
//			connectionProps.put("user", "root");
//			connectionProps.put("password", "Inverno2017");
//			conn = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("Connected to database");
			return conn;
		}
		catch(Exception e) {
			System.out.println("Ripalhada");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static String simpleSelect(String column, String from, String where) {
		String res = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT " + column + " FROM " + from + " WHERE " + where;
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
					res = rs.getString(column);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static boolean executeQuery(String query) {
		try {
			System.out.println(query);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			return true;
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(e.getMessage());
			alert.showAndWait();
			return false;
		}
	}

	public static ArrayList<DatabaseObject> selectQuery() {
		return new ArrayList<DatabaseObject>();
	}	
	
	public static ResultSet select(String query) {
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
