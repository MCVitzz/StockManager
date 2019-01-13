package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Company extends DatabaseObject {

	private String company;
	private String address;
	private String country;
	
	public Company(String company) {
		this.company = company;
		try {
			ResultSet rs = Database.select("SELECT * FROM company WHERE Company = '" + Utilities.escape(company) + "'");
			while (rs.next()) {
				this.address = rs.getString("Address");
				this.country = rs.getString("Country");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Company> getAll() {
		ResultSet rs = Database.select("SELECT Company FROM company");
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			while(rs.next())
				companies.add(new Company(rs.getString("Company")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return companies;
	}

	@Override
	public String toString() {
		return this.company;
	}
	
	@Override
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO company (Company, Address, Country) VALUES ('" + Utilities.escape(company) + "', '" + Utilities.escape(address) + "', '" + Utilities.escape(country) + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE company SET Address = '" + Utilities.escape(address) + "', Country = '" + Utilities.escape(country) + "' WHERE Company = '" + Utilities.escape(company) + "'");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Company", "company", "company = '" + Utilities.escape(company) + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM company WHERE Company = '" + Utilities.escape(company) + "'");
	}

	@Override
	protected boolean validate() {
		return (!Utilities.stringIsEmpty(company) && !Utilities.stringIsEmpty(address) && !Utilities.stringIsEmpty(country));
	}
	
	public String getCompany() {
		return company;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
