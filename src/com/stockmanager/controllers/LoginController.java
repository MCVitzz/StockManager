package com.stockmanager.controllers;

import java.net.URL;

import com.stockmanager.Main;
import com.stockmanager.model.Database;
import com.stockmanager.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

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

		if(Database.simpleSelect("Password", "user", "User = '" + username + "'") == null) {
			alert.setHeaderText("Username does not exist");
			alert.show();
		}
		else
			try {
				if(user.authenticate(password)) {
					Stage newWindow = new Stage();
					URL path = Main.class.getResource("views/MainView.fxml");
					FXMLLoader loader = new FXMLLoader(path);
					Parent root = loader.load();
					Scene scene = new Scene(root);
					newWindow.setScene(scene);
					Window mainWindow = usernameTF.getScene().getWindow();
					newWindow.show();
					((Stage)mainWindow).close();		
				}
				else {
					alert.setHeaderText("The password does not match");
					alert.show();
				}


			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void openNew() {

	}
}
