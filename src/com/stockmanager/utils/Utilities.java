package com.stockmanager.utils;

import java.io.IOException;
import java.net.URL;

import com.stockmanager.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Utilities {

	public static boolean stringIsEmpty(String str) {
		return str == null || str == "";
	}

	public static void openScene(String sceneName, Window oldWindow) {
		try {
			Stage newWindow = new Stage();
			URL path = Main.class.getResource("views/" + sceneName +".fxml");
			FXMLLoader loader = new FXMLLoader(path);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			newWindow.setScene(scene);
			Window mainWindow = oldWindow;
			newWindow.show();
			((Stage)mainWindow).close();
		}
		catch(IOException e) {
			warn("Problem opening Scene");
		}
	}
	
	public static void warn(String message) {
		alert(AlertType.WARNING, message);
	}
	
	public static void alert(AlertType type, String message) {
		Alert alert = new Alert(type);
		alert.setHeaderText(message);
		alert.show();
	}
}
