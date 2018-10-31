package com.stockmanager.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.stockmanager.model.Location;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogLocationController {

	private Location location;

	@FXML
	private TextField txtCompany;
	
	@FXML
	private TextField txtWarehouse;
	
	@FXML
	private TextField txtLocation;

	@FXML
	private ComboBox<String> sltType;

	private LocationController locationController;



	public DialogLocationController(LocationController locationController) {
		this.locationController = locationController;
	}

	public DialogLocationController(String company, String warehouse, String location, LocationController locationController) {
		this.locationController = locationController;
		this.location = new Location(company, warehouse, location);
	}

	public void initialize() {
		
		sltType.getItems().clear();
		sltType.getItems().addAll(new ArrayList<String>(Arrays.asList(
				"Normal", "Pier"
		)));
		
		if(location != null) {
			txtCompany.setDisable(true);
			txtWarehouse.setDisable(true);
			txtLocation.setDisable(true);
			
			txtCompany.setText(location.getCompany());
			txtWarehouse.setText(location.getWarehouse());
			txtLocation.setText(location.getLocation());
			sltType.setValue(location.getType());
		}
	}

	@FXML
	public void btnSave_OnClick() {
		Location lct = new Location(txtCompany.getText(), txtWarehouse.getText(), txtLocation.getText());
		lct.setType(sltType.getValue());
		lct.save();
		locationController.initialize();
		btnCancel_OnClick();
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtLocation.getScene().getWindow()).close();
	}
}
