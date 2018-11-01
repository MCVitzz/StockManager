package com.stockmanager.controllers;

import com.stockmanager.model.Item;
import com.stockmanager.model.Unit;
import com.stockmanager.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogItemController {

	private Item item;

	
	@FXML
	private ComboBox<Company> cbCompany;
	
	@FXML
	private TextField txtItem;

	@FXML
	private TextField txtName;

	@FXML
	private ComboBox<Unit> cbUnit;

	private ItemController itemController;



	public DialogItemController(ItemController itemController) {
		this.itemController = itemController;
	}

	public DialogItemController(String company, String item, ItemController itemController) {
		this.itemController = itemController;
		this.item = new Item(company, item);
	}

	public void initialize() {
		cbCompany.getItems().clear();
		cbCompany.getItems().addAll(Company.getAll());
		
		cbUnit.getItems().clear();
		
		
		if(item != null) {
			txtItem.setDisable(true);
			txtName.setDisable(true);
			
			fillUnit(item.getCompany());
			
			cbCompany.setValue(new Company(item.getCompany()));
			cbUnit.setValue(new Unit(item.getCompany(),item.getUnit()));
			txtItem.setText(item.getItem());
			txtName.setText(item.getName());
			
		}
	}

	@FXML
	public void btnItemSave_OnClick() {
		Item itm = new Item(cbCompany.getValue().getCompany(), txtItem.getText());
		
		itm.setName(txtName.getText());
		itm.setUnit(cbUnit.getValue().getUnit());
		itm.save();
		itemController.initialize();
		btnItemCancel_OnClick();
	}

	@FXML
	public void cbCompany_OnAction() {
		fillUnit(cbCompany.getValue().getCompany());
	}
	
	
	public void fillUnit(String company) {
		cbUnit.getItems().clear();
		cbUnit.getItems().addAll(Unit.getAllUnitsFromCompany(company));
	}

	@FXML
	public void btnItemCancel_OnClick() {
		((Stage)txtItem.getScene().getWindow()).close();
	}
}
