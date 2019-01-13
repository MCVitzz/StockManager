package com.stockmanager.controllers;


import com.stockmanager.model.Database;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.Sale;
import com.stockmanager.model.StockVolume;
import com.stockmanager.model.StockVolumeItem;
import com.stockmanager.model.Warehouse;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DashboardController {
	@FXML
	private Text purchasesNumber;

	@FXML
	private Text salesNumber;

	@FXML
	private Text itemsNumber;

	@FXML
	private Text warehouseNumber;

	@FXML
	private Text volumesNumber;

	public void initialize() {
		changeNumbers();

	}
	
	public void changeNumbers() {
		warehouseNumber.setText(Warehouse.getDashboardText()); 
		volumesNumber.setText(StockVolume.getDashboardText());
		itemsNumber.setText(StockVolumeItem.getDashboardText());
		salesNumber.setText(Sale.getDashboardText());
		purchasesNumber.setText(Purchase.getDashboardText());
	}

}
