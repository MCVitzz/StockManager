package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class StockVolume extends DatabaseObject {

	private String company;
	private String warehouse;
	private long volume;
	private String location;

	public StockVolume() {}

	public StockVolume(String company, long volume) {
		this.company = company;
		this.volume = volume;
		try {
			ResultSet rs = Database.select("SELECT * FROM stockVolume WHERE Company = '" + company + "' AND Volume = '" + volume + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.location = rs.getString("Location");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<StockVolume> getAll() {
		ResultSet rs = Database.select("SELECT Company, Volume FROM stockVolume");
		ArrayList<StockVolume> stockVolumes = new ArrayList<StockVolume>();
		try {
			while(rs.next())
				stockVolumes.add(new StockVolume(rs.getString("Company"), rs.getLong("Volume")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return stockVolumes;
	}
	
	public String getCompany() {
		return company;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public long getVolume() {
		return this.volume;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO stockVolume (Company, Warehouse, Volume, Location) VALUES ('" + company + "', '" + warehouse + "', '" + volume + "', '" + location + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE stockVolume SET Warehouse = '" + warehouse + "', Location = '" + location + "' WHERE Company = '" + company + "' AND Volume = '" + volume + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("StockVolume", "stockVolume", "Company = '" + company + "' AND Volume = '" + volume + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM stockVolume WHERE Company = '" + company + "' AND Volume = '" + volume + "'");
	}

	protected boolean validate() {
		return (volume != 0 && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(location));
	}
}
