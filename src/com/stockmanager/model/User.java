package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.stockmanager.utils.PasswordUtils;
import com.stockmanager.utils.Utilities;

public class User extends DatabaseObject {

	private String user;
	private String password;
	private String passwordSalt;

	public User() {}

	public User(String user) {
		this.user = user;
		try {
			ResultSet rs = Database.select("SELECT * FROM user WHERE User = '" + Utilities.escape(user) + "'");
			while (rs.next()) {
				this.password = rs.getString("Password");
				this.passwordSalt = rs.getString("PasswordSalt");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUser() {
		return this.user;
	}

	public static ArrayList<User> getAll() {
		ResultSet rs = Database.select("SELECT User FROM user");
		ArrayList<User> users = new ArrayList<User>();
		try {
			while(rs.next())
				users.add(new User(rs.getString("User")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return users;
	}
	
	private ArrayList<UserPermission> getUsersPermissions() {
		ResultSet rs = Database.select("SELECT Type FROM userpermission WHERE User = '" + Utilities.escape(user) + "'");
		ArrayList<UserPermission> permissions = new ArrayList<UserPermission>();
		try {
			while(rs.next())
				permissions.add(new UserPermission(user, rs.getString("Type")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return permissions;
	}
	
	public HashMap<String, Boolean> getPermissions() {
		HashMap<String, Boolean> permissions = new HashMap<String, Boolean>();
		for(UserPermission permission : getUsersPermissions())
			permissions.put(permission.getType(), permission.hasAccess());
		return permissions;
	}

	public boolean authenticate(String password) {		
		return Database.simpleSelect("Password", "user", "User = '" + Utilities.escape(user) + "'") != null && PasswordUtils.generateSecurePassword(password, passwordSalt).equals(this.password);
	}

	public void changePassword(String password) {
		passwordSalt = PasswordUtils.getSalt(30);
		this.password = PasswordUtils.generateSecurePassword(password, passwordSalt);
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO user (User, Password, PasswordSalt) VALUES ('" + Utilities.escape(user) + "', '" + Utilities.escape(password) + "', '" + Utilities.escape(passwordSalt) + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE user SET Password = '" + Utilities.escape(password) + "', PasswordSalt = '" + Utilities.escape(passwordSalt) + "' WHERE User = '" + Utilities.escape(user) + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("User", "user", "User = '" + Utilities.escape(user) + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM user WHERE user = '" + Utilities.escape(user) + "'");
	}

	protected boolean validate() {
		return (!Utilities.stringIsEmpty(user) && !Utilities.stringIsEmpty(password) && !Utilities.stringIsEmpty(passwordSalt));
	}
}
