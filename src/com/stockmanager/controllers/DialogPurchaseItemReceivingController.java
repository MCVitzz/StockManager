package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.Location;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.model.PurchaseVolume;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

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
    	for(PurchaseItem pi : purchase.getItems())
    		cbItem.getItems().add(new Item(pi.getCompany(), pi.getItem()));
    }
    
     @FXML
    void btnReceive_OnClick(ActionEvent event) {
    	PurchaseVolume pv = new PurchaseVolume(purchase.getCompany(), purchase.getPurchase(), Long.parseLong(txtVolume.getText()));
    	pv.setLocation(txtLocation.getText());
    	pv.setWarehouse(purchase.getWarehouse());
    	pv.save();
    	pv.makeItem(cbItem.getValue().getItem(), Double.parseDouble(txtQuantity.getText()), txtUnit.getText());
    	reset();
    }

    @FXML
    void btnFinalize_OnClick(ActionEvent event) {
    	purchase.createStock();
    	Utilities.alert(AlertType.INFORMATION, "The Purchase has been completed");
    	purchase.setState("Closed");
    	for(PurchaseItem pi : purchase.getItems()) {
    		pi.setState("Closed");	
    		pi.save();
    	}
    	purchase.save();
		((Stage)txtLocation.getScene().getWindow()).close();
    }

    @FXML
    void btnNormal_OnClick(ActionEvent event) {
    	txtQuantity.setText(Double.toString(new PurchaseItem(purchase.getCompany(), purchase.getPurchase(), cbItem.getValue().getItem()).getQuantity()));
    }

    @FXML
    void btnReceive_OnClick(ActionEvent event) {
    	PurchaseVolume pv = new PurchaseVolume(purchase.getCompany(), purchase.getPurchase(), Long.parseLong(txtVolume.getText()));
    	pv.setLocation(txtLocation.getText());
    	pv.setWarehouse(purchase.getWarehouse());
    	pv.save();
    	pv.makeItem(cbItem.getValue().getItem(), Double.parseDouble(txtQuantity.getText()), txtUnit.getText());
    	PurchaseItem pi = new PurchaseItem(purchase.getCompany(), purchase.getPurchase(), cbItem.getValue().getItem());
    	pi.setConfirmedQuantity(Double.parseDouble(txtQuantity.getText()));
    	pi.save();
    	reset();
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
    
    private void reset() {
    	txtVolume.setDisable(false);
    	hideLocation(true);
    	fillItems();
    	txtItem.setText("");
    	txtQuantity.setText("");
    	txtUnit.setText("");
    }
    
    private void hideLocation(boolean loc) {
    	if(loc) {
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
    	hideLocation(txtLocation.isVisible());
    }

    @FXML
    void cbItem_OnAction(ActionEvent event) {
    	txtItem.setText(cbItem.getValue().getName());
    	txtUnit.setText(cbItem.getValue().getUnit());
    }
}
