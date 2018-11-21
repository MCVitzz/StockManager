package com.stockmanager.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.stockmanager.model.Company;
import com.stockmanager.model.Location;
import com.stockmanager.model.Warehouse;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogLocationController {

	private Location location;

	@FXML
	private ComboBox<Company> cbCompany;
	
	@FXML
	private ComboBox<Warehouse> cbWarehouse;
	
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
		
		cbCompany.getItems().clear();
		cbCompany.getItems().addAll(Company.getAll());
		
		sltType.getItems().clear();
		sltType.getItems().addAll(new ArrayList<String>(Arrays.asList(
				"Normal", "Pier"
		)));
		
		if(location != null) {
			cbCompany.setDisable(true);
			cbWarehouse.setDisable(true);
			txtLocation.setDisable(true);
			
			Utilities.fillWarehouses(cbWarehouse, location.getCompany());
			
			cbCompany.setValue(new Company(location.getCompany()));
			cbWarehouse.setValue(new Warehouse(location.getCompany(), location.getWarehouse()));
			txtLocation.setText(location.getLocation());
			sltType.setValue(location.getType());
		}
	}

	@FXML
	public void btnSave_OnClick() {
		Location lct = new Location(cbCompany.getValue().getCompany(), cbWarehouse.getValue().getWarehouse(), txtLocation.getText());
		lct.setType(sltType.getValue());
		lct.save();
		locationController.initialize();
		btnCancel_OnClick();
	}
	
	@FXML
	public void sltCompany_OnAction() {
		Utilities.fillWarehouses(cbWarehouse, cbCompany.getValue().getCompany());
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtLocation.getScene().getWindow()).close();
	}
}
