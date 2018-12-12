package com.stockmanager;

import com.stockmanager.controllers.LoginController;
import com.stockmanager.controllers.MainController;
import com.stockmanager.model.Database;
import com.stockmanager.model.User;
import com.stockmanager.utils.Utilities;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	public void start(Stage primaryStage) {
		Utilities.openDialog("LoginView", new LoginController());
		//MainController.getInstance(new User("Vasco"))
	}
	
	public static void main(String[] args) {
		Database.connect();
		launch(args);
	}
}
