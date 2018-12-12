package com.stockmanager.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.stockmanager.model.Company;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.model.User;
import com.stockmanager.model.UserPermission;
import com.stockmanager.model.Warehouse;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DialogPurchaseController implements Controller {

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
				cbWarehouse.setDisable(false);
			case "Closed":
				btnClose.setVisible(false);
				btnReceive.setVisible(false);
				txtSupplier.setDisable(false);
				cbWarehouse.setDisable(false);
				dtPckrDate.setDisable(false);
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
		purchase.setState("Receiving");
		purchase.save();
		Utilities.openDialog("DialogPurchaseItemReceivingView", new DialogPurchaseItemReceivingController(purchase, 0));
		for(PurchaseItem pi : purchase.getItems()) {
			pi.setState("Receiving");
			pi.save();
		}
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
	void btnClose_OnClick() {
		
	}
	
	@FXML
	public void btnBack_OnClick() {
		checkPermissions(null);
		MainController.getInstance().changeView("PurchaseView");
	}

	@FXML
	public void btnCancel_OnClick() {
		btnBack_OnClick();
	}

	@Override
	public void checkPermissions(HashMap<String, Boolean> permissions) {
		System.out.println(new User("Vasco").getPermissions());
	}
}
