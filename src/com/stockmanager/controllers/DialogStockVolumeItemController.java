package com.stockmanager.controllers;

import com.stockmanager.model.StockVolumeItem;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogStockVolumeItemController {

	private StockVolumeItem volume;

	@FXML
	private TextField txtCompany;
	
	@FXML
	private TextField txtVolume;

	@FXML
	private TextField txtItem;

	@FXML
	private TextField txtQuantity;

	@FXML
	private TextField txtUnit;
	
	@FXML
	private DatePicker dtPckrEntryDate;

	public DialogStockVolumeItemController(String company, long volume, String item) {
		this.volume = new StockVolumeItem(company, volume, item);
	}

	public void initialize() {
		txtCompany.setDisable(true);
		txtVolume.setDisable(true);
		txtItem.setDisable(true);
		txtQuantity.setDisable(true);
		txtUnit.setDisable(true);
		dtPckrEntryDate.setDisable(true);
		
		txtCompany.setText(volume.getCompany());
		txtVolume.setText(Long.toString(volume.getVolume()));
		txtItem.setText(volume.getItem());
		txtQuantity.setText(Double.toString(volume.getQuantity()));
		txtUnit.setText(volume.getUnit());
		dtPckrEntryDate.setValue(volume.getEntryDate());
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtCompany.getScene().getWindow()).close();
	}
}
