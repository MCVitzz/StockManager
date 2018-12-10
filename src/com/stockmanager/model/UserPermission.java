package com.stockmanager.model;

public class UserPermission {
	
	String user;
	String permissions;
	boolean value;
	
	public UserPermission(String user, String permissions, boolean value) {
		this.user = user;
		this.permissions = permissions;
		this.value = value;
	}

	public String getUser() {
		return user;
	}

	public String getPermissions() {
		return permissions;
	}

	public boolean isValue() {
		return value;
	}
	
	
	

}
