package com.stockmanager.controllers;

import com.stockmanager.model.Company;
import com.stockmanager.model.User;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserPermissionsController {

	private String user;

	@FXML
	private TableView<User> tblUserPermissions;

	@FXML
	private TableColumn<User, String> clmnUser;

	@FXML
	private TableColumn<User, String> clmnRole;

	public void initialize() {
		clmnUser.setCellValueFactory(new PropertyValueFactory<User, String>("User"));
		clmnRole.setCellValueFactory(new PropertyValueFactory<User, String>("Role"));
		
		getData();
	}
	
	public void getData() {
		tblUserPermissions.setItems(FXCollections.observableArrayList(User.getAll()));
	}
	
	public UserPermissionsController(String user) {
		this.user = user;
	}

	@FXML
	void btnRefresh_OnClick(ActionEvent event) {

	}

	@FXML
	void tblUserPermissions_OnClick(ActionEvent event) {

	}
}
