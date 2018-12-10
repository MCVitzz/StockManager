package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.SaleItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DialogSaleItemPickController {
	
	private PickItem pickItem;
	
	@FXML
	private TextField txtVolume;
	
	@FXML
	private ComboBox<Item> cbItem;
	
	@FXML
	private TextField txtItem;
	
	@FXML
	private TextField txtQuantity;
	
	@FXML
	private TextField txtUnit;
	
	public DialogSaleItemPickController(PickItem pickItem) {
		this.pickItem = pickItem;
		
	}
	
	@FXML
	void initialize() {
		txtUnit.setVisible(false);
	}
	
	@FXML
	void txtItem_OnAction(ActionEvent event) {
		txtUnit.setText();
	}
	
	@FXML
	void btnPick_OnClick(ActionEvent event) {
		
	}
	
	@FXML
	void btnFinalize_OnClick(ActionEvent event) {
		
	}
	
}
