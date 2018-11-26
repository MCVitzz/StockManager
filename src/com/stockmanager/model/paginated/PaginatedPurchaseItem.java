package com.stockmanager.model.paginated;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.model.Database;
import com.stockmanager.model.Purchase;
import com.stockmanager.model.PurchaseItem;
import com.stockmanager.utils.Utilities;

public class PaginatedPurchaseItem extends PurchaseItem {
	
	String name;
	
	public PaginatedPurchaseItem(String company, int purchase, String item, String name) {
		super(company, purchase, item);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public static ArrayList<PaginatedPurchaseItem> getAllPaginatedItemsInPurchase(Purchase purchase) {
		ResultSet rs = Database.select("SELECT PI.Item, I.Name FROM purchaseItem AS PI INNER JOIN Item AS I ON I.Company = PI.Company AND I.Item = PI.Item WHERE PI.Company = '" + purchase.getCompany() + "' AND Purchase = " + purchase.getPurchase() + "");
		ArrayList<PaginatedPurchaseItem> purchases = new ArrayList<PaginatedPurchaseItem>();
		try {
			while(rs.next())
				purchases.add(new PaginatedPurchaseItem(purchase.getCompany(), purchase.getPurchase(), rs.getString("Item"), rs.getString("Name")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return purchases;
	}
}