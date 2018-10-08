package com.stockmanager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

public class MainController {
	
	@FXML
    private VBox mainVerticalBox;


    @FXML
    private PieChart mainPieChart;

    
    public MainController() {
    	showPieChart();
    }
    
    void showPieChart() {
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Iphone 5S", 13), 
            new PieChart.Data("Samsung Grand", 25), 
            new PieChart.Data("MOTO G", 10), 
            new PieChart.Data("Nokia Lumia", 22)); 
    
    mainPieChart.setData(pieChartData);
    	}
	}

