package com.stockmanager.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.stockmanager.utils.Utilities;


public class SalesOrder {
//	
//	private int saleID;
//	private	Date date;
//	private String status;
//	
//	
//	public SalesOrder(int saleID) {
//		this.saleID = saleID;
//		try {
//			ResultSet rs = Database.select("SELECT * FROM sales WHERE SaleID = '" + saleID + "'");
//			while (rs.next()) {
//				this.date = rs.getDate("Date");
//				this.status= rs.getString("Status");
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static ArrayList<SalesOrder> getAll() {
//		ResultSet rs = Database.select("SELECT SaleID FROM sales");
//		ArrayList<SalesOrder> sales = new ArrayList<SalesOrder>();
//		try {
//			while(rs.next())
//				sales.add(new SalesOrder(rs.getInt("SaleID")));
//		} catch (SQLException e) {
//			Utilities.warn(e.getMessage());
//			e.printStackTrace();
//		}
//		return sales;
//	}
//	
//	@Override
//	protected boolean insert() {
//		return Database.executeQuery("INSERT INTO sales (SaleID, Date, Status) VALUES ('" + saleID + "', '" + date + "', '" + status + "')");
//	}
//
//	@Override
//	protected boolean update() {
//		return Database.executeQuery("UPDATE sales SET Date = '" + date + "', Status = '" + status + "' WHERE SaleID = '" + saleID + "'");
//	}
//
//	@Override
//	protected boolean exists() {
//		return Database.simpleSelect("SaleID", "sales", "sales = '" + saleID + "'") != null;
//	}
//
//	@Override
//	public boolean delete() {
//		return Database.executeQuery("DELETE FROM sales WHERE SaleID = '" + this.saleID + "'");
//	}
//
//	@Override
//	protected boolean validate() {
//		return (!Utilities.stringIsEmpty(Integer.toString(saleID)) && !Utilities.stringIsEmpty(Date.toString(date)) && !Utilities.stringIsEmpty(status)); }
	}
	

