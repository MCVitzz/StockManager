package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class SaleVolume extends DatabaseObject {

	private String company;
	private int sale;
	private long volume;
	private String warehouse;
	private String location;
	
	public SaleVolume(String company, int sale, long volume) {
		this.company = company;
		this.sale = sale;
		this.volume = volume;
		try {
			ResultSet rs = Database.select("SELECT * FROM saleVolume WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Volume = '" + volume + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.location = rs.getString("Location");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public SaleVolumeItem makeItem(String item, double quantity, String unit) {
		SaleVolumeItem svi = new SaleVolumeItem(company, sale, volume, item);
		svi.setQuantity(quantity);
		svi.setUnit(unit);
		return svi;
	}
	
	public ArrayList<SaleVolumeItem> getItems() {
		ResultSet rs = Database.select("SELECT Item FROM saleVolumeItem WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Volume = '" + volume + "'");
		ArrayList<SaleVolumeItem> items = new ArrayList<SaleVolumeItem>();
		try {
			while(rs.next())
				items.add(new SaleVolumeItem(company, sale, volume, rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return items;
	}
	
	
	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public int getSale() {
		return sale;
	}

	public long getVolume() {
		return volume;
	}

	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO saleVolume (Company, Sale, Volume, Warehouse, Location) VALUES ('" + company + "', '" + sale + "', '" + volume + "', '" + warehouse + "', '" + location + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE saleVolume SET Warehouse = '" + warehouse + "', Location = '" + location + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Sale", "saleVolume", "Company = '" + company + "' AND Sale = '" + sale + "' AND Volume = '" + volume + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM saleVolume WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Volume '" + volume + "'");
	}

	@Override
	protected boolean validate() {
		return (sale != 0 && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(location));
	

}
}