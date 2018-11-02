package com.stockmanager.controllers;

import com.stockmanager.model.StockVolume;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogStockVolumeController {

	private StockVolume volume;

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

	public DialogStockVolumeController(String company, long volume) {
		this.volume = new StockVolume(company, volume);
	}

	public void initialize() {
		txtCompany.setDisable(true);
		txtWarehouse.setDisable(true);
		txtVolume.setDisable(true);
		txtLocation.setDisable(true);
		
		txtCompany.setText(volume.getCompany());
		txtWarehouse.setText(volume.getWarehouse());
		txtVolume.setText(Long.toString(volume.getVolume()));
		txtLocation.setText(volume.getLocation());
		
		vbStockVolumeItem.getChildren().clear();
		vbStockVolumeItem.getChildren().add(0, Utilities.getNode("StockVolumeItemView", new StockVolumeItemController(volume)));
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtLocation.getScene().getWindow()).close();
	}
}
