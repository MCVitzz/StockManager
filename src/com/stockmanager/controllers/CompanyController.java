package com.stockmanager.controllers;

import com.stockmanager.model.Company;
import com.stockmanager.utils.Utilities;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CompanyController {

    @FXML
    private TableView<Company> tblCompany;

    @FXML
    private TableColumn<Company, String> clmnCompany;

    @FXML
    private TableColumn<Company, String> clmnAddress;

    @FXML
    private TableColumn<Company, String> clmnCountry;

	public void initialize() {
		clmnCompany.setCellValueFactory(new PropertyValueFactory<Company, String>("company"));
		clmnAddress.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
		clmnCountry.setCellValueFactory(new PropertyValueFactory<Company, String>("country"));
		
		
		
		tblCompany.setItems(FXCollections.observableArrayList(Company.getAll()));
	}
    
	@FXML
	public void tblCompany_OnClick(MouseEvent e) {
		if (tblCompany.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2)
			Utilities.openDialog("DialogCompanyView", new DialogCompanyController(tblCompany.getSelectionModel().getSelectedItem().getCompany(), this));
		
	}
	
	@FXML
	public void btnAddCompany_OnClick() {
    	System.out.println("oi");
		Utilities.openDialog("DialogCompanyView", new DialogCompanyController(this));
	}
	
	@FXML
	public void btnRemoveCompany_OnClick() {
		if (tblCompany.getSelectionModel().getSelectedItem() != null) {
			if(Utilities.confirmDialog("Are you sure you want to permanently remove the company " + tblCompany.getSelectionModel().getSelectedItem().getCompany() + "?")) {
				new Company(tblCompany.getSelectionModel().getSelectedItem().getCompany()).delete();
				initialize();
			}
		}
	}

}