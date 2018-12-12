package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PickItem {


	private StockVolumeItem volume;
	private double quantity;
<<<<<<< HEAD
	
=======
>>>>>>> master


	public PickItem(StockVolumeItem volume, double quantity) {
		this.volume = volume;
		this.quantity = quantity;		
	}

	public static ArrayList<PickItem> getAllPickingItems(Sale sale) {
		ArrayList<PickItem> pickingList= new ArrayList<>();
		double saleqnt = 0;

		try {
			for(SaleItem si : sale.getItems()) {
<<<<<<< HEAD
				ResultSet rs = Database.select("SELECT SVI.Company, SVI.Volume, SVI.Item, SVI.Quantity "
						+ "FROM stockvolumeitem AS SVI "
						+ "INNER JOIN saleitem AS SI ON SVI.Company = SI.Company AND SVI.Item = SI.Item "
						+ "WHERE SI.Company = '" + sale.getCompany() + "' AND SI.Sale = " + sale.getSale() + " GROUP BY SVI.Company, SVI.Item, SVI.Volume;");
				saleqnt = si.getQuantity();
				while(rs.next()) {
					saleqnt -= rs.getDouble("Quantity");
					PickItem pck = new PickItem(new StockVolumeItem(si.getCompany(), rs.getLong("Volume"), si.getItem()), rs.getDouble("Quantity"));
					SaleVolume sv = new SaleVolume(si.getCompany(), si.getSale(), rs.getLong("Volume"));
					StockVolume stv= new StockVolume(si.getCompany(), rs.getLong("Volume"));
					sv.setLocation(stv.getLocation());
					sv.setWarehouse(sale.getWarehouse());
					SaleVolumeItem svi = new SaleVolumeItem(si.getCompany(), si.getSale(), rs.getLong("Volume"), si.getItem());
					svi.setQuantity(rs.getLong("Quantity"));
					svi.setUnit(si.getUnit());
					
					sv.save();
					svi.save();
					
					if(saleqnt < 0) {
						pck.setQuantity(rs.getDouble("Quantity")+saleqnt);
						svi.setQuantity(rs.getDouble("Quantity")+saleqnt);
					}
					
					pickingList.add(pck);
=======
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
>>>>>>> master
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
		return volume.getItem();
	}

	public double getQuantity() {
		return quantity;
	}
<<<<<<< HEAD
	
	public void setQuantity(double quantity) {
		this.quantity= quantity;
	}
=======
>>>>>>> master



}