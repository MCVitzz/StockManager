package com.stockmanager.model.paginated;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.model.Database;
import com.stockmanager.model.Sale;
import com.stockmanager.model.SaleItem;
import com.stockmanager.utils.Utilities;

public class PaginatedSaleItem extends SaleItem {
	
	String name;
	
	public PaginatedSaleItem(String company, int sale, String item, String name) {
		super(company, sale, item);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public static ArrayList<PaginatedSaleItem> getAllPaginatedItemsInSale(Sale sale) {
		ResultSet rs = Database.select("SELECT PI.Item, I.Name FROM saleItem AS PI INNER JOIN Item AS I ON I.Company = PI.Company AND I.Item = PI.Item WHERE PI.Company = '" + sale.getCompany() + "' AND Sale = " + sale.getSale() + "");
		ArrayList<PaginatedSaleItem> sales = new ArrayList<PaginatedSaleItem>();
		try {
			while(rs.next())
				sales.add(new PaginatedSaleItem(sale.getCompany(), sale.getSale(), rs.getString("Item"), rs.getString("Name")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return sales;
	}
}