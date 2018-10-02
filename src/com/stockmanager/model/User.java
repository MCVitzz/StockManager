package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.stockmanager.utils.PasswordUtils;
import com.stockmanager.utils.Utilities;

public class User extends DatabaseObject {

	private String name;
	private String password;
	private String passwordSalt;

	public User() {

	}

	public User(String name) {
		this.name = name;
		try {
			ResultSet rs = Database.select("SELECT * FROM user WHERE User = '" + name + "'");
			while (rs.next()) {
				this.password = rs.getString("Password");
				this.passwordSalt = rs.getString("PasswordSalt");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean authenticate(String password) {
		return PasswordUtils.generateSecurePassword(password, passwordSalt).equals(this.password);
	}

	public void changePassword(String password) {
		passwordSalt = PasswordUtils.getSalt(30);
		this.password = PasswordUtils.generateSecurePassword(password, passwordSalt);
	}

	protected boolean insert() {
		if(validate())
			return Database.executeQuery("INSERT INTO user (User, Password, PasswordSalt) VALUES ('" + name + "', '" + password + "', '" + passwordSalt + "')");
		else 
			return false;
	}

	protected boolean update() {
		if(validate())
			return Database.executeQuery("UPDATE user SET Password = '" + password + "', PasswordSalt = '" + passwordSalt + "' WHERE User = '" + name + "'");
		else 
			return false;
	}

	protected boolean exists() {
		return Database.simpleSelect("*", "user", "User = '" + name + "'") != null;
	}

	protected boolean validate() {
		return (!Utilities.stringIsEmpty(name) && !Utilities.stringIsEmpty(password) && !Utilities.stringIsEmpty(passwordSalt));
	}
}
