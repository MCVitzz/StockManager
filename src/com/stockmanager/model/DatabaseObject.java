package com.stockmanager.model;

public abstract class DatabaseObject {
	
	protected abstract boolean insert();
	
	protected abstract boolean update();
	
	protected abstract boolean exists();
	
	protected abstract boolean validate();
	
	public void save() {
		if(this.exists())
			update();
		else
			insert();
	}
}
