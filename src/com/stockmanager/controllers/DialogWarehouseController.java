package com.stockmanager.controllers;

import com.stockmanager.model.Warehouse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogWarehouseController {

	private Warehouse warehouse;

	@FXML
	private TextField txtCompany;
	
	@FXML
	private TextField txtWarehouse;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtName;

	private WarehouseController warehouseController;



	public DialogWarehouseController(WarehouseController warehouseController) {
		this.warehouseController = warehouseController;
	}

	public DialogWarehouseController(String company, String warehouse, WarehouseController warehouseController) {
		this.warehouseController = warehouseController;
		this.warehouse = new Warehouse(company, warehouse);
	}

	public void initialize() {
		if(warehouse != null) {
			txtCompany.setDisable(true);
			txtWarehouse.setDisable(true);
			txtCompany.setText(warehouse.getCompany());
			txtWarehouse.setText(warehouse.getWarehouse());
			txtAddress.setText(warehouse.getAddress());
			txtName.setText(warehouse.getName());
		}
	}

	@FXML
	public void btnSave_OnClick() {
		Warehouse wrh = new Warehouse(txtCompany.getText(), txtWarehouse.getText());
		wrh.setAddress(txtAddress.getText());
		wrh.setName(txtName.getText());
		wrh.save();
		warehouseController.initialize();
		btnCancel_OnClick();
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtWarehouse.getScene().getWindow()).close();
	}
}
