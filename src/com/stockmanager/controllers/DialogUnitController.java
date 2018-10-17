package com.stockmanager.controllers;

import com.stockmanager.model.Unit;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogUnitController {

	private Unit unit;

	@FXML
	private TextField txtUnit;

	@FXML
	private TextField txtName;

	private UnitController unitController;



	public DialogUnitController(UnitController unitController) {
		this.unitController = unitController;
	}

	public DialogUnitController(String unit, UnitController unitController) {
		this.unitController = unitController;
		this.unit = new Unit(unit);
	}

	public void initialize() {
		if(unit != null) {
			txtUnit.setDisable(true);
			txtUnit.setText(unit.getUnit());
			txtName.setText(unit.getName());
			
		}
	}

	@FXML
	public void btnUnitSave_OnClick() {
		Unit unt = new Unit("");
		unt.setUnit(txtUnit.getText());
		unt.setName(txtName.getText());
		unt.save();
		unitController.initialize();
		btnUnitCancel_OnClick();
	}

	@FXML
	public void btnUnitCancel_OnClick() {
		((Stage)txtUnit.getScene().getWindow()).close();
	}
	
}
