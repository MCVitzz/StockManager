package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class SaleItem extends DatabaseObject {

	private String company;
	private int sale;
	private String item;
	private double quantity;
	private double confirmedQuantity;
	private String unit;
	private String state;

	public SaleItem() {}

	public SaleItem(String company, int sale, String item) {
		this.company = company;
		this.sale = sale;
		this.item = item;
		try {
			ResultSet rs = Database.select("SELECT * FROM saleItem WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Item = '" + item + "'");
			while (rs.next()) {
				this.quantity = rs.getFloat("Quantity");
				this.confirmedQuantity = rs.getFloat("ConfirmedQuantity");
				this.unit = rs.getString("Unit");
				this.state = rs.getString("State");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<SaleItem> getAll() {
		ResultSet rs = Database.select("SELECT Company, Warehouse, Sale FROM sale");
		ArrayList<SaleItem> sales = new ArrayList<SaleItem>();
		try {
			while(rs.next())
				sales.add(new SaleItem(rs.getString("Company"), rs.getInt("Sale"), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return sales;
	}
	
	public static ArrayList<SaleItem> getAllItemsInSale(Sale sale) {
		ResultSet rs = Database.select("SELECT Item FROM saleItem WHERE Company = '" + sale.getCompany() + "' AND Sale = " + sale.getSale() + "");
		ArrayList<SaleItem> sales = new ArrayList<SaleItem>();
		try {
			while(rs.next())
				sales.add(new SaleItem(sale.getCompany(), sale.getSale(), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return sales;
	}
	
	public String getName() {
		return Database.simpleSelect("Name", "Item", "Company = '" + company + "' AND Item = '" + item + "'");
	}
	
	public String getCompany() {
		return company;
	}

	public int getSale() {
		return this.sale;
	}

	public String getItem() {
		return this.item;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public double getConfirmedQuantity() {
		return confirmedQuantity;
	}

	public void setConfirmedQuantity(double confirmedQuantity) {
		this.confirmedQuantity = confirmedQuantity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO saleItem (Company, Sale, Item, Quantity, ConfirmedQuantity, Unit, State) VALUES ('" + company + "', '" + sale + "', '" + item + "', '" + quantity + "', '" + confirmedQuantity + "', '" + unit + "', '" + state + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE saleItem SET Quantity = '" + quantity + "', ConfirmedQuantity = '" + confirmedQuantity + "', Unit = '" + unit + "', state = '" + state + "' WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Item = '" + item + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Sale", "saleItem", "Company = '" + company + "' AND Sale = '" + sale + "' AND Item = '" + item + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM saleItem WHERE Company = '" + company + "' AND Sale = '" + sale + "' AND Item '" + item + "'");
	}

	protected boolean validate() {
		return (sale != 0 && !Utilities.stringIsEmpty(company) && quantity != 0 && !Utilities.stringIsEmpty(unit));
	}
}
