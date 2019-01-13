package com.stockmanager.controllers;

import java.time.LocalDate;

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
	private TableColumn<StockVolumeItem, String> clmnItem;
	
	@FXML
	private TableColumn<StockVolumeItem, String> clmnName;
	
	@FXML
	private TableColumn<StockVolumeItem, Double> clmnQuantity;
	
	@FXML
	private TableColumn<StockVolumeItem, String> clmnUnit;
	
	@FXML
	private TableColumn<StockVolumeItem, LocalDate> clmnEntryDate;

	public StockVolumeItemController(StockVolume volume) {
		this.volume = volume;
	}
	
	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<StockVolumeItem, String>("item"));
		clmnName.setCellValueFactory(new PropertyValueFactory<StockVolumeItem, String>("name"));
		clmnQuantity.setCellValueFactory(new PropertyValueFactory<StockVolumeItem, Double>("quantity"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<StockVolumeItem, String>("unit"));
		clmnEntryDate.setCellValueFactory(new PropertyValueFactory<StockVolumeItem, LocalDate>("entryDate"));
		
		getData();
	}
	
	public void getData() {
		tblStockVolumeItem.setItems(FXCollections.observableArrayList(StockVolumeItem.getAllItemsInVolume(volume)));
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
