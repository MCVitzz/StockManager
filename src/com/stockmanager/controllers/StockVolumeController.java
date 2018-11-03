package com.stockmanager.controllers;

import com.stockmanager.model.StockVolume;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StockVolumeController {

	@FXML
	private TableView<StockVolume> tblStockVolume;

	@FXML
	private TableColumn<StockVolume, String> clmnWarehouse;
	
	@FXML
	private TableColumn<StockVolume, Long> clmnVolume;
	
	@FXML
	private TableColumn<StockVolume, String> clmnLocation;

	public void initialize() {
		clmnWarehouse.setCellValueFactory(new PropertyValueFactory<StockVolume, String>("warehouse"));
		clmnVolume.setCellValueFactory(new PropertyValueFactory<StockVolume, Long>("volume"));
		clmnLocation.setCellValueFactory(new PropertyValueFactory<StockVolume, String>("location"));
		
		getData();
	}

	public void getData() {
		tblStockVolume.setItems(FXCollections.observableArrayList(StockVolume.getAll()));
	}
	
	@FXML
	public void tblStockVolume_OnClick(MouseEvent e) {
		if (tblStockVolume.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			StockVolume w = tblStockVolume.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogStockVolumeView", new DialogStockVolumeController(w.getCompany(), w.getVolume()));
		}
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}	
}
