package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Sale extends DatabaseObject {

	private String company;
	private int sale;
	private String warehouse;
	private LocalDate date;
	private String client;
	private String state;

	public Sale() {}

	public Sale(String company, int sale) {
		this.company = company;
		this.sale = sale;
		try {
			ResultSet rs = Database.select("SELECT * FROM sale WHERE Company = '" + Utilities.escape(company) + "' AND Sale = '" + sale + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.date = rs.getDate("Date").toLocalDate();
				this.client = rs.getString("Client");
				this.state = rs.getString("State");
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Sale(String company) {
		this.company = company;
		try {
			this.sale = 1;
			String s = Database.simpleSelect("MAX(Sale) AS Sale", "sale",  "Company = '" + Utilities.escape(company) + "'");
			if(s != null)
				this.sale = Integer.parseInt(s) + 1;
			
			ResultSet rs = Database.select("SELECT * FROM sale WHERE Company = '" + Utilities.escape(company) + "' AND Sale = '" + sale + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.date = rs.getDate("Date").toLocalDate();
				this.client = rs.getString("Client");
				this.state = rs.getString("State");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Sale> getAll() {
		ResultSet rs = Database.select("SELECT Company, Sale FROM sale");
		ArrayList<Sale> sales = new ArrayList<Sale>();
		try {
			while(rs.next())
				sales.add(new Sale(rs.getString("Company"), rs.getInt("Sale")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return sales;
	}
	
	public boolean executeSale() {
		return Database.executeQuery("CALL withdrawstock('" + Utilities.escape(company) + "'	," + sale +")");
	}
	
	public ArrayList<SaleItem> getItems() {
		ResultSet rs = Database.select("SELECT Item FROM saleitem WHERE Company = '" + Utilities.escape(company) + "' AND Sale = " + sale);
		ArrayList<SaleItem> sales = new ArrayList<SaleItem>();
		try {
			while(rs.next())
				sales.add(new SaleItem(company, sale, rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return sales;
	}
	
	public static String getDashboardText() {
		return Database.simpleSelect("count(sale)", "sale");
	}
	
	public String getCompany() {
		return company;
	}

	public int getSale() {
		return this.sale;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO sale (Company, Sale, Warehouse, Date, Client, State) VALUES ('" + Utilities.escape(company) + "', '" + sale + "', '" + Utilities.escape(warehouse) + "', '" + date + "', '" + Utilities.escape(client) + "', '" + Utilities.escape(state) + "')");
	}

	protected boolean update() {
		return Database.executeQuery("UPDATE sale SET Warehouse = '" + Utilities.escape(warehouse) + "', Date = '" + date + "', Client = '" + Utilities.escape(client) + "', State = '" + Utilities.escape(state) + "' WHERE Company = '" + Utilities.escape(company) + "' AND Sale = '" + sale + "'");
	}

	protected boolean exists() {
		return Database.simpleSelect("Sale", "sale", "Company = '" + Utilities.escape(company) + "' AND Sale = '" + sale + "'") != null;
	}

	public boolean delete() {
		return Database.executeQuery("DELETE FROM sale WHERE Company = '" + Utilities.escape(company) + "' AND Sale = '" + sale + "'");
	}

	protected boolean validate() {
		return (!Utilities.stringIsEmpty(company) && sale != 0 && !Utilities.stringIsEmpty(warehouse) && date != null && !Utilities.stringIsEmpty(client) && !Utilities.stringIsEmpty(state));
	}
}
