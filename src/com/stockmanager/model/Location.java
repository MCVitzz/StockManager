package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Location extends DatabaseObject {

	private String company;
	private String warehouse;
	private String location;
	private String type;

	public Location() {}

	public Location(String company, String warehouse, String location) {
		this.company = company;
		this.warehouse = warehouse;
		this.location = location;
		try {
			ResultSet rs = Database.select("SELECT * FROM location WHERE Company = '" + Utilities.escape(company) + "' AND Warehouse = '" + Utilities.escape(warehouse) + "' AND Location = '" + Utilities.escape(location) + "'");
			while (rs.next()) {
				this.type = rs.getString("Type");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Location> getAll() {
		ResultSet rs = Database.select("SELECT Company, Warehouse, Location FROM location");
		ArrayList<Location> locations = new ArrayList<Location>();
		try {
			while(rs.next())
				locations.add(new Location(rs.getString("Company"), rs.getString("Warehouse"), rs.getString("Location")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return locations;
	}
	
	public String getCompany() {
		return company;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public String getLocation() {
		return this.location;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO location (Company, Warehouse, Location, Type) VALUES ('" + Utilities.escape(company) + "', '" + Utilities.escape(warehouse) + "', '" + Utilities.escape(location) + "', '" + Utilities.escape(type) + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE location SET Type = '" + type + "' WHERE Company = '" + Utilities.escape(company) + "' AND Warehouse = '" + Utilities.escape(warehouse) + "' AND Location = '" + Utilities.escape(location) + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Location", "location", "Company = '" + Utilities.escape(company) + "' AND Location = '" + Utilities.escape(location) + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM location WHERE Company = '" + Utilities.escape(company) + "' AND Location = '" + Utilities.escape(location) + "'");
	}

	protected boolean validate() {
		return (!Utilities.stringIsEmpty(location) && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(type));
	}
}
