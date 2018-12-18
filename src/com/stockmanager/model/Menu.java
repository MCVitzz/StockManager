package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class Menu {
	private String name;
	private String image;
	private String cssClass;
	private String view;
	
	public Menu(String name, String image, String cssClass) {
		this.name = name;
		this.cssClass = cssClass;
		try {
			ResultSet rs = Database.select("SELECT * FROM menu WHERE Name = '" + name + "'");
			while (rs.next()) {
				this.image = rs.getString("Image");
				this.cssClass = rs.getString("CssClass");
				this.view = rs.getString("View");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Menu> getAll() {
		ResultSet rs = Database.select("SELECT Name, Image, cssClass FROM menu");
		ArrayList<Menu> menu = new ArrayList<Menu>();
		try {
			while(rs.next())
				menu.add(new Menu(rs.getString("Name"), rs.getString("Image"), rs.getString("CssClass")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return menu;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getCssClass() {
		return cssClass;
	}
	
	public String getView() {
		return this.view;
	}
	
	public void setCssClass (String cssClass) {
		this.cssClass = cssClass;
	}
}
