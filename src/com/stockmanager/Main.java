package com.stockmanager;

import com.stockmanager.controllers.LoginController;
import com.stockmanager.model.Database;
import com.stockmanager.utils.Utilities;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author André Oliveira
 * @author Francisco Cordeiro
 * @author Vasco Pereira
 *
 * This Application manages stock on any given warehouses. With dynamic buisness models.
 *
 */
public class Main extends Application {

	public void start(Stage primaryStage) {
		Utilities.openDialog("LoginView", new LoginController());
	}
	
	public static void main(String[] args) {
		Database.connect();
		launch(args);
	}
}
