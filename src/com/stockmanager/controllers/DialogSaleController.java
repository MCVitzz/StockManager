package com.stockmanager.controllers;

import com.stockmanager.model.Company;
import com.stockmanager.model.Sale;
import com.stockmanager.model.Warehouse;
import com.stockmanager.utils.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogSaleController {

	private Sale sale;
	
	private SaleController saleController;

	@FXML
	private ComboBox<Company> cbCompany;

	@FXML
	private TextField txtSale;

	@FXML
	private ComboBox<Warehouse> cbWarehouse;

	@FXML
	private DatePicker dtPckrDate;

	@FXML
	private TextField txtClient;

	@FXML
	private TextField txtState;
	
	@FXML
	private Button btnPick;

	@FXML
	private VBox vbSaleItem;

	public DialogSaleController(SaleController saleController, Sale sale) {
		this.sale = sale;
		this.saleController = saleController;
	}

	public void initialize() {
		Utilities.fillCompanies(cbCompany);
		txtSale.setDisable(true);
		txtState.setDisable(true);

		if(sale != null) {
			cbCompany.setDisable(true);
			cbCompany.setValue(new Company(sale.getCompany()));
			txtSale.setText(Integer.toString(sale.getSale()));
			Utilities.fillWarehouses(cbWarehouse, sale.getCompany());
			cbWarehouse.setValue(new Warehouse(sale.getCompany(), sale.getWarehouse()));
			dtPckrDate.setValue(sale.getDate());
			txtClient.setText(sale.getClient());
			txtState.setText(sale.getState());
			putPag();
			switch(sale.getState()) {
			case "Open":
				
				btnPick.setVisible(true);
				break;
			case "Picking":
				
				btnPick.setVisible(false);
				cbWarehouse.setDisable(false);
			case "Closed":
				
				btnPick.setVisible(false);
				txtClient.setDisable(false);
				cbWarehouse.setDisable(false);
				dtPckrDate.setDisable(false);
			}
		}
		else {
			txtState.setText("Open");
			vbSaleItem.setVisible(false);
			btnPick.setVisible(false);
		}

	}

	@FXML
	public void btnSave_OnClick() {
		Sale prc;
		if(Utilities.stringIsEmpty(txtSale.getText()))
			prc = new Sale(cbCompany.getValue().getCompany());
		else
			prc = new Sale(cbCompany.getValue().getCompany(), Integer.parseInt(txtSale.getText()));
		prc.setDate(dtPckrDate.getValue());
		prc.setWarehouse(cbWarehouse.getValue().getWarehouse());
		prc.setClient(txtClient.getText());
		prc.setState(txtState.getText());
		prc.save();
		saleController.initialize();
		btnCancel_OnClick();
	}
	
	@FXML
	public void btnPick_OnAction() {
		Utilities.openDialog("DialogSaleItemPickView",new DialogSaleItemPickController());
	}
	
	@FXML
	public void cbCompany_OnAction() {
		Utilities.fillWarehouses(cbWarehouse, cbCompany.getValue().getCompany());
	}
	
	private void putPag() {
		vbSaleItem.getChildren().clear();
		vbSaleItem.getChildren().add(0, Utilities.getNode("SaleItemView", new SaleItemController(sale)));
	}
	
	@FXML
	public void btnBack_OnClick() {
		MainController.getInstance().changeView("SaleView");
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtSale.getScene().getWindow()).close();
	}
}
