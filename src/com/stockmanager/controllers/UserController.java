package com.stockmanager.controllers;

import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UserController {

	@FXML
	private TableView<User> tblUser;


	@FXML
	private TableColumn<User, String> clmnUsername;

	public void initialize() {
		clmnUsername.setCellValueFactory(new PropertyValueFactory<User, String>("user"));
		tblUser.setItems(FXCollections.observableArrayList(User.getAll()));
	}

	@FXML
	public void tblUser_OnClick(MouseEvent e) {
		if (tblUser.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2)
			Utilities.openDialog("DialogUserView", new DialogUserController(tblUser.getSelectionModel().getSelectedItem().getUser(), this));
	}
	
	@FXML
	public void btnAddUser_OnClick() {
		Utilities.openDialog("DialogUserView", new DialogUserController(this));
	}
	
	@FXML
	public void btnRemoveUser_OnClick() {
		if (tblUser.getSelectionModel().getSelectedItem() != null) {
			if(Utilities.confirmDialog("Are you sure you want to permanently remove the user " + tblUser.getSelectionModel().getSelectedItem().getUser() + "?")) {
				new User(tblUser.getSelectionModel().getSelectedItem().getUser()).delete();
				initialize();
			}
		}
	}
}
