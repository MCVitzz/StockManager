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

	public static ArrayList<PickItem> getAllPickingItems(Sale sale) {
		ArrayList<PickItem> pickingList= new ArrayList<>();
		double saleqnt = 0;

		try {
			for(SaleItem si : sale.getItems()) {
				ResultSet rs = Database.select("SELECT SVI.Volume, SVI.Item, SVI.Quantity"
						+ "FROM stockvolumeitem AS SVI "
						+ "INNER JOIN saleitem AS SI ON SVI.Company = SI.Company AND SVI.Item = SI.Item "
						+ "WHERE SI.Company = '" + sale.getCompany() + "' AND SI.Sale = " + sale.getSale() + " GROUP BY SVI.Company, SVI.Item, SVI.Volume;");
				saleqnt = Double.parseDouble(Database.simpleSelect("Quantity", "saleitem", "Company = '" + sale.getCompany() + "' AND Sale = " + sale.getSale() + " AND Item = '" + rs.getString("Item") + "'"));
				while(rs.next()) {
					saleqnt -= rs.getDouble("Quantity");
					pickingList.add(new PickItem(new StockVolumeItem(si.getCompany(), rs.getLong("Volume"), si.getItem()), rs.getDouble("Quantity")));
					new StockVolume(si.getCompany(), rs.getLong("Volume"));
					if(saleqnt < 0) {
						
					}
				}
			}
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