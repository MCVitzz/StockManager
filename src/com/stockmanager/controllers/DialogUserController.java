package com.stockmanager.controllers;

import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogUserController {

	private String user;
	
    @FXML
    private VBox vbUserpermissions;
	
    @FXML
    private TextField txtPassword, txtUser;
    
    private UserController userController;
    
    public DialogUserController(UserController userController) {
    	this.userController = userController;
    }
    
    public DialogUserController(String user, UserController userController) {
    	this.userController = userController;
    	this.user = user;
    }
    
    public void initialize() {
    	if(user != null) {
	    	txtUser.setDisable(true);
	    	txtUser.setText(user);
    	}
    	putPag();

    	
    	
    }
    
	@FXML
	void btnSave_OnClick(ActionEvent event) {
		if(!Utilities.stringIsEmpty(txtPassword.getText())) {
			User usr = new User(txtUser.getText());
			usr.changePassword(txtPassword.getText());
			usr.save();
			userController.initialize();
		}
		btnCancel_OnClick();
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtUser.getScene().getWindow()).close();
	}
	
	private void putPag() {
		vbUserpermissions.getChildren().clear();
		vbUserpermissions.getChildren().add(0, Utilities.getNode("UserPermissionsView", new UserPermissionsController(user)));
	}
	
}
