package com.stockmanager.controllers;

import com.stockmanager.model.PaginatedStockVolumeItem;
import com.stockmanager.model.StockVolume;
import com.stockmanager.model.StockVolumeItem;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StockVolumeItemController {

	private StockVolume volume;
	
	@FXML
	private TableView<PaginatedStockVolumeItem> tblStockVolumeItem;

	@FXML
	private TableColumn<PaginatedStockVolumeItem, String> clmnItem;
	
	@FXML
	private TableColumn<PaginatedStockVolumeItem, String> clmnName;
	
	@FXML
	private TableColumn<PaginatedStockVolumeItem, Double> clmnQuantity;
	
	@FXML
	private TableColumn<PaginatedStockVolumeItem, String> clmnUnit;

	public StockVolumeItemController(StockVolume volume) {
		this.volume = volume;
	}
	
	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<PaginatedStockVolumeItem, String>("item"));
		clmnName.setCellValueFactory(new PropertyValueFactory<PaginatedStockVolumeItem, String>("name"));
		clmnQuantity.setCellValueFactory(new PropertyValueFactory<PaginatedStockVolumeItem, Double>("quantity"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<PaginatedStockVolumeItem, String>("unit"));
		
		getData();
	}
	
	public void getData() {
		tblStockVolumeItem.setItems(FXCollections.observableArrayList(PaginatedStockVolumeItem.getAllPaginatedItemsInVolume(volume)));
	}

	@FXML
	public void tblStockVolumeItem_OnClick(MouseEvent e) {
		if (tblStockVolumeItem.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			StockVolumeItem w = tblStockVolumeItem.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogStockVolumeItemView", new DialogStockVolumeItemController(w.getCompany(), w.getVolume(), w.getItem()));
		}
	}
	
	@FXML
	public void btnRefresh_OnClick() {
		getData();
	}
}
