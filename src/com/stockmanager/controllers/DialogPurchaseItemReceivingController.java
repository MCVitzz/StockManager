package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.Location;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.RowConstraints;

public class DialogPurchaseItemReceivingController {

	private Purchase purchase;
	
	private long volume;
	
    @FXML
    private ComboBox<Item> cbItem;
    
    @FXML
    private Label lblLocation;

    @FXML
    private TextField txtVolume;
    
    @FXML
    private TextField txtItem;
    
    @FXML
    private TextField txtLocation;

    @FXML
    private RowConstraints rowLocation;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnit;

    public DialogPurchaseItemReceivingController(Purchase purchase, long volume) {
    	this.purchase = purchase;
    	this.volume = volume;
	}

    @FXML
    void initialize() {
    	fillItems();
    	txtItem.setDisable(true);
    	txtUnit.setDisable(true);
    	if(volume != 0) {
    		txtVolume.setDisable(true);
    		txtVolume.setText(Long.toString(volume));
    	}
    }
    
    private void fillItems() {
    	cbItem.getItems().clear();
    	for(PurchaseItem pi : purchase.getPurchaseItems())
    		cbItem.getItems().add(new Item(pi.getCompany(), pi.getItem()));
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
    void txtLocation_OnAction() {
    	if(new Location(purchase.getCompany(), purchase.getWarehouse(), txtLocation.getText()).getType() == null) {
    		Utilities.warn("The Location does not exist.");
    	}
    	else {
    		Utilities.alert(AlertType.INFORMATION, "Location Confirmed.");
    	}
    }
    
    private void toggleLocation() {
    	if(txtLocation.isVisible()) {
    		rowLocation.setMaxHeight(0);
    		txtLocation.setVisible(false);
    		lblLocation.setVisible(false);
    	}
    	else {
        	rowLocation.setMaxHeight(33);
    		txtLocation.setVisible(true);
    		lblLocation.setVisible(true);
    	}
    }
    
    @FXML
    void btnChange_OnClick(ActionEvent event) {
    	txtVolume.setDisable(false);
    	txtVolume.requestFocus();
    	toggleLocation();
    }

    @FXML
    void cbItem_OnAction(ActionEvent event) {
    	txtItem.setText(cbItem.getValue().getName());
    	txtUnit.setText(cbItem.getValue().getUnit());
    }
}
