package com.stockmanager.controllers;

import com.stockmanager.model.Purchase;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PurchaseItemController {

	private Purchase purchase;
	
	@FXML
	private TableView<PurchaseItem> tblPurchaseItem;

	@FXML
	private TableColumn<PurchaseItem, String> clmnItem;
	
	@FXML
	private TableColumn<PurchaseItem, String> clmnName;
	
	@FXML
	private TableColumn<PurchaseItem, Double> clmnQuantity;
	
	@FXML
	private TableColumn<PurchaseItem, Double> clmnConfirmed;
	
	@FXML
	private TableColumn<PurchaseItem, String> clmnUnit;
	
	@FXML
	private TableColumn<PurchaseItem, String> clmnState;

	public PurchaseItemController(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("item"));
		clmnName.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("name"));
		clmnQuantity.setCellValueFactory(new PropertyValueFactory<PurchaseItem, Double>("quantity"));
		clmnConfirmed.setCellValueFactory(new PropertyValueFactory<PurchaseItem, Double>("confirmedQuantity"));
		clmnState.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("state"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("unit"));
		
		getData();
	}
	
	public void getData() {
		tblPurchaseItem.setItems(FXCollections.observableArrayList(PurchaseItem.getAllItemsInPurchase(purchase)));
	}

	@FXML
	public void tblPurchaseItem_OnClick(MouseEvent e) {
		if (tblPurchaseItem.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			PurchaseItem w = tblPurchaseItem.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogPurchaseItemView", new DialogPurchaseItemController(w.getCompany(), w.getPurchase(), w.getItem(), this));
		}
	}
	
	@FXML
	public void btnAddPurchaseItem_OnClick() {
		Utilities.openDialog("DialogPurchaseItemView", new DialogPurchaseItemController(purchase.getCompany(), purchase.getPurchase(), this));
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}
}
