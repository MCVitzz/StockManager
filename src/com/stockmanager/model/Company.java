package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Company extends DatabaseObject {

	private String company;
	private String address;
	private String country;
	
	public Company() {
		
	}
	
	public Company(String company) {
		this.company = company;
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
	protected boolean insert() {
		return Database.executeQuery("INSERT INTO company (Company, Address, Country) VALUES ('" + company + "', '" + address + "', '" + country + "')");
	}

	@Override
	protected boolean update() {
		return Database.executeQuery("UPDATE company SET( WHERECompany, Address, Country) VALUES (, '" + address + "', '" + country + "')");
	}

	@Override
	protected boolean exists() {
		return Database.simpleSelect("Company", "company", "company = '" + company + "'") != null;
	}

	@Override
	public boolean delete() {
		return Database.executeQuery("DELETE FROM company WHERE Company = '" + this.company + "'");
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
}
