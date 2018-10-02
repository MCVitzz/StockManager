package com.stockmanager.controllers;

import com.stockmanager.model.Database;
import com.stockmanager.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usernameTF;
	@FXML
	private PasswordField passwordPF; 

	@FXML
	private void login() {
		String username = usernameTF.getText();
		String password = passwordPF.getText();

		Alert alert = new Alert(AlertType.WARNING);
		User user = new User(username);
		if(user.authenticate(password))
			alert.setHeaderText("YAY");
		else 
			alert.setHeaderText("NAY");
		alert.showAndWait();
	}
}
