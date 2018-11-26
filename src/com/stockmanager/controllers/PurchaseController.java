package com.stockmanager.controllers;

import java.util.Date;

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
	private TableColumn<Purchase, Integer> clmnPurchase;
	
	@FXML
	private TableColumn<Purchase, String> clmnWarehouse;
	
	@FXML
	private TableColumn<Purchase, Date> clmnDate;
	
	@FXML
	private TableColumn<Purchase, String> clmnSupplier;
	
	@FXML
	private TableColumn<Purchase, String> clmnState;

	public void initialize() {
		clmnPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("purchase"));
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<Purchase, String>("warehouse"));
		clmnDate.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("date"));
		clmnSupplier.setCellValueFactory(new PropertyValueFactory<Purchase, String>("supplier"));
		clmnState.setCellValueFactory(new PropertyValueFactory<Purchase, String>("state"));
		
		getData();
	}
	
	@FXML
	public void btnAddPurchase_OnClick() {
		Utilities.openDialog("DialogPurchaseView", new DialogPurchaseController(this, null));
	}

	public void getData() {
		tblPurchase.setItems(FXCollections.observableArrayList(Purchase.getAll()));
	}
	
	@FXML
	public void tblPurchase_OnClick(MouseEvent e) {
		if (tblPurchase.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2)
			Utilities.openDialog("DialogPurchaseView", new DialogPurchaseController(this, tblPurchase.getSelectionModel().getSelectedItem()));
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}	
}
