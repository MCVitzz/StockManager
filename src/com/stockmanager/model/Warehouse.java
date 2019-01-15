package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Warehouse extends DatabaseObject {

	private String company;
	private String warehouse;
	private String name;
	private String address;

	public Warehouse() {}

	public Warehouse(String company, String warehouse) {
		this.company = company;
		this.warehouse = warehouse;
		try {
			ResultSet rs = Database.select("SELECT * FROM warehouse WHERE Company = '" + Utilities.escape(company) + "' AND Warehouse = '" + Utilities.escape(warehouse) + "'");
			while (rs.next()) {
				this.name = rs.getString("Name");
				this.address = rs.getString("Address");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public String getWarehouse() {
		return this.warehouse;
	}

	public static ArrayList<Warehouse> getAllWarehousesFromCompany(String company) {
		ArrayList<Warehouse> warehouses = new ArrayList<>();
		
		try {
			ResultSet rs = Database.select("SELECT Warehouse FROM warehouse WHERE Company = '" + Utilities.escape(company) + "'");
			while (rs.next())
				warehouses.add(new Warehouse(company, rs.getString("Warehouse")));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return warehouses;
	}
	
	public static ArrayList<Warehouse> getAll() {
		ResultSet rs = Database.select("SELECT Company, Warehouse FROM warehouse");
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		try {
			while(rs.next())
				warehouses.add(new Warehouse(rs.getString("Company"), rs.getString("Warehouse")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return warehouses;
	}
	
	public static String getDashboardText() {
		return Database.simpleSelect("count(company)" , "warehouse");
	}
	
	public String getCompany() {
		return company;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO warehouse (Company, Warehouse, Name, Address) VALUES ('" + Utilities.escape(company) + "', '" + Utilities.escape(warehouse) + "', '" + Utilities.escape(name) + "', '" + Utilities.escape(address) + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE warehouse SET Name = '" + Utilities.escape(name) + "', Address = '" + Utilities.escape(address) + "' WHERE Company = '" + Utilities.escape(company) + "' AND Warehouse = '" + Utilities.escape(warehouse) + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Warehouse", "warehouse", "Company = '" + Utilities.escape(company) + "' AND Warehouse = '" + Utilities.escape(warehouse) + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM warehouse WHERE Company = '" + Utilities.escape(company) + "' AND Warehouse = '" + Utilities.escape(warehouse) + "'");
	}

	protected boolean validate() {
		return (!Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(address) && !Utilities.stringIsEmpty(name));
	}
	
	@Override
	public String toString() {
		return this.warehouse;
	}
	
}
