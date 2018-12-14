package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class StockVolumeItem extends DatabaseObject {

	private String company;
	private long volume;
	private String item;
	private double quantity;
	private String unit;
	private LocalDate entryDate;

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
				this.entryDate = rs.getDate("EntryDate").toLocalDate();
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
	
	public static boolean hasStock(String company, String item, double quantity) {
		
		double qtdStock = Double.parseDouble(Database.simpleSelect("SUM(Quantity)","stockvolumeitem","Company = '" + company + "' AND Item = '"+ item +"'"));
		
		return quantity <= qtdStock;
		
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
	
	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO stockVolumeItem (Company, Warehouse, Volume, Item, Quantity, Unit, EntryDate) VALUES ('" + company + "', '" + volume + "', '" + item + "', '" + quantity + "', '" + unit + "', '" + entryDate + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE stockVolumeItem SET Quantity = '" + quantity + "', Unit = '" + unit + "', EntryDate = '" + entryDate + "' WHERE Company = '" + company + "' AND Volume = '" + volume + "' AND Item = '" + item + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Volume", "stockVolumeItem", "Company = '" + company + "' AND Volume = '" + volume + "' AND Item = '" + item + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM stockVolumeItem WHERE Company = '" + company + "' AND Volume = '" + volume + "' AND Item = '" + item + "'");
	}

	protected boolean validate() {
		return (volume != 0 && !Utilities.stringIsEmpty(company) && quantity != 0 && !Utilities.stringIsEmpty(unit));
	}
}
