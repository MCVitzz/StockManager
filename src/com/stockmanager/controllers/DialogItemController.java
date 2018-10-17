package com.stockmanager.controllers;

import com.stockmanager.model.Item;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogItemController {

	private Item item;

	@FXML
	private TextField txtItem;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtUnit;

	private ItemController itemController;



	public DialogItemController(ItemController itemController) {
		this.itemController = itemController;
	}

	public DialogItemController(String item, ItemController itemController) {
		this.itemController = itemController;
		this.item = new Item(item);
	}

	public void initialize() {
		if(item != null) {
			txtItem.setDisable(true);
			txtItem.setText(item.getItem());
			txtName.setText(item.getName());
			txtUnit.setText(item.getUnit());
		}
	}

	@FXML
	public void btnItemSave_OnClick() {
		Item itm = new Item("");
		itm.setItem(txtItem.getText());
		itm.setName(txtName.getText());
		itm.setUnit(txtUnit.getText());
		itm.save();
		itemController.initialize();
		btnItemCancel_OnClick();
	}

	@FXML
	public void btnItemCancel_OnClick() {
		((Stage)txtItem.getScene().getWindow()).close();
	}
}
