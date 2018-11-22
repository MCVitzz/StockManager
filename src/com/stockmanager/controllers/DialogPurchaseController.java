package com.stockmanager.controllers;

import java.time.ZoneId;

import com.stockmanager.model.Company;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.Warehouse;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogPurchaseController {

	private Purchase purchase;

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
	private VBox vbPurchaseItem;

	public DialogPurchaseController(String company, int purchase) {
		this.purchase = new Purchase(company, purchase);
	}

	public void initialize() {
		Utilities.fillCompanies(cbCompany);
		txtPurchase.setDisable(true);

		if(purchase != null) {
			cbCompany.setDisable(true);
			cbCompany.setValue(new Company(purchase.getCompany()));
			txtPurchase.setText(Integer.toString(purchase.getPurchase()));
			Utilities.fillWarehouses(cbWarehouse, purchase.getCompany());
			cbWarehouse.setValue(new Warehouse(purchase.getCompany(), purchase.getWarehouse()));
			dtPckrDate.setValue(purchase.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			txtSupplier.setText(purchase.getSupplier());
			txtState.setText(purchase.getState());
		}
		else {
			txtState.setText("Open");
			putPag();
		}

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
