package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Item extends DatabaseObject{

	private String company;
	private String item;
	private String name;
	private String unit;
	
	
	public Item(String company, String item) {
		this.company = company;
		this.item = item;
		try {
			ResultSet rs = Database.select("SELECT * FROM item WHERE Company = '" + company + "' AND Item = '" + item + "'");
			while (rs.next()) {
				this.name = rs.getString("Name");
				this.unit = rs.getString("Unit");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Item> getAll() {
		ResultSet rs = Database.select("SELECT Company, Item FROM item");
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			while(rs.next())
				items.add(new Item(rs.getString("Company"),rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return items;
	}
	
	@Override
    public String toString() {
        return this.item;
    }

	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO item (Company, Item, Name, Unit) VALUES ('"+ Utilities.escape(company)+ "', '" + Utilities.escape(item) + "', '" + Utilities.escape(name) + "', '" + Utilities.escape(unit) + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE item SET Company = '" + Utilities.escape(company) + "', Name = '" + Utilities.escape(name) + "', Unit = '" + unit + "' WHERE Item = '" + Utilities.escape(item) + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Item", "item", "item = '" + Utilities.escape(item) + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM item WHERE Item = '" + Utilities.escape(item) + "'");
	}

	@Override
	protected boolean validate() {
		return (!Utilities.stringIsEmpty(item) && !Utilities.stringIsEmpty(name) && !Utilities.stringIsEmpty(unit));
	}
	
	public String getCompany() {
		return company;
	}
	
	
	public String getItem() {
		return item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
