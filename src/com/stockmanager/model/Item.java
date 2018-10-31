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
	
	
	public Item(String item) {
		this.item = item;
		try {
			ResultSet rs = Database.select("SELECT * FROM item WHERE Item = '" + item + "'");
			while (rs.next()) {
				this.company = rs.getString("Company");
				this.name = rs.getString("Name");
				this.unit = rs.getString("Unit");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Item> getAll() {
		ResultSet rs = Database.select("SELECT Item FROM item");
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			while(rs.next())
				items.add(new Item(rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return items;
	}

	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO item (Company, Item, Name, Unit) VALUES ('"+ company+ "', '" + item + "', '" + name + "', '" + unit + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE item SET Company = '" + company + "', Name = '" + name + "', Unit = '" + unit + "' WHERE Item = '" + item + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Item", "item", "item = '" + item + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM item WHERE Item = '" + this.item + "'");
	}

	@Override
	protected boolean validate() {
		return (!Utilities.stringIsEmpty(item) && !Utilities.stringIsEmpty(name) && !Utilities.stringIsEmpty(unit));
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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
