package com.stockmanager.controllers;


import com.stockmanager.model.Company;
import com.stockmanager.model.Unit;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogUnitController {

	private Unit unit;
	
	
	@FXML
	private ComboBox<Company> cbCompany;

	@FXML
	private TextField txtUnit;

	@FXML
	private TextField txtName;

	private UnitController unitController;



	public DialogUnitController(UnitController unitController) {
		this.unitController = unitController;
	}

	public DialogUnitController(String name, String unit, UnitController unitController) {
		this.unitController = unitController;
		this.unit = new Unit(name, unit);
	}

	public void initialize() {
		
		cbCompany.getItems().clear();
		cbCompany.getItems().addAll(Company.getAll());
				
		if(unit != null) {
			txtUnit.setDisable(true);
			cbCompany.setValue(new Company(unit.getCompany()));
			txtUnit.setText(unit.getUnit());
			txtName.setText(unit.getName());
			
		}
	}

	@FXML
	public void btnUnitSave_OnClick() {
		Unit unt = new Unit(txtUnit.getText(),txtName.getText());
		unt.setCompany(cbCompany.getValue().getCompany());
		unt.save();
		unitController.initialize();
		btnUnitCancel_OnClick();
	}

	@FXML
	public void btnUnitCancel_OnClick() {
		((Stage)txtUnit.getScene().getWindow()).close();
	}
	
}
