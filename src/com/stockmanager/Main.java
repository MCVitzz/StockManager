package com.stockmanager;

import com.stockmanager.model.Database;
import com.stockmanager.utils.Utilities;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Utilities.openScene("LoginView", null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Database.connect();
		launch(args);
	}
}
