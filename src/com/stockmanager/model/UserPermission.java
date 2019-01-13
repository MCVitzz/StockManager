package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class UserPermission extends DatabaseObject {

	String user;
	String type;
	boolean access;

	public UserPermission(String user, String type) {
		this.user = user;
		this.type = type;

		try {
			ResultSet rs = Database.select("SELECT * FROM userpermission WHERE user = '" + Utilities.escape(user) + "' AND Type = '" + Utilities.escape(type) + "'");
			while (rs.next()) {
				this.access = rs.getBoolean("Access");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<UserPermission> getAll() {
		ResultSet rs = Database.select("SELECT User,Type FROM userpermission");
		ArrayList<UserPermission> userpermissions = new ArrayList<UserPermission>();
		try {
			while(rs.next())
				userpermissions.add(new UserPermission(rs.getString("User"), rs.getString("Type")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return userpermissions;
	}
	
	public static ArrayList<UserPermission> getPremissionsFromUser(String user) {
		ResultSet rs = Database.select("SELECT User, Type FROM userpermission WHERE User = '" + Utilities.escape(user) + "'");
		ArrayList<UserPermission> userpermissions = new ArrayList<UserPermission>();
		try {
			while(rs.next())
				userpermissions.add(new UserPermission(rs.getString("User"), rs.getString("Type")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return userpermissions;
	}

	public void switchAccess() {
		this.access = !this.access;
	}
	
	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO userpermission (User, Type, Access) VALUES ('" + Utilities.escape(user) + "', '" + Utilities.escape(type) + "', " + access() + ")");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE userpermission SET Access = " + access() + " WHERE User = '" + Utilities.escape(user) + "' AND Type = '" + Utilities.escape(type) + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("User", "userpermission", "user = '" + Utilities.escape(user) + "' AND Type = '" + Utilities.escape(type) + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM userpermission WHERE User = '" + Utilities.escape(user) + "' AND Type = '" + Utilities.escape(type) + "'");
	}

	@Override
	protected boolean validate() {
		return (!Utilities.stringIsEmpty(user) && !Utilities.stringIsEmpty(type));
	}

	public String getUser() {
		return user;
	}

	public String getType() {
		return type;
	}

	public boolean hasAccess() {
		return access;
	}

	public String getAccess() {
		return access ? "Yes" : "No";
	}

	private int access() {
		if(access)
			return 1;
		else return 0;
	}


}
