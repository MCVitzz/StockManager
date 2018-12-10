package com.stockmanager.controllers;

import java.util.Date;

import com.stockmanager.model.Database;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.Warehouse;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class DashboardController {

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

	@FXML
	private TableView<Purchase> tblPurchase;

	@FXML
	private TableColumn<Purchase, Integer> clmnPurchase;

	@FXML
	private TableColumn<Purchase, Date> clmnDate;

	@FXML
	private TableColumn<Purchase, String> clmnSupplier;

	@FXML
	private TableColumn<Purchase, String> clmnState;

	@FXML
	private Text purchasesNumber;

	@FXML
	private Text salesNumber;

	@FXML
	private Text itemsNumber;

	@FXML
	private Text warehouseNumber;

	@FXML
	private Text volumesNumber;

	public void initialize() {
		// Warehouse
		clmnCompany.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("company"));
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("warehouse"));
		clmnName.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("name"));
		clmnAddress.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("address"));

		// Purchases
		clmnPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("purchase"));
		clmnDate.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("date"));
		clmnSupplier.setCellValueFactory(new PropertyValueFactory<Purchase, String>("supplier"));
		clmnState.setCellValueFactory(new PropertyValueFactory<Purchase, String>("state"));
		changeNumbers();
		getData();
	}

	public void getData() {
		tblWarehouse.setItems(FXCollections.observableArrayList(Warehouse.getAll()));
		tblPurchase.setItems(FXCollections.observableArrayList(Purchase.getAll()));

	}

	public void changeNumbers() {
		warehouseNumber.setText(Database.simpleSelect("count(company)" , "warehouse")); 
		volumesNumber.setText(Database.simpleSelect("count(volume)", "stockvolume"));
		itemsNumber.setText(Database.simpleSelect("count(item)", "item"));
		salesNumber.setText(Database.simpleSelect("count(sale)", "sale"));
		purchasesNumber.setText(Database.simpleSelect("count(purchase)", "purchase"));


	}

}
