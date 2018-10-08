package com.stockmanager.controllers;

import com.stockmanager.utils.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainController {

	@FXML
	private VBox mainVerticalBox;


	void initialize() {
		dashboardButton(null);
	}

	@FXML
	void dashboardButton(ActionEvent event) {
		mainVerticalBox.getChildren().clear();
		mainVerticalBox.getChildren().add(0, Utilities.getNode("DashboardView"));

	}

	@FXML
	void stockButton(ActionEvent event) {
		mainVerticalBox.getChildren().clear();
		mainVerticalBox.getChildren().add(0, Utilities.getNode("StockView"));
	}

}

