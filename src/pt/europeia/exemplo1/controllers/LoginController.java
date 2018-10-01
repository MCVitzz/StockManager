package pt.europeia.exemplo1.controllers;

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
		
		if (username == "admin" && password == "admin") {
			System.out.println("CERTO");
			Stage newWindow = new Stage();
		}
		
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("User:" + username + "\nPassword:" + password);
			alert.showAndWait();
		}
		
	}
	
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa
	//PILa

}
