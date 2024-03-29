package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;
 /***
  * 
  * Class does not represent a DatabaseObject
  * Manages all the picking action on the back-end
  *
  */
public class PickItem {


	private StockVolumeItem volume;
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
				ResultSet rs = Database.select("SELECT SVI.Company, SVI.Volume, SVI.Item, SVI.Quantity "
						+ "FROM stockvolumeitem AS SVI "
						+ "INNER JOIN saleitem AS SI ON SVI.Company = SI.Company AND SVI.Item = SI.Item "
						+ "WHERE SI.Company = '" + Utilities.escape(sale.getCompany()) + "' AND SI.Sale = " + sale.getSale() + " GROUP BY SVI.Company, SVI.Item, SVI.Volume;");
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
					svi.setConfirmedQuantity(0);
					svi.setUnit(si.getUnit());
					sv.save();
					svi.save();

					if(saleqnt < 0) {
						pck.setQuantity(rs.getDouble("Quantity")+saleqnt);
						svi.setQuantity(rs.getDouble("Quantity")+saleqnt);
					}

					pickingList.add(pck);
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
	
	public void setQuantity(double quantity) {
		this.quantity= quantity;
	}


}