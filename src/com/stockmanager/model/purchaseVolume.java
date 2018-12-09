package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.stockmanager.utils.Utilities;

public class purchaseVolume extends DatabaseObject {

	private String company;
	private int purchase;
	private long volume;
	private String warehouse;
	private String location;
	
	public purchaseVolume(String company, int purchase, long volume) {
		this.company = company;
		this.purchase = purchase;
		this.volume = volume;
		try {
			ResultSet rs = Database.select("SELECT * FROM purchaseVolume WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "'");
			while (rs.next()) {
				this.warehouse = rs.getString("Warehouse");
				this.location = rs.getString("Location");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO purchaseVolume (Company, Purchase, Volume, Warehouse, Location) VALUES '" + company + "', '" + purchase + "', '" + volume + "', '" + warehouse + "', '" + location + "'");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE purchaseVolume SET Warehouse = '" + warehouse + "', Location = '" + location + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Purchase", "purchaseVolume", "Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume = '" + volume + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM purchaseVolume WHERE Company = '" + company + "' AND Purchase = '" + purchase + "' AND Volume '" + volume + "'");
	}

	@Override
	protected boolean validate() {
		return (purchase != 0 && !Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(warehouse) && !Utilities.stringIsEmpty(location));
	}

}
