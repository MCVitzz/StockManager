package com.stockmanager.utils;

import java.io.IOException;
import java.net.URL;

import com.stockmanager.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
		Stage newWindow = new Stage();
		Node node = getNode(sceneName);
		Scene scene = new Scene((Parent)node);
		newWindow.setScene(scene);
		newWindow.show();
		if(oldWindow != null)
			((Stage)oldWindow).close();
	}

	public static Node getNode(String name) {
		Node node = null;
		try {
			URL path = Main.class.getResource("views/" + name +".fxml");
			node = FXMLLoader.load(path);
		} catch (IOException e) {
			warn("Problem opening Scene");
			e.printStackTrace();
		}
		return node;
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
