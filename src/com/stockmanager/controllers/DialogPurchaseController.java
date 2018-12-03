package com.stockmanager.controllers;

import com.stockmanager.model.Company;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.Warehouse;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogPurchaseController {

	private Purchase purchase;
	
	private PurchaseController purchaseController;

	@FXML
	private ComboBox<Company> cbCompany;

	@FXML
	private TextField txtPurchase;

	@FXML
	private ComboBox<Warehouse> cbWarehouse;

	@FXML
	private DatePicker dtPckrDate;

	@FXML
	private TextField txtSupplier;


	@FXML
	private TextField txtState;
	
	@FXML
	private Button btnReceive;

	@FXML
	private Button btnClose;

	@FXML
	private VBox vbPurchaseItem;

	public DialogPurchaseController(PurchaseController purchaseController, Purchase purchase) {
		this.purchase = purchase;
		this.purchaseController = purchaseController;
	}

	public void initialize() {
		Utilities.fillCompanies(cbCompany);
		txtPurchase.setDisable(true);
		txtState.setDisable(true);

		if(purchase != null) {
			cbCompany.setDisable(true);
			cbCompany.setValue(new Company(purchase.getCompany()));
			txtPurchase.setText(Integer.toString(purchase.getPurchase()));
			Utilities.fillWarehouses(cbWarehouse, purchase.getCompany());
			cbWarehouse.setValue(new Warehouse(purchase.getCompany(), purchase.getWarehouse()));
			dtPckrDate.setValue(purchase.getDate());
			txtSupplier.setText(purchase.getSupplier());
			txtState.setText(purchase.getState());
			putPag();
			switch(purchase.getState()) {
			case "Open":
				btnClose.setVisible(false);
				btnReceive.setVisible(true);
				break;
			case "Receiving":
				btnClose.setVisible(true);
				btnReceive.setVisible(false);
			case "Closed":
				btnClose.setVisible(false);
				btnReceive.setVisible(false);
			}
				
		}
		else {
			txtState.setText("Open");
			vbPurchaseItem.setVisible(false);
			btnClose.setVisible(false);
			btnReceive.setVisible(false);
		}

	}

	@FXML
	public void btnReceive_OnClick() {
		
	}
	
	@FXML
	public void btnSave_OnClick() {
		Purchase prc;
		if(Utilities.stringIsEmpty(txtPurchase.getText()))
			prc = new Purchase(cbCompany.getValue().getCompany());
		else
			prc = new Purchase(cbCompany.getValue().getCompany(), Integer.parseInt(txtPurchase.getText()));
		prc.setDate(dtPckrDate.getValue());
		prc.setWarehouse(cbWarehouse.getValue().getWarehouse());
		prc.setSupplier(txtSupplier.getText());
		prc.setState(txtState.getText());
		prc.save();
		purchaseController.initialize();
		btnCancel_OnClick();
	}
	
	@FXML
	public void cbCompany_OnAction() {
		Utilities.fillWarehouses(cbWarehouse, cbCompany.getValue().getCompany());
	}
	
	private void putPag() {
		vbPurchaseItem.getChildren().clear();
		vbPurchaseItem.getChildren().add(0, Utilities.getNode("PurchaseItemView", new PurchaseItemController(purchase)));
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtPurchase.getScene().getWindow()).close();
	}
}
