package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DialogPurchaseItemController {

	PurchaseItemController purchaseItemController;
	
	private PurchaseItem purchaseItem;
	
	private String company;
	
	private int purchase;

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

	public DialogPurchaseItemController(String company, int purchase, String item, PurchaseItemController purchaseItemController) {
		this.purchaseItem = new PurchaseItem(company, purchase, item);
		this.company = company;
		this.purchase = purchase;
		this.purchaseItemController = purchaseItemController;
	}

	public DialogPurchaseItemController(String company, int purchase, PurchaseItemController purchaseItemController) {
		this.company = company;
		this.purchase = purchase;
		this.purchaseItemController = purchaseItemController;
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
			txtState.setText(purchaseItem.getState().toString());
			txtConfirmed.setText(Double.toString(purchaseItem.getConfirmedQuantity()));
			txtUnitConfirmed.setText(purchaseItem.getUnit());
		}
		else {
			txtConfirmed.setText("0");
			txtState.setText("Open");
		}
	}
	
	@FXML
	public void btnSavePurchaseItem_OnClick() {
		PurchaseItem pi = new PurchaseItem(company, purchase, txtItem.getText());
		pi.setConfirmedQuantity(Double.parseDouble(txtConfirmed.getText()));;
		pi.setQuantity(Double.parseDouble(txtQuantity.getText()));
		pi.setUnit(txtUnit.getText());
		pi.setState(txtState.getText());
		System.out.println(txtState.getText());
		pi.save();
		purchaseItemController.initialize();
		btnCancel_OnClick();
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtItem.getScene().getWindow()).close();
	}
	
	@FXML
	public void txtItem_KeyPressed(KeyEvent key) {
		if(key.getCode() == KeyCode.TAB) {
			Item item = new Item(company, txtItem.getText());
			txtUnit.setText(item.getUnit());
			txtUnitConfirmed.setText(item.getUnit());
		}
	}
}
