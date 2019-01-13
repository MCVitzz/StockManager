package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.stockmanager.utils.Utilities;

public class SaleVolumeItem extends DatabaseObject {
	private String company;
	private int  sale;
	private long volume;
	private String item;
	private double quantity;
	private double confirmedQuantity;
	private String unit;

	public SaleVolumeItem(String company, int sale, long volume, String item) {
		this.company = company;
		this.sale = sale;
		this.volume = volume;
		this.item = item;
		ResultSet rs = Database.select("SELECT * FROM salevolumeitem WHERE Company= '" + company + "' AND Sale = '" + sale + "' AND Volume = '" + volume + "' AND Item = '" + item + "'");
		try {
			while(rs.next()) {
				this.quantity = rs.getDouble("Quantity");
				this.confirmedQuantity = rs.getDouble("ConfirmedQuantity");
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

	public double getConfirmedQuantity() {
		return confirmedQuantity;
	}

	public void setConfirmedQuantity(double confirmedQuantity) {
		this.confirmedQuantity = confirmedQuantity;
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


	public int getSale() {
		return sale;
	}


	public long getVolume() {
		return volume;
	}


	public String getItem() {
		return item;
	}


	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO saleVolumeItem (Company, Sale, Volume, Item, Quantity, Unit) VALUES ('" + company + "', '" + sale + "', '" + volume + "', '" + item + "', '" + quantity + "', '" + unit + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE saleVolumeItem SET Quantity = '" + quantity + "', Unit = '" + unit + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Sale", "saleVolumeItem", "Company = '" + company + "' AND Sale = '" + sale + "' AND Volume = '" + volume + "' AND Item = '" + item + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM saleVolumeItem WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Volume '" + volume + "' AND Item = '" + item + "'");
	}

	@Override
	protected boolean validate() {
		return (sale != 0 && !Utilities.stringIsEmpty(company) && 0 != volume && !Utilities.stringIsEmpty(item));
	}

}
