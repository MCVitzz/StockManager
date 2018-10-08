package com.stockmanager;

	
import java.net.URL;

import com.stockmanager.controllers.LoginController;
import com.stockmanager.model.Database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			URL path = Main.class.
					getResource("views/LoginView.fxml");
			FXMLLoader loader = new FXMLLoader(path);
			loader.setController(new LoginController());
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Database.connect();
		launch(args);
	}
}
