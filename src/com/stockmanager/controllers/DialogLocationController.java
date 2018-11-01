package com.stockmanager.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.stockmanager.model.Company;
import com.stockmanager.model.Location;
import com.stockmanager.model.Warehouse;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogLocationController {

	private Location location;

	@FXML
	private ComboBox<Company> sltCompany;
	
	@FXML
	private ComboBox<Warehouse> sltWarehouse;
	
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
		
		sltCompany.getItems().clear();
		sltCompany.getItems().addAll(Company.getAll());
		
		sltType.getItems().clear();
		sltType.getItems().addAll(new ArrayList<String>(Arrays.asList(
				"Normal", "Pier"
		)));
		
		if(location != null) {
			sltCompany.setDisable(true);
			sltWarehouse.setDisable(true);
			txtLocation.setDisable(true);
			
			fillWarehouse(location.getCompany());
			
			sltCompany.setValue(new Company(location.getCompany()));
			sltWarehouse.setValue(new Warehouse(location.getCompany(), location.getWarehouse()));
			txtLocation.setText(location.getLocation());
			sltType.setValue(location.getType());
		}
	}

	@FXML
	public void btnSave_OnClick() {
		Location lct = new Location(sltCompany.getValue().getCompany(), sltWarehouse.getValue().getWarehouse(), txtLocation.getText());
		lct.setType(sltType.getValue());
		lct.save();
		locationController.initialize();
		btnCancel_OnClick();
	}
	
	@FXML
	public void sltCompany_OnAction() {
		fillWarehouse(sltCompany.getValue().getCompany());
	}
	
	public void fillWarehouse(String company) {
		sltWarehouse.getItems().clear();
		sltWarehouse.getItems().addAll(Warehouse.getAllWarehousesFromCompany(company));
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtLocation.getScene().getWindow()).close();
	}
}
