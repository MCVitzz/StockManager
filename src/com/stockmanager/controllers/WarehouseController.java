package com.stockmanager.controllers;

import com.stockmanager.model.Warehouse;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class WarehouseController {

	@FXML
	private TableView<Warehouse> tblWarehouse;

	@FXML
	private TableColumn<Warehouse, String> clmnCompany;
	
	@FXML
	private TableColumn<Warehouse, String> clmnWarehouse;
	
	@FXML
	private TableColumn<Warehouse, String> clmnName;
	
	@FXML
	private TableColumn<Warehouse, String> clmnAddress;

	public void initialize() {
		clmnCompany.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("company"));
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("warehouse"));
		clmnName.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("name"));
		clmnAddress.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("address"));
		
		getData();
	}

	public void getData() {
		tblWarehouse.setItems(FXCollections.observableArrayList(Warehouse.getAll()));
	}
	
	@FXML
	public void tblWarehouse_OnClick(MouseEvent e) {
		if (tblWarehouse.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			Warehouse w = tblWarehouse.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogWarehouseView", new DialogWarehouseController(w.getCompany(), w.getWarehouse(), this));
		}
	}
	
	@FXML
	public void btnAddWarehouse_OnClick() {
		Utilities.openDialog("DialogWarehouseView", new DialogWarehouseController(this));
	}
	
	@FXML
	public void btnRemoveWarehouse_OnClick() {
		if (tblWarehouse.getSelectionModel().getSelectedItem() != null) {
			if(Utilities.confirmDialog("Are you sure you want to permanently remove the warehouse " + tblWarehouse.getSelectionModel().getSelectedItem().getWarehouse() + "?")) {
				tblWarehouse.getSelectionModel().getSelectedItem().delete();
				initialize();
			}
		}
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}
}
