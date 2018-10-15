package com.stockmanager.controllers;

import com.stockmanager.model.Company;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    void btnAddCompany_OnClick(ActionEvent event) {
    	
    }

    @FXML
    void btnRemoveCompany_OnClick(ActionEvent event) {
    	
    }

    @FXML
    void tblCompany_OnClick(MouseEvent event) {
    	
    }

}