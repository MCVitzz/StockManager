package com.stockmanager.controllers;

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
	private TableView<StockVolumeItem> tblStockVolumeItem;

	@FXML
	private TableColumn<StockVolume, String> clmnItem;
	
	@FXML
	private TableColumn<StockVolume, Double> clmnQuantity;
	
	@FXML
	private TableColumn<StockVolume, String> clmnUnit;

	public StockVolumeItemController(StockVolume volume) {
		this.volume = volume;
	}
	
	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<StockVolume, String>("item"));
		clmnQuantity.setCellValueFactory(new PropertyValueFactory<StockVolume, Double>("quantity"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<StockVolume, String>("unit"));
		
		tblStockVolumeItem.setItems(FXCollections.observableArrayList(StockVolumeItem.getAllItemsInVolume(volume)));
	}

	@FXML
	public void tblStockVolumeItem_OnClick(MouseEvent e) {
		if (tblStockVolumeItem.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2) {
			StockVolumeItem w = tblStockVolumeItem.getSelectionModel().getSelectedItem();
			Utilities.openDialog("DialogStockVolumeItemView", new DialogStockVolumeItemController(w.getCompany(), w.getVolume(), w.getItem()));
		}
	}
}
