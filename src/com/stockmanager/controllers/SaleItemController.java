package com.stockmanager.controllers;

import com.stockmanager.model.paginated.PaginatedSaleItem;
import com.stockmanager.model.Sale;
import com.stockmanager.model.SaleItem;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SaleItemController {

	private Sale sale;
	
	@FXML
	private TableView<PaginatedSaleItem> tblSaleItem;

	@FXML
	private TableColumn<PaginatedSaleItem, String> clmnItem;
	
	@FXML
	private TableColumn<PaginatedSaleItem, String> clmnName;
	
	@FXML
	private TableColumn<PaginatedSaleItem, Double> clmnQuantity;
	
	@FXML
	private TableColumn<PaginatedSaleItem, Double> clmnConfirmed;
	
	@FXML
	private TableColumn<PaginatedSaleItem, String> clmnUnit;
	
	@FXML
	private TableColumn<PaginatedSaleItem, String> clmnState;

	public SaleItemController(Sale sale) {
		this.sale = sale;
	}
	
	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<PaginatedSaleItem, String>("item"));
		clmnName.setCellValueFactory(new PropertyValueFactory<PaginatedSaleItem, String>("name"));
		clmnQuantity.setCellValueFactory(new PropertyValueFactory<PaginatedSaleItem, Double>("quantity"));
		clmnConfirmed.setCellValueFactory(new PropertyValueFactory<PaginatedSaleItem, Double>("confirmedQuantity"));
		clmnState.setCellValueFactory(new PropertyValueFactory<PaginatedSaleItem, String>("state"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<PaginatedSaleItem, String>("unit"));
		
		getData();
	}
	
	public void getData() {
		tblSaleItem.setItems(FXCollections.observableArrayList(PaginatedSaleItem.getAllPaginatedItemsInSale(sale)));
	}

	@FXML
	public void tblSaleItem_OnClick(MouseEvent e) {
		if (tblSaleItem.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			SaleItem w = tblSaleItem.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogSaleItemView", new DialogSaleItemController(w.getCompany(), w.getSale(), w.getItem(), this));
		}
	}
	
	@FXML
	public void btnAddSaleItem_OnClick() {
		Utilities.openDialog("DialogSaleItemView", new DialogSaleItemController(sale.getCompany(), sale.getSale(), this));
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}
}
