package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PickItem {

	
	private StockVolumeItem volume;
	private String item;
	private double quantity;
	
	public PickItem(StockVolumeItem volume, double quantity) {
		this.volume = volume;
		this.quantity = quantity;		
	}

	public static ArrayList<PickItem> getAllPickingItems() {
		ArrayList<PickItem> pickingList= new ArrayList<>();
		
		try {
			ResultSet rs = Database.select("SELECT SVI.Company, SVI.Volume, SI.Item, SI.Quantity FROM stockvolumeitem AS SVI INNER JOIN saleitem AS SI ON SVI.Company=SI.Company AND SVI.Item=SI.Item");
			
			while (rs.next()) 
				pickingList.add(new PickItem(new StockVolumeItem(rs.getString("SVI.Company"),rs.getLong("SVI.Volume"),rs.getString("SVI.Item")),rs.getDouble("SI.Quantity")));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pickingList;
	}

	public StockVolumeItem getVolume() {
		return volume;
	}

	public String getItem() {
		return item;
	}

	public double getQuantity() {
		return quantity;
	}
	
	
	
}