package com.stockmanager.utils;

import java.io.IOException;
import java.net.URL;

import com.stockmanager.Main;
import com.stockmanager.model.Company;
import com.stockmanager.model.Warehouse;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Utilities {

	public static boolean stringIsEmpty(String str) {
		return str == null || str.equals("");
	}
	
	public static void allowOnlyNumbers(TextField txt) {
		txt.textProperty().addListener(new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*"))
		            txt.setText(newValue.replaceAll("[^\\d]", ""));
		    }
		});
	}

	public static void fillCompanies(ComboBox<Company> cbCompany) {
		cbCompany.getItems().clear();
		cbCompany.getItems().addAll(Company.getAll());
	}

	public static void fillWarehouses(ComboBox<Warehouse> cbWarehouse, String company) {
		cbWarehouse.getItems().clear();
		cbWarehouse.getItems().addAll(Warehouse.getAllWarehousesFromCompany(company));
	}
	
	public static void openScene(String sceneName, Window oldWindow) {
		Stage newWindow = new Stage();
		Node node = getNode(sceneName);
		Scene scene = new Scene((Parent)node);
		newWindow.getIcons().add(new Image("file://../Images/logo.png"));
		newWindow.setScene(scene);
		newWindow.setResizable(false);
		newWindow.show();
		if(oldWindow != null)
			((Stage)oldWindow).close();
	}
	
	public static void openScene(String sceneName, Window oldWindow, Object obj) {
		Stage newWindow = new Stage();
		Node node = getNode(sceneName, obj);
		Scene scene = new Scene((Parent)node);
		newWindow.getIcons().add(new Image("file://../Images/logo.png"));
		newWindow.setScene(scene);
		newWindow.setResizable(false);
		newWindow.show();
		if(oldWindow != null)
			((Stage)oldWindow).close();
	}
	
	public static void openDialog(String sceneName, Object obj) {
		Stage newWindow = new Stage();
		Node node = getNode(sceneName, obj);
		Scene scene = new Scene((Parent)node);
		newWindow.setResizable(false);
		newWindow.getIcons().add(new Image("file://../Images/logo.png"));
		newWindow.setScene(scene);
		newWindow.show();
	}
	
	public static Node getNode(String name, Object obj) {
		Node node = null;
		try {
			URL path = Main.class.getResource("views/" + name +".fxml");
			FXMLLoader loader = new FXMLLoader(path);
			loader.setController(obj);
			node = loader.load();
		} catch (IOException e) {
			warn("Problem opening Scene");
			e.printStackTrace();
		}
		return node;
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

	public static boolean confirmDialog(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		return alert.getResult() == ButtonType.YES;
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
