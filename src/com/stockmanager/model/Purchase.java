package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.stockmanager.utils.Utilities;

public class Purchase extends DatabaseObject {

	private String company;
	private int purchase;
	private String warehouse;
	private Date date;
	private String supplier;
	private String state;

	public Purchase() {}

	public Purchase(String company, int purchase) {
		this.company = company;
		this.purchase = purchase;
		try {
			ResultSet rs = Database.select("SELECT * FROM purchase WHERE Company = '" + company + "' AND Purchase = '" + purchase + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.date = rs.getDate("Date");
				this.supplier = rs.getString("Supplier");
				this.state = rs.getString("Date");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Purchase> getAll() {
		ResultSet rs = Database.select("SELECT Company, Purchase FROM purchase");
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		try {
			while(rs.next())
				purchases.add(new Purchase(rs.getString("Company"), rs.getInt("Purchase")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return purchases;
	}
	
	public String getCompany() {
		return company;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public long getPurchase() {
		return this.purchase;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO purchase (Company, Purchase, Warehouse, Date, Supplier, State) VALUES ('" + company + "', '" + purchase + "', '" + warehouse + "', '" + date + "', '" + supplier + "', '" + state + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE purchase SET Warehouse = '" + warehouse + "', Date = '" + date + "', Supplier = '" + supplier + "', State = '" + state + "' WHERE Company = '" + company + "' AND Purchase = '" + purchase + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Purchase", "purchase", "Company = '" + company + "' AND Purchase = '" + purchase + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM purchase WHERE Company = '" + company + "' AND Warehouse = '" + warehouse + "' AND Purchase = '" + purchase + "'");
	}

	protected boolean validate() {
		return (purchase != 0 && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(location));
	}
}
