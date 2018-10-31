package com.stockmanager.controllers;

import com.stockmanager.model.Location;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class LocationController {

	@FXML
	private TableView<Location> tblLocation;

	@FXML
	private TableColumn<Location, String> clmnWarehouse;
	
	@FXML
	private TableColumn<Location, String> clmnLocation;
	
	@FXML
	private TableColumn<Location, String> clmnType;

	public void initialize() {
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<Location, String>("warehouse"));
		clmnLocation.setCellValueFactory(new PropertyValueFactory<Location, String>("location"));
		clmnType.setCellValueFactory(new PropertyValueFactory<Location, String>("type"));
		
		tblLocation.setItems(FXCollections.observableArrayList(Location.getAll()));
	}

	@FXML
	public void tblLocation_OnClick(MouseEvent e) {
		if (tblLocation.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			Location w = tblLocation.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogLocationView", new DialogLocationController(w.getCompany(), w.getWarehouse(), w.getLocation(), this));
		}
	}
	
	@FXML
	public void btnAddLocation_OnClick() {
		Utilities.openDialog("DialogLocationView", new DialogLocationController(this));
	}
	
	@FXML
	public void btnRemoveLocation_OnClick() {
		if (tblLocation.getSelectionModel().getSelectedItem() != null) {
			if(Utilities.confirmDialog("Are you sure you want to permanently remove the location " + tblLocation.getSelectionModel().getSelectedItem().getLocation() + "?")) {
				tblLocation.getSelectionModel().getSelectedItem().delete();
				initialize();
			}
		}
	}
}
