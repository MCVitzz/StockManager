package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class StockVolumeItem extends DatabaseObject {

	private String company;
	private long volume;
	private String item;
	private double quantity;
	private String unit;

	public StockVolumeItem() {}

	public StockVolumeItem(String company, long volume, String item) {
		this.company = company;
		this.volume = volume;
		this.item = item;
		try {
			ResultSet rs = Database.select("SELECT * FROM stockVolumeItem WHERE Company = '" + company + "' AND Volume = '" + volume + "' AND Item = '" + item + "'");
			while (rs.next()) {
				this.quantity = rs.getFloat("Quantity");
				this.unit = rs.getString("Unit");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<StockVolumeItem> getAll() {
		ResultSet rs = Database.select("SELECT Company, Warehouse, Volume FROM stockVolume");
		ArrayList<StockVolumeItem> stockVolumes = new ArrayList<StockVolumeItem>();
		try {
			while(rs.next())
				stockVolumes.add(new StockVolumeItem(rs.getString("Company"), rs.getLong("Volume"), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return stockVolumes;
	}
	
	public static ArrayList<StockVolumeItem> getAllItemsInVolume(StockVolume volume) {
		ResultSet rs = Database.select("SELECT Item FROM stockVolumeItem WHERE Company = '" + volume.getCompany() + "' AND Volume = " + volume.getVolume() + "");
		ArrayList<StockVolumeItem> stockVolumes = new ArrayList<StockVolumeItem>();
		try {
			while(rs.next())
				stockVolumes.add(new StockVolumeItem(volume.getCompany(), volume.getVolume(), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return stockVolumes;
	}
	
	public String getCompany() {
		return company;
	}

	public long getVolume() {
		return this.volume;
	}

	public String getItem() {
		return this.item;
	}
	
	public double getQuantity() {
		return quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO stockVolume (Company, Warehouse, Volume, Item, Quantity, Unit) VALUES ('" + company + "', '" + volume + "', '" + item + "', '" + quantity + "', '" + unit + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE stockVolume SET Quantity = '" + quantity + "', Unit = '" + unit + "' WHERE Company = '" + company + "' AND Volume = '" + volume + "' AND Item = '" + item + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("StockVolume", "stockVolume", "Company = '" + company + "' AND Volume = '" + volume + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM stockVolume WHERE Company = '" + company + "' AND Volume = '" + volume + "'");
	}

	protected boolean validate() {
		return (volume != 0 && !Utilities.stringIsEmpty(company) && quantity != 0 && !Utilities.stringIsEmpty(unit));
	}
}
