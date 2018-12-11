package com.stockmanager.controllers;


import com.stockmanager.model.Database;

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
		warehouseNumber.setText(Database.simpleSelect("count(company)" , "warehouse")); 
		volumesNumber.setText(Database.simpleSelect("count(volume)", "stockvolume"));
		itemsNumber.setText(Database.simpleSelect("count(item)", "item"));
		salesNumber.setText(Database.simpleSelect("count(sale)", "sale"));
		purchasesNumber.setText(Database.simpleSelect("count(purchase)", "purchase"));


	}

}
