package com.stockmanager.controllers;

import com.stockmanager.model.StockVolume;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class DialogStockVolumeController {

	private StockVolume stockVolume;

	@FXML
	private TextField txtCompany;
	
	@FXML
	private TextField txtWarehouse;
	
	@FXML
	private TextField txtVolume;

	@FXML
	private TextField txtLocation;
	
	@FXML
	private VBox vbStockVolumeItem;

	public DialogStockVolumeController(StockVolume stockVolume) {
		this.stockVolume = stockVolume;
	}

	public void initialize() {
		txtCompany.setDisable(true);
		txtWarehouse.setDisable(true);
		txtVolume.setDisable(true);
		txtLocation.setDisable(true);
		
		txtCompany.setText(stockVolume.getCompany());
		txtWarehouse.setText(stockVolume.getWarehouse());
		txtVolume.setText(Long.toString(stockVolume.getVolume()));
		txtLocation.setText(stockVolume.getLocation());
		
		vbStockVolumeItem.getChildren().clear();
		vbStockVolumeItem.getChildren().add(0, Utilities.getNode("StockVolumeItemView", new StockVolumeItemController(stockVolume)));
	}
	
	@FXML
	public void btnBack_OnClick() {
		MainController.getInstance().changeView("StockVolumeView");
	}
	
}
