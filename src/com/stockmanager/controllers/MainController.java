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
	    private PieChart pieChart;

    
    public MainController() {
    	showPieChart();
    }
    
    void showPieChart() {
    	ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Sunday", 30),
                    new PieChart.Data("Monday", 45),
                    new PieChart.Data("Tuesday", 70),
                    new PieChart.Data("Wednesday", 97),
                    new PieChart.Data("Thursday", 100),
                    new PieChart.Data("Friday", 80),
                    new PieChart.Data("Saturday", 10));
         
        pieChart.setTitle("Weekly Record");
        pieChart.setData(pieChartData);
    	}
	}

