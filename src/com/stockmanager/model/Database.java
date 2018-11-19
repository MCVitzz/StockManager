package com.stockmanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Database {
	private static Connection conn;

	public static void connect() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String connection = "jdbc:mysql://localhost:3306/stockmanager";
			String user = "root";
			String password = "password";
			Class.forName(driver);
			conn = DriverManager.getConnection(connection, user, password);
			System.out.println(conn);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static String simpleSelect(String column, String from, String where) {
		String res = null;
		try {
			ResultSet rs = select("SELECT " + column + " FROM " + from + " WHERE " + where);
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
			Utilities.warn(e.getMessage());
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
