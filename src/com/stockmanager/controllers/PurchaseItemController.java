package com.stockmanager.controllers;

import com.stockmanager.model.paginated.PaginatedPurchaseItem;
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
	private TableView<PaginatedPurchaseItem> tblPurchaseItem;

	@FXML
	private TableColumn<PaginatedPurchaseItem, String> clmnItem;
	
	@FXML
	private TableColumn<PaginatedPurchaseItem, String> clmnName;
	
	@FXML
	private TableColumn<PaginatedPurchaseItem, Double> clmnQuantity;
	
	@FXML
	private TableColumn<PaginatedPurchaseItem, Double> clmnConfirmed;
	
	@FXML
	private TableColumn<PaginatedPurchaseItem, String> clmnUnit;
	
	@FXML
	private TableColumn<PaginatedPurchaseItem, String> clmnState;

	public PurchaseItemController(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<PaginatedPurchaseItem, String>("item"));
		clmnName.setCellValueFactory(new PropertyValueFactory<PaginatedPurchaseItem, String>("name"));
		clmnQuantity.setCellValueFactory(new PropertyValueFactory<PaginatedPurchaseItem, Double>("quantity"));
		clmnConfirmed.setCellValueFactory(new PropertyValueFactory<PaginatedPurchaseItem, Double>("confirmedQuantity"));
		clmnState.setCellValueFactory(new PropertyValueFactory<PaginatedPurchaseItem, String>("state"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<PaginatedPurchaseItem, String>("unit"));
		
		getData();
	}
	
	public void getData() {
		tblPurchaseItem.setItems(FXCollections.observableArrayList(PaginatedPurchaseItem.getAllPaginatedItemsInPurchase(purchase)));
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
