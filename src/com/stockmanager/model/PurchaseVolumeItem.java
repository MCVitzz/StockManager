package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.stockmanager.utils.Utilities;

public class PurchaseVolumeItem extends DatabaseObject {
	private String company;
	private int  purchase;
	private long volume;
	private String item;
	private double quantity;
	private String unit;

	public PurchaseVolumeItem(String company, int purchase, long volume, String item) {
		this.company = company;
		this.purchase = purchase;
		this.volume = volume;
		this.item = item;
		ResultSet rs = Database.select("SELECT * FROM purchaseVolumeItem WHERE Company= '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "' AND Item = '" + Utilities.escape(item) + "'");
		try {
			while(rs.next()) {
				this.quantity = rs.getDouble("Quantity");
				this.unit = rs.getString("Unit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
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


	public String getItem() {
		return item;
	}

	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO purchaseVolumeItem (Company, Purchase, Volume, Item, Quantity, Unit) VALUES ('" + Utilities.escape(company) + "', '" + purchase + "', '" + volume + "', '" + Utilities.escape(item) + "', '" + quantity + "', '" + Utilities.escape(unit) + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE purchaseVolumeItem SET Quantity = '" + quantity + "', Unit = '" + Utilities.escape(unit) + "' WHERE Company = '"+ Utilities.escape(company) + "' AND Purchase ='" + purchase + "' AND Volume ='" + volume + "' AND Item='"+ Utilities.escape(item) +"';");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Purchase", "purchaseVolumeItem", "Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "' AND Item = '" + Utilities.escape(item) + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM purchaseVolumeItem WHERE Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "' AND Volume '" + volume + "' AND Item = '" + Utilities.escape(item) + "'");
	}

	@Override
	protected boolean validate() {
		return (purchase != 0 && !Utilities.stringIsEmpty(company) && 0 != volume && !Utilities.stringIsEmpty(item));
	}
}
