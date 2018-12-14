package com.stockmanager.controllers;

import com.stockmanager.model.UserPermission;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UserPermissionsController {

	private String user;

	@FXML
	private TableView<UserPermission> tblUserPermissions;

	@FXML
	private TableColumn<UserPermission, String> clmnType;

	@FXML
	private TableColumn<UserPermission, String> clmnAccess;

	public void initialize() {
		clmnType.setCellValueFactory(new PropertyValueFactory<UserPermission, String>("Type"));
		clmnAccess.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccess()));
		clmnAccess.setCellFactory(column -> {
			return new TableCell<UserPermission, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						UserPermission auxPerson = getTableView().getItems().get(getIndex());
						if (auxPerson.hasAccess())
							setStyle("-fx-background-color: #007c40");
						else
							setStyle("-fx-background-color: #894a40");
					}
				}
			};
		});

		tblUserPermissions.getSelectionModel().setCellSelectionEnabled(true);
		getData();
	}

	public void getData() {
		tblUserPermissions.setItems(FXCollections.observableArrayList(UserPermission.getPremissionsFromUser(user)));
	}

	public UserPermissionsController(String user) {
		this.user = user;
	}

	@FXML
	void btnRefresh_OnClick() {
		getData();
	}

	@FXML
	void btnUserPermissions_OnClick() {

	}

	@FXML
	void tblUserPermissions_OnClick(MouseEvent e) {
		if (tblUserPermissions.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2 && tblUserPermissions.getSelectionModel().getSelectedCells().get(0).getColumn() == 1) {
			tblUserPermissions.getSelectionModel().getSelectedItem().switchAccess();
			tblUserPermissions.getSelectionModel().getSelectedItem().save();
			btnRefresh_OnClick();
			MainController.getInstance().checkPermissions(MainController.getInstance().getUser().getPermissions());
		}
	}

}
