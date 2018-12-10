package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class UserPermission extends DatabaseObject {

	String user;
	String role;
	boolean value;

	public UserPermission(String user, String role) {
		this.user = user;
		this.role = role;

		try {
			ResultSet rs = Database.select("SELECT * FROM userpermission WHERE user = '" + user + "' AND Role = '" + role + "'");
			while (rs.next()) {
				this.value = rs.getBoolean("Value");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<UserPermission> getAll() {
		ResultSet rs = Database.select("SELECT User,Role FROM userpermission");
		ArrayList<UserPermission> userpermissions = new ArrayList<UserPermission>();
		try {
			while(rs.next())
				userpermissions.add(new UserPermission(rs.getString("User"), rs.getString("Role")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return userpermissions;
	}
	
	public static ArrayList<UserPermission> getPremissionsFromUser(String user) {
		ResultSet rs = Database.select("SELECT User, Role FROM userpermission WHERE User = '" + user + "'");
		ArrayList<UserPermission> userpermissions = new ArrayList<UserPermission>();
		try {
			while(rs.next())
				userpermissions.add(new UserPermission(rs.getString("User"), rs.getString("Role")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return userpermissions;
	}

	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO userpermission (User, Role, Value) VALUES ('" + user + "', '" + role + "', '" + value + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE userpermission SET value = '" + value + "' WHERE User = '" + user + "' AND Role = '" + role + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("User", "userpermission", "user = '" + user + "' AND role = '" + role + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM userpermission WHERE User = '" + user + "' AND Role = '" + role + "'");
	}

	@Override
	protected boolean validate() {
		return (!Utilities.stringIsEmpty(user) && !Utilities.stringIsEmpty(role));
	}


	public String getUser() {
		return user;
	}

	public String getRole() {
		return role;
	}

	public boolean isValue() {
		return value;
	}




}
