package com.stockmanager.model.paginated;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.model.Database;
import com.stockmanager.model.StockVolume;
import com.stockmanager.model.StockVolumeItem;
import com.stockmanager.utils.Utilities;

public class PaginatedStockVolumeItem extends StockVolumeItem {
	
	private String name;
	
	public PaginatedStockVolumeItem(String company, long purchase, String item, String name) {
		super(company, purchase, item);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public static ArrayList<PaginatedStockVolumeItem> getAllPaginatedItemsInVolume(StockVolume stockVolume) {
		ResultSet rs = Database.select("SELECT PI.Item, I.Name FROM stockVolumeItem AS PI INNER JOIN Item AS I ON I.Company = PI.Company AND I.Item = PI.Item WHERE I.Company = '" + stockVolume.getCompany() + "' AND Volume = " + stockVolume.getVolume() + "");
		ArrayList<PaginatedStockVolumeItem> stockVolumeItems = new ArrayList<PaginatedStockVolumeItem>();
		try {
			while(rs.next())
				stockVolumeItems.add(new PaginatedStockVolumeItem(stockVolume.getCompany(), stockVolume.getVolume(), rs.getString("Item"), rs.getString("Name")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return stockVolumeItems;
	}
}
