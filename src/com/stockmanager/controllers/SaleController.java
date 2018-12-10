package com.stockmanager.controllers;

import java.util.Date;

import com.stockmanager.model.Sale;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SaleController {

	@FXML
	private TableView<Sale> tblSale;

	@FXML
	private TableColumn<Sale, Integer> clmnSale;
	
	@FXML
	private TableColumn<Sale, String> clmnWarehouse;
	
	@FXML
	private TableColumn<Sale, Date> clmnDate;
	
	@FXML
	private TableColumn<Sale, String> clmnClient;
	
	@FXML
	private TableColumn<Sale, String> clmnState;

	public void initialize() {
		clmnSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("sale"));
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<Sale, String>("warehouse"));
		clmnDate.setCellValueFactory(new PropertyValueFactory<Sale, Date>("date"));
		clmnClient.setCellValueFactory(new PropertyValueFactory<Sale, String>("client"));
		clmnState.setCellValueFactory(new PropertyValueFactory<Sale, String>("state"));
		
		getData();
	}
	
	@FXML
	public void btnAddSale_OnClick() {
		Utilities.openDialog("DialogSaleView", new DialogSaleController(this, null));
	}

	public void getData() {
		tblSale.setItems(FXCollections.observableArrayList(Sale.getAll()));
	}
	
	@FXML
	public void tblSale_OnClick(MouseEvent e) {
		if (tblSale.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2)
			MainController.getInstance().changeView(Utilities.getNode("DialogSaleView", new DialogSaleController(this, tblSale.getSelectionModel().getSelectedItem())));
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}	
}
