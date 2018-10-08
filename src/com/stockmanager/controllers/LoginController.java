package com.stockmanager.controllers;

import java.net.URL;

import com.stockmanager.Main;
import com.stockmanager.model.Database;
import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController extends BaseController {

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

		if(Database.simpleSelect("Password", "user", "User = '" + username + "'") == null) Utilities.warn("Username does not exist.");
		else try {
				if(user.authenticate(password)) Utilities.openScene("MainView", passwordPF.getScene().getWindow());
				loader.setController(new LoginController());

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
	 
	 	public void openNew() {

	 	}
}
