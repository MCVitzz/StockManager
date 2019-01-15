package com.stockmanager.controllers;

import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {

	@FXML
	private TextField txtUser;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private void btnLogin_OnClick(ActionEvent event) {
		String username = txtUser.getText();
		String password = txtPassword.getText();
		User user = new User(username);

		if(user.authenticate(password)) Utilities.openScene("MainView", txtPassword.getScene().getWindow(), MainController.getInstance(user));
		else Utilities.warn("Username does not exist/Wrong password.");
	}

	@FXML
	void btnExit_OnClick(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	void txtPassword_KeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			btnLogin_OnClick(null);
	}
}
