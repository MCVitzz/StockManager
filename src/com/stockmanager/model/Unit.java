package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Unit extends DatabaseObject{

	private String company;
	private String unit;
	private String name;
	
	public Unit(String company, String unit) {
		this.company = company;
		this.unit = unit;
		try {
			ResultSet rs = Database.select("SELECT * FROM unit WHERE Company = '" + company + "' AND Unit = '" + unit + "'");
			while (rs.next()) {
				this.name = rs.getString("Name");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<Unit> getAll() {
		ResultSet rs = Database.select("SELECT Company,Unit FROM unit");
		ArrayList<Unit> units = new ArrayList<Unit>();
		try {
			while(rs.next())
				units.add(new Unit(rs.getString("Company"), rs.getString("Unit")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return units;
	}
	
	public static ArrayList<Unit> getAllUnitsFromCompany(String company) {
		ArrayList<Unit> units = new ArrayList<>();
		
		try {
			ResultSet rs = Database.select("SELECT Unit FROM unit WHERE Company = '" + company + "'");
			while (rs.next()) 
				units.add(new Unit(company, rs.getString("Unit")));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return units;
	}
	
	@Override
    public String toString() {
        return this.unit;
    }
	
	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO unit (Company, Unit, Name) VALUES ('"+ company+"', '" + unit + "', '" + name + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE unit SET Company = '" + company + "Name = '" + name +  "' WHERE Unit = '" + unit + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Unit", "unit", "unit = '" + unit + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM unit WHERE Unit = '" + this.unit + "'");
	}

	@Override
	protected boolean validate() {
		return (!Utilities.stringIsEmpty(unit) && !Utilities.stringIsEmpty(name));
	}
	
	
	public String getCompany() {
		return company;
	}
	
	public String getUnit() {
		return unit;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
