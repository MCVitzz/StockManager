package com.stockmanager.controllers;

import java.util.HashMap;

/**
 * 
 * Interface of the main controllers on the side menu
 *
 */
public interface Controller {
	
	/**
	 * Checks permissions for the User
	 * @param permissions
	 */
	public void checkPermissions(HashMap<String, Boolean> permissions);
}
