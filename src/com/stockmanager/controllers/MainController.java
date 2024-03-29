package com.stockmanager.controllers;

import java.util.HashMap;

import com.stockmanager.model.Menu;
import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainController implements Controller {

	private static MainController instance;

	private User user;

	@FXML
	private VBox vboxMenu;
	
	@FXML
	private VBox vboxSubmenu;

	@FXML
	private VBox mainVerticalBox;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblUser;

	@FXML
	private Button btnWarehouse;

	@FXML
	private Button btnCompanies;

	@FXML
	private Button btnStock;

	@FXML
	private Button btnPurchases;

	@FXML
	private Button btnSales;

	@FXML
	private Button btnReports;

	@FXML
	private Button btnItems;

	@FXML
	private Button btnUnits;

	@FXML
	private Button btnLocations;

	@FXML
	private Button btnUsers;

	private MainController(User user) {
		setUser(user);
	}

	public static MainController getInstance() {
		return instance;
	}

	public static MainController getInstance(User user) {
		if (instance == null)
			instance = new MainController(user);
		else {
			instance.setUser(user);
			instance.lblUser.setText(user.getUser());
			instance.createMenu();
		}
		return instance;
	}

	public void initialize() {
		changeView("DashboardView", "Dashboard");
		lblUser.setText(user.getUser());
		checkPermissions(user.getPermissions());
		createMenu();
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
		Utilities.openScene("LoginView", lblTitle.getScene().getWindow(), new LoginController());
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

	public User getUser() {
		return user;
	}

	private void setUser(User user) {
		this.user = user;
	}

	private void createMenu() {
		HashMap<String, Boolean> permissions = user.getPermissions();
		for(Menu menu : Menu.getAll()) {
			System.out.println(menu.getName());
			if(permissions.get(menu.getName()))
				createButton(menu);
		}
	}
	
	private void createButton(Menu menu) {
			Image buttonImage = new Image(menu.getImage());
			Button button = new Button(menu.getName(), new ImageView(buttonImage));
			button.getStyleClass().add(menu.getCssClass());
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					changeView(menu.getView() + "View", menu.getName());
			}});
			vboxSubmenu.getChildren().add(button);
	}

	@Override
	public void checkPermissions(HashMap<String, Boolean> permissions) {
	}
	
	
}





