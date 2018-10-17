package com.stockmanager.controllers;
import com.stockmanager.model.Item;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class ItemController {

	@FXML
    private TableView<Item> tblItem;

    @FXML
    private TableColumn<Item, String> clmnItem;

    @FXML
    private TableColumn<Item, String> clmnName;

    @FXML
    private TableColumn<Item, String> clmnUnit;

	public void initialize() {
		clmnItem.setCellValueFactory(new PropertyValueFactory<Item, String>("item"));
		clmnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		clmnUnit.setCellValueFactory(new PropertyValueFactory<Item, String>("unit"));

		tblItem.setItems(FXCollections.observableArrayList(Item.getAll()));

	}
    
	@FXML
	public void tblItem_OnClick(MouseEvent e) {
		if (tblItem.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2)
			Utilities.openDialog("DialogItemView", new DialogItemController(tblItem.getSelectionModel().getSelectedItem().getItem(), this));
		
	}
	
	@FXML
	public void btnAddItem_OnClick() {
		Utilities.openDialog("DialogItemView", new DialogItemController(this));
	}
	
	@FXML
	public void btnRemoveItem_OnClick() {
		if (tblItem.getSelectionModel().getSelectedItem() != null) {
			if(Utilities.confirmDialog("Are you sure you want to permanently remove this item" + tblItem.getSelectionModel().getSelectedItem().getItem() + "?")) {
				new Item(tblItem.getSelectionModel().getSelectedItem().getItem()).delete();
				initialize();
			}
		}
	}
	
}
