package com.stockmanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;
/***
 * 
 * The class that manages all Database accesses, the lowest level.
 * 
 *
 */
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
		String s = "SELECT " + column + " FROM " + from + " WHERE " + where;
		try {
			ResultSet rs = select(s);
			while (rs.next())
				res = rs.getString(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static String simpleSelect(String column, String from) {
		String res = null;
		String s = "SELECT " + column + " FROM " + from;
		try {
			ResultSet rs = select(s);
			while (rs.next())
				res = rs.getString(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static ArrayList<DatabaseObject> selectQuery() {
		return new ArrayList<DatabaseObject>();
	}

	public static boolean executeQuery(String query) {
		try {
			//query = escape(query);
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

	public static ResultSet select(String query) {
		ResultSet rs = null;
		try {
			query = escape(query);
			System.out.println(query);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	private static String escape(String s) {
		return s;
	}
}
