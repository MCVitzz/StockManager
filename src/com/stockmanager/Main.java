package com.stockmanager;

import com.stockmanager.controllers.MainController;
import com.stockmanager.model.Database;
import com.stockmanager.utils.Utilities;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	public void start(Stage primaryStage) {
		Utilities.openDialog("MainView", MainController.getInstance());
		
	}
	
	public static void main(String[] args) {
		Database.connect();
		launch(args);
	}
}
