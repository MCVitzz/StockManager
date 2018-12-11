package com.stockmanager.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.stockmanager.model.User;
import com.stockmanager.model.UserPermission;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController implements Controller {

	private static MainController instance;
	
	@FXML
	private VBox mainVerticalBox;
	

    @FXML
    private Label lblTitle;
    
    @FXML
    private Button btnWarehouse;


    private MainController() {}
    
    public static MainController getInstance() {
    	if(instance == null)
    		instance = new MainController();
    	return instance;
    }
    
	public void initialize() {
		changeView("DashboardView", "Dashboard");
		checkPermissions(new User("Vasco").getPermissions());
	}

	@FXML
	void dashboardButton(ActionEvent event) {
		changeView("DashboardView", "Dashboard");
	}

	@FXML
	void stockButton(ActionEvent event) {
		changeView("StockVolumeView", "Stock");
	}

	@FXML
	void salesOrderButton_OnClick(ActionEvent event) {
		changeView("SalesOrderView", "Sales Order");
	}

	@FXML
	void purchaseButton_OnClick(ActionEvent event) {
		changeView("PurchaseView", "Purchases");
	}
	
	@FXML
	void saleButton_OnClick(ActionEvent event) {
		changeView("SaleView", "Sales");
	}
	@FXML
	void warehouseButton_OnClick() {
		changeView("WarehouseView", "Warehouses");
	}
	
	@FXML
	void userButton_OnClick(ActionEvent event) {
		changeView("UserView", "Users");
	}
	
	@FXML
	void companyButton_OnClick() {
		changeView("CompanyView", "Companies");
	}
	
	@FXML
	void itemButton_OnClick() {
		changeView("ItemView", "Items");
	}
	
	@FXML
	void unitButton_OnClick() {
		changeView("UnitView", "Units");
	}
	
	@FXML
	void locationButton_OnClick() {
		changeView("LocationView", "Locations");
	}
	
	@FXML
	void btnLogout_OnClick() {
		Utilities.openScene("LoginView", lblTitle.getScene().getWindow());
	}
	
	public void changeView(String newView, String title) {
		lblTitle.setText(title);
		mainVerticalBox.getChildren().clear();
		mainVerticalBox.getChildren().add(0, Utilities.getNode(newView));
	}
	
	public void changeView(String newView) {
		mainVerticalBox.getChildren().clear();
		mainVerticalBox.getChildren().add(0, Utilities.getNode(newView));
	}
	
	public void changeView(Node newView) {
		mainVerticalBox.getChildren().clear();
		mainVerticalBox.getChildren().add(0, newView);
	}

	@Override
	public void checkPermissions(HashMap<String, Boolean> permissions) {
		btnWarehouse.setDisable(!permissions.get("Show Warehouse"));
	}
}