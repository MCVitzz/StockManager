package com.stockmanager.controllers;

import com.stockmanager.model.Purchase;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PurchaseController {

	@FXML
	private TableView<Purchase> tblPurchase;

	@FXML
	private TableColumn<Purchase, String> clmnWarehouse;
	
	@FXML
	private TableColumn<Purchase, Long> clmnPurchase;
	
	@FXML
	private TableColumn<Purchase, String> clmnLocation;

	public void initialize() {
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<Purchase, String>("warehouse"));
		clmnPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Long>("purchase"));
		clmnLocation.setCellValueFactory(new PropertyValueFactory<Purchase, String>("location"));
		
		getData();
	}

	public void getData() {
		tblPurchase.setItems(FXCollections.observableArrayList(Purchase.getAll()));
	}
	
	@FXML
	public void tblPurchase_OnClick(MouseEvent e) {
		if (tblPurchase.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			Purchase w = tblPurchase.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogPurchaseView", new DialogPurchaseController(w.getCompany(), w.getPurchase()));
		}
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}	
}
