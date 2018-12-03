package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.PurchaseItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DialogPurchaseItemReceivingController {

	private Purchase purchase;
	
    @FXML
    private ComboBox<Item> cbItem;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnit;

    public DialogPurchaseItemReceivingController(Purchase purchase) {
    	this.purchase = purchase;
	}

    @FXML
    void initialize() {

    }
    
    @FXML
    void btnFinalize_OnClick(ActionEvent event) {
    	
    }

    @FXML
    void btnNormal_OnClick(ActionEvent event) {
    	txtQuantity.setText(Double.toString(new PurchaseItem(purchase.getCompany(), purchase.getPurchase(), cbItem.getValue().getItem()).getQuantity()));
    }

    @FXML
    void btnReceive_OnClick(ActionEvent event) {
    	
    }

    @FXML
    void cbItem_OnAction(ActionEvent event) {
    	txtItem.setText(cbItem.getValue().getName());
    }
}
