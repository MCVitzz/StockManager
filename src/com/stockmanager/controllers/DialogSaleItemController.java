package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.SaleItem;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DialogSaleItemController {

	SaleItemController saleItemController;
	
	private SaleItem saleItem;
	
	private String company;
	
	private int sale;

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

	public DialogSaleItemController(String company, int sale, String item, SaleItemController saleItemController) {
		this.saleItem = new SaleItem(company, sale, item);
		this.company = company;
		this.sale = sale;
		this.saleItemController = saleItemController;
	}

	public DialogSaleItemController(String company, int sale, SaleItemController saleItemController) {
		this.company = company;
		this.sale = sale;
		this.saleItemController = saleItemController;
	}

	public void initialize() {
		Utilities.allowOnlyNumbers(txtQuantity);
		Utilities.allowOnlyNumbers(txtConfirmed);
		txtUnit.setDisable(true);
		txtState.setDisable(true);
		txtConfirmed.setDisable(true);
		txtUnitConfirmed.setDisable(true);
		
		if(saleItem != null) {
			txtItem.setText(saleItem.getItem());
			txtQuantity.setText(Double.toString(saleItem.getQuantity()));
			txtUnit.setText(saleItem.getUnit());
			txtState.setText(saleItem.getState().toString());
			txtConfirmed.setText(Double.toString(saleItem.getConfirmedQuantity()));
			txtUnitConfirmed.setText(saleItem.getUnit());
		}
		else {
			txtConfirmed.setText("0");
			txtState.setText("Open");
		}
	}
	
	@FXML
	public void btnSaveSaleItem_OnClick() {
		SaleItem pi = new SaleItem(company, sale, txtItem.getText());
		pi.setConfirmedQuantity(Double.parseDouble(txtConfirmed.getText()));;
		pi.setQuantity(Double.parseDouble(txtQuantity.getText()));
		pi.setUnit(txtUnit.getText());
		pi.setState(txtState.getText());
		pi.save();
		saleItemController.initialize();
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
