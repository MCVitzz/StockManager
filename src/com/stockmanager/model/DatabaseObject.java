package com.stockmanager.model;

public abstract class DatabaseObject {
	
	protected abstract boolean insert();
	
	protected abstract boolean update();
	
	protected abstract boolean exists();
	
	public abstract boolean delete();
	
	protected abstract boolean validate();
	
	public void save() {
		if(this.exists() && validate())
			update();
		else
			insert();
	}
}
