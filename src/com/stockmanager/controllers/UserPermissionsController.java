package com.stockmanager.controllers;

import com.stockmanager.model.UserPermission;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserPermissionsController {

	private String user;

	@FXML
	private TableView<UserPermission> tblUserPermissions;

	@FXML
	private TableColumn<UserPermission, String> clmnUser;

	@FXML
	private TableColumn<UserPermission, String> clmnRole;

	public void initialize() {
		clmnUser.setCellValueFactory(new PropertyValueFactory<UserPermission, String>("User"));
		clmnRole.setCellValueFactory(new PropertyValueFactory<UserPermission, String>("Role"));
		
		getData();
	}
	
	public void getData() {
		tblUserPermissions.setItems(FXCollections.observableArrayList(UserPermission.getPremissionsFromUser(user)));
	}
	
	public UserPermissionsController(String user) {
		this.user = user;
	}

	@FXML
	void btnRefresh_OnClick(ActionEvent event) {

	}
	
	@FXML
	void btnUserPermissions_OnClick() {
		
	}

	@FXML
	void tblUserPermissions_OnClick(ActionEvent event) {

	}
}
