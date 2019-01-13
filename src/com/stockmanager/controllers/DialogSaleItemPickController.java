package com.stockmanager.controllers;

import java.util.ArrayList;

import com.stockmanager.model.Item;
import com.stockmanager.model.PickItem;
import com.stockmanager.model.Sale;
import com.stockmanager.model.SaleVolume;
import com.stockmanager.model.SaleVolumeItem;
import com.stockmanager.model.StockVolume;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
public class DialogSaleItemPickController {
	
	private Sale sale;
	
	private ArrayList<PickItem> pktlist;
	private PickItem pickItem;
	
	@FXML
	private TextField txtVolume;
	
	@FXML
	private TextField txtItem;
	
	@FXML
	private TextField txtQuantity;
	
	@FXML
	private TextField txtUnit;
	
	public DialogSaleItemPickController(ArrayList<PickItem> pktlist, Sale sale) {
		this.pktlist = pktlist;
		pickItem = pktlist.get(0);
		this.sale = sale;
	}
	
	@FXML
	void initialize() {
		txtUnit.setVisible(false);
		putPlaceHolders();
	}
	
	@FXML
	void txtItem_OnAction(ActionEvent event) {
		txtUnit.setText(new Item(sale.getCompany(),txtItem.getText()).getUnit());
	}
	
	@FXML
	void btnPick_OnClick(ActionEvent event) {
		SaleVolume sv = new SaleVolume(sale.getCompany(), sale.getSale(), Long.parseLong(txtVolume.getText()));
		sv.setLocation(new StockVolume(sale.getCompany(), Long.parseLong(txtVolume.getText())).getLocation());
		sv.setWarehouse(sale.getWarehouse());
		
		SaleVolumeItem svi = new SaleVolumeItem(sale.getCompany(), sale.getSale(), Long.parseLong(txtVolume.getText()), txtItem.getText());
		svi.setQuantity(pickItem.getQuantity());
		svi.setUnit(txtUnit.getText());
		svi.setConfirmedQuantity(Double.parseDouble(txtQuantity.getText()));
		sv.save();
		svi.save();
		pktlist.remove(pickItem);
	}
	
	@FXML
	void btnFinalize_OnClick(ActionEvent event) {
		if(sale.executeSale())
			Utilities.alert(AlertType.INFORMATION, "The sale has been completed.");
	}
	
	public void putPlaceHolders() {
		txtVolume.setPromptText(Long.toString(pickItem.getVolume().getVolume()));
		txtItem.setPromptText(pickItem.getItem());
		txtQuantity.setPromptText(Double.toString(pickItem.getQuantity()));
	}
	
}
