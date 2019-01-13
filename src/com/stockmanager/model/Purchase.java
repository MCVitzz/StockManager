package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Purchase extends DatabaseObject {

	private String company;
	private int purchase;
	private String warehouse;
	private LocalDate date;
	private String supplier;
	private String state;

	public Purchase() {}

	public Purchase(String company, int purchase) {
		this.company = company;
		this.purchase = purchase;
		try {
			ResultSet rs = Database.select("SELECT * FROM purchase WHERE Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.date = rs.getDate("Date").toLocalDate();
				this.supplier = rs.getString("Supplier");
				this.state = rs.getString("State");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Purchase(String company) {
		this.company = company;
		try {
			this.purchase = 1;
			String s = Database.simpleSelect("MAX(Purchase) AS Purchase", "purchase",  "Company = '" + Utilities.escape(company) + "'");
			if(s != null)
				this.purchase = Integer.parseInt(s) + 1;
			
			ResultSet rs = Database.select("SELECT * FROM purchase WHERE Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.date = rs.getDate("Date").toLocalDate();
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
	
	public void createStock() {
		Database.executeQuery("INSERT INTO stockvolume SELECT PV.Company, PV.Volume, P.Warehouse, PV.Location FROM purchase AS P INNER JOIN purchasevolume AS PV ON P.Company = PV.Company AND P.Purchase = PV.Purchase WHERE P.Company = '" + Utilities.escape(company) + "' AND P.Purchase = " + purchase + "");
		Database.executeQuery("INSERT INTO stockvolumeitem SELECT PVI.Company, PVI.Volume, PVI.Item, PVI.Quantity, PVI.Unit, CURDATE() FROM purchase AS P INNER JOIN purchasevolumeitem AS PVI ON P.Company = PVI.Company AND P.Purchase = PVI.Purchase WHERE P.Company = '" + Utilities.escape(company) + "' AND P.Purchase = " + purchase + "");
	}
	
	public ArrayList<PurchaseItem> getItems() {
		ResultSet rs = Database.select("SELECT Item FROM purchaseItem WHERE Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "'");
		ArrayList<PurchaseItem> items = new ArrayList<PurchaseItem>();
		try {
			while(rs.next())
				items.add(new PurchaseItem(company, purchase, rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return items;
	}
	
	public static String getDashboardText() {
		return Database.simpleSelect("count(purchase)", "purchase");
	}
	
	public String getCompany() {
		return company;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public int getPurchase() {
		return this.purchase;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
    	for(PurchaseItem pi : getItems()) {
    		pi.setState(state);	
    		pi.save();
    	}
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	protected boolean insert() {
		return Database.executeQuery("INSERT INTO purchase (Company, Purchase, Warehouse, Date, Supplier, State) VALUES ('" + Utilities.escape(company) + "', '" + purchase + "', '" + Utilities.escape(warehouse) + "', '" + date + "', '" + Utilities.escape(supplier) + "', '" + Utilities.escape(state) + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE purchase SET Warehouse = '" + Utilities.escape(warehouse) + "', Date = '" + date + "', Supplier = '" + Utilities.escape(supplier) + "', State = '" + Utilities.escape(state) + "' WHERE Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Purchase", "purchase", "Company = '" + Utilities.escape(company) + "' AND Purchase = '" + purchase + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM purchase WHERE Company = '" + Utilities.escape(company)+ "' AND Purchase = '" + purchase + "'");
	}

	protected boolean validate() {
		return true;
	}
}
