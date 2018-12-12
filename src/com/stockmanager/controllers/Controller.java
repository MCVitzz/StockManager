package com.stockmanager.controllers;

import java.util.HashMap;

public interface Controller {
	public void checkPermissions(HashMap<String, Boolean> permissions);
}
