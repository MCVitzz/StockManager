package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class PurchaseVolume extends DatabaseObject {

	private String company;
	private int purchase;
	private long volume;
	private String warehouse;
	private String location;
	
	public PurchaseVolume(String company, int purchase, long volume) {
		this.company = company;
		this.purchase = purchase;
		this.volume = volume;
		try {
			ResultSet rs = Database.select("SELECT * FROM purchaseVolume WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.location = rs.getString("Location");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PurchaseVolumeItem makeItem(String item, double quantity, String unit) {
		PurchaseVolumeItem pvi = new PurchaseVolumeItem(company, purchase, volume, item);
		pvi.setQuantity(quantity);
		pvi.setUnit(unit);
		pvi.save();
		return pvi;
	}
	
	public ArrayList<PurchaseVolumeItem> getItems() {
		ResultSet rs = Database.select("SELECT Item FROM purchaseVolumeItem WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "'");
		ArrayList<PurchaseVolumeItem> items = new ArrayList<PurchaseVolumeItem>();
		try {
			while(rs.next())
				items.add(new PurchaseVolumeItem(company, purchase, volume, rs.getString("Item")));
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

	public int getPurchase() {
		return purchase;
	}

	public long getVolume() {
		return volume;
	}

	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO purchaseVolume (Company, Purchase, Volume, Warehouse, Location) VALUES ('" + company + "', '" + purchase + "', '" + volume + "', '" + warehouse + "', '" + location + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE purchaseVolume SET Warehouse = '" + warehouse + "', Location = '" + location + "' WHERE Company= '" + company + "' AND Purchase = '"+ purchase + "' AND Volume ='"+ volume+ "';");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Purchase", "purchaseVolume", "Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM purchaseVolume WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume '" + volume + "'");
	}

	@Override
	protected boolean validate() {
		return (purchase != 0 && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(location));
	}

}
