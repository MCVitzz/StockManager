package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Unit extends DatabaseObject{

	private String unit;
	private String name;
	
	public Unit(String unit) {
		this.unit = unit;
		try {
			ResultSet rs = Database.select("SELECT * FROM unit WHERE Unit = '" + unit + "'");
			while (rs.next()) {
				this.name = rs.getString("Name");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<Unit> getAll() {
		ResultSet rs = Database.select("SELECT Unit FROM unit");
		ArrayList<Unit> units = new ArrayList<Unit>();
		try {
			while(rs.next())
				units.add(new Unit(rs.getString("Unit")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return units;
	}
	
	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO unit (Unit, Name) VALUES ('" + unit + "', '" + name + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE unit SET Name = '" + name +  "' WHERE Unit = '" + unit + "'");
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
	
	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
