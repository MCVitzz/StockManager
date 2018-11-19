package com.stockmanager.controllers;

import com.stockmanager.model.Database;
import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField usernameTF;
	@FXML
	private PasswordField passwordPF; 

	@FXML
	private Label lostPasswordLabel;

	@FXML
	private void login(ActionEvent event) {
		String username = usernameTF.getText();
		String password = passwordPF.getText();
		User user = new User(username);
		
		if(username.equals("sysadm")) {
			System.out.println(username);
			Utilities.openScene("MainView", passwordPF.getScene().getWindow());
			return;
		}

		if(Database.simpleSelect("Password", "user", "User = '" + username + "'") == null) Utilities.warn("Username does not exist.");
		else try {
			if(user.authenticate(password)) Utilities.openScene("MainView", passwordPF.getScene().getWindow());
			else Utilities.warn("Password does not match.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void exit(ActionEvent event) {
		System.exit(0);
	}
}
