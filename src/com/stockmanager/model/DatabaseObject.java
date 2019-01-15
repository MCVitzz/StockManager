package com.stockmanager.model;

/**
 * 
 * Describes what all Models that represent Database objects should have
 *
 */
public abstract class DatabaseObject {
	/**
	 * 
	 * Inserts on the Database
	 */
	protected abstract boolean insert();
	/**
	 * 
	 * Updates on the Database
	 */
	protected abstract boolean update();
	/**
	 * 
	 * Checks if the object exists
	 */
	protected abstract boolean exists();
	/**
	 * 
	 * Removes from the Database
	 */
	public abstract boolean delete();
	/**
	 * 
	 * Validates for insertion
	 */
	protected abstract boolean validate();
	/**
	 * 
	 * Saves to the Database, checking if exists and validates the Objects
	 */
	public void save() {
		if(this.exists() && validate())
			update();
		else
			insert();
	}
}
