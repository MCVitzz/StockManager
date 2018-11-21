package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogPurchaseItemController {

	private PurchaseItem purchaseItem;
	
	private String company;

	@FXML
	private TextField txtItem;
	
	@FXML
	private TextField txtQuantity;

	@FXML
	private TextField txtUnit;

	@FXML
	private TextField txtState;

	@FXML
	private TextField txtConfirmed;
	
	@FXML
	private TextField txtUnitConfirmed;

	public DialogPurchaseItemController(String company, int purchase, String item) {
		this.purchaseItem = new PurchaseItem(company, purchase, item);
		this.company = company;
	}

	public DialogPurchaseItemController(String company, int purchase) {
		this.company = company;
	}

	public void initialize() {
		Utilities.allowOnlyNumbers(txtQuantity);
		Utilities.allowOnlyNumbers(txtConfirmed);
		txtUnit.setDisable(true);
		txtState.setDisable(true);
		txtConfirmed.setDisable(true);
		txtUnitConfirmed.setDisable(true);
		
		if(purchaseItem != null) {
			txtItem.setText(purchaseItem.getItem());
			txtQuantity.setText(Double.toString(purchaseItem.getQuantity()));
			txtUnit.setText(purchaseItem.getUnit());
			txtState.setText(purchaseItem.getState());
			txtConfirmed.setText(Double.toString(purchaseItem.getConfirmedQuantity()));
			txtUnitConfirmed.setText(purchaseItem.getUnit());
		}
		else txtState.setText("Open");
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtItem.getScene().getWindow()).close();
	}
	
	@FXML
	public void txtItem_OnAction() {
		Item item = new Item(company, txtItem.getText());
		txtUnit.setText(item.getUnit());
		txtUnitConfirmed.setText(item.getUnit());
	}
}
