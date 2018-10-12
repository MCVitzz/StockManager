package com.stockmanager.controllers;

import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController {

	@FXML
	private VBox mainVerticalBox;
	

    @FXML
    private Label lblTitle;


	public void initialize() {
		changeView("DashboardView", "Dashboard");
	}

	@FXML
	void dashboardButton(ActionEvent event) {
		changeView("DashboardView", "Dashboard");

	}

	@FXML
	void stockButton(ActionEvent event) {
		changeView("StockView", "Stock");
	}

	@FXML
	void userButton_OnClick(ActionEvent event) {
		changeView("UserView", "Users");
	}
	
	@FXML
	void btnLogout_OnClick() {
		Utilities.openScene("LoginView", lblTitle.getScene().getWindow());
	}
	
	private void changeView(String newView, String title) {
		lblTitle.setText(title);
		mainVerticalBox.getChildren().clear();
		mainVerticalBox.getChildren().add(0, Utilities.getNode(newView));
	}
	
	
}