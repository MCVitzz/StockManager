package com.stockmanager.controllers;

import com.stockmanager.model.Company;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogCompanyController {

	private Company company;

	@FXML
	private TextField txtCompany;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtCountry;

	private CompanyController companyController;



	public DialogCompanyController(CompanyController companyController) {
		this.companyController = companyController;
	}

	public DialogCompanyController(String company, CompanyController companyController) {
		this.companyController = companyController;
		this.company = new Company(company);
	}

	public void initialize() {
		if(company != null) {
			txtCompany.setDisable(true);
			txtCompany.setText(company.getCompany());
			txtAddress.setText(company.getAddress());
			txtCountry.setText(company.getCountry());
		}
	}

	@FXML
	public void btnSave_OnClick() {
		Company cpy = new Company();
		cpy.setCompany(txtCompany.getText());
		cpy.setAddress(txtAddress.getText());
		cpy.setCountry(txtCountry.getText());
		cpy.save();
		companyController.initialize();
		btnCancel_OnClick();
	}

	@FXML
	public void btnCancel_OnClick() {
		((Stage)txtCompany.getScene().getWindow()).close();
	}
}
