package com.stockmanager.controllers;

import com.stockmanager.model.Purchase;
import com.stockmanager.model.Sale;
import com.stockmanager.model.StockVolume;
import com.stockmanager.model.StockVolumeItem;
import com.stockmanager.model.Warehouse;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DashboardController {
	@FXML
	private Text lblPurchases;

	@FXML
	private Text lblSales;

	@FXML
	private Text lblItems;

	@FXML
	private Text lblWarehouses;

	@FXML
	private Text lblVolumes;

	public void initialize() {
		changeNumbers();
	}
	
	/*
	 * Updates the displayed numbers on the Dashboard 
	 */
	
	public void changeNumbers() {
		lblWarehouses.setText(Warehouse.getDashboardText()); 
		lblVolumes.setText(StockVolume.getDashboardText());
		lblItems.setText(StockVolumeItem.getDashboardText());
		lblSales.setText(Sale.getDashboardText());
		lblPurchases.setText(Purchase.getDashboardText());
	}

}
