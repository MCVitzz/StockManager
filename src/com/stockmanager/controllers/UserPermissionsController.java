package com.stockmanager.controllers;

import javax.sound.midi.Soundbank;

import com.stockmanager.model.UserPermission;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
		//PropertyValueFactory<UserPermission, String>  a = new PropertyValueFactory<UserPermission, String>("Access");
//		tblUserPermissions.setRowFactory(t -> new TableRow<UserPermission>() {
//			@Override
//		    protected void updateItem(UserPermission item, boolean empty) {
//				super.updateItem(item, empty);
//
//	            if (item == null || empty) {
//	                setText(null);
//	                setStyle("");
//	            } else {
//	                setItem(item);
//	                
//	                UserPermission auxPerson = getTableView().getItems().get(getIndex());
//	                
//                	setTextFill(Color.BLACK);
//	                if (auxPerson.getAccess().equals("Yes"))
//	                    setStyle("-fx-background-color: GREEN");
//	                else
//	                    setStyle("-fx-background-color: RED");
//	            }
//	        }
//		});
		
		clmnAccess.setCellFactory(column -> {
			return new TableCell<UserPermission, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty); //This is mandatory

		            if (item == null || empty) {
		            	System.out.println(item);
		                setText(null);
		                setStyle("");
		            } else { //If the cell is not empty

		                setText(item); //Put the String data in the cell

		                //We get here all the info of the Person of this row
		                UserPermission auxPerson = getTableView().getItems().get(getIndex());

		                // Style all persons wich name is "Edgard"
		                if (auxPerson.getAccess().equals("Yes")) {
		                    setStyle("-fx-background-color: green");
		                } else {
		                    setStyle("-fx-background-color: red");
		                }
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
