package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class PurchaseItem extends DatabaseObject {

	private String company;
	private int purchase;
	private String item;
	private double quantity;
	private double confirmedQuantity;
	private String unit;
	private String state;

	public PurchaseItem() {}

	public PurchaseItem(String company, int purchase, String item) {
		this.company = company;
		this.purchase = purchase;
		this.item = item;
		try {
			ResultSet rs = Database.select("SELECT * FROM purchaseItem WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Item = '" + item + "'");
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

	public static ArrayList<PurchaseItem> getAll() {
		ResultSet rs = Database.select("SELECT Company, Warehouse, Purchase FROM purchase");
		ArrayList<PurchaseItem> purchases = new ArrayList<PurchaseItem>();
		try {
			while(rs.next())
				purchases.add(new PurchaseItem(rs.getString("Company"), rs.getInt("Purchase"), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return purchases;
	}
	
	public static ArrayList<PurchaseItem> getAllItemsInPurchase(Purchase purchase) {
		ResultSet rs = Database.select("SELECT Item FROM purchaseItem WHERE Company = '" + purchase.getCompany() + "' AND Purchase = " + purchase.getPurchase() + "");
		ArrayList<PurchaseItem> purchases = new ArrayList<PurchaseItem>();
		try {
			while(rs.next())
				purchases.add(new PurchaseItem(purchase.getCompany(), purchase.getPurchase(), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return purchases;
	}
	
	public String getCompany() {
		return company;
	}

	public int getPurchase() {
		return this.purchase;
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
		return Database.executeQuery("INSERT INTO purchaseItem (Company, Warehouse, Purchase, Item, Quantity, ConfirmedQuantity, Unit, State) VALUES ('" + company + "', '" + purchase + "', '" + item + "', '" + quantity + "', '" + confirmedQuantity + "', '" + unit + "', '" + state + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE purchase SET Quantity = '" + quantity + "', ConfirmedQuantity = '" + confirmedQuantity + "', Unit = '" + unit + "', state = '" + state + "' WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Item = '" + item + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Purchase", "purchaseItem", "Company = '" + company + "' AND Purchase = '" + purchase + "' AND Item = '" + item + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM purchaseItem WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Item '" + item + "'");
	}

	protected boolean validate() {
		return (purchase != 0 && !Utilities.stringIsEmpty(company) && quantity != 0 && !Utilities.stringIsEmpty(unit));
	}
}
