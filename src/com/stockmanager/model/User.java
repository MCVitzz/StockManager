package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.PasswordUtils;
import com.stockmanager.utils.Utilities;

public class User extends DatabaseObject {

	private String user;
	private String password;
	private String passwordSalt;

	public User() {

	}

	public User(String user) {
		this.user = user;
		try {
			ResultSet rs = Database.select("SELECT * FROM user WHERE User = '" + user + "'");
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

	public boolean authenticate(String password) {
		return PasswordUtils.generateSecurePassword(password, passwordSalt).equals(this.password);
	}

	public void changePassword(String password) {
		passwordSalt = PasswordUtils.getSalt(30);
		this.password = PasswordUtils.generateSecurePassword(password, passwordSalt);
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO user (User, Password, PasswordSalt) VALUES ('" + user + "', '" + password + "', '" + passwordSalt + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE user SET Password = '" + password + "', PasswordSalt = '" + passwordSalt + "' WHERE User = '" + user + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("User", "user", "User = '" + user + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM user WHERE user = '" + this.user + "'");
	}

	protected boolean validate() {
		return (!Utilities.stringIsEmpty(user) && !Utilities.stringIsEmpty(password) && !Utilities.stringIsEmpty(passwordSalt));
	}
}
