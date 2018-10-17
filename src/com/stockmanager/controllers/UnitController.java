package com.stockmanager.controllers;
 
import com.stockmanager.utils.Utilities;
import com.stockmanager.model.Unit;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class UnitController {

	@FXML
	private TableView<Unit> tblUnit;
	
    @FXML
    private TableColumn<Unit, String> clmUnit;

    @FXML
    private TableColumn<Unit, String> clmName;
    
    public void initialize() {
	
		clmUnit.setCellValueFactory(new PropertyValueFactory<Unit, String>("Unit"));
		clmName.setCellValueFactory(new PropertyValueFactory<Unit, String>("Name"));

		tblUnit.setItems(FXCollections.observableArrayList(Unit.getAll()));
	}

    @FXML
	public void tblUnit_OnClick(MouseEvent e) {
		if (tblUnit.getSelectionModel().getSelectedItem() != null && e.getClickCount() == 2)
			Utilities.openDialog("DialogUnitView", new DialogUnitController(tblUnit.getSelectionModel().getSelectedItem().getUnit(), this));
		
	}
	
	@FXML
	public void addUnitBtn_OnClick() {
		Utilities.openDialog("DialogUnitView", new DialogUnitController(this));
	}
	
	@FXML
	public void removeUnitBtn_OnClick() {
		if (tblUnit.getSelectionModel().getSelectedItem() != null) {
			if(Utilities.confirmDialog("Are you sure you want to permanently remove this unit " + tblUnit.getSelectionModel().getSelectedItem().getUnit() + "?")) {
				new Unit(tblUnit.getSelectionModel().getSelectedItem().getUnit()).delete();
				initialize();
			}
		}
	}
}
