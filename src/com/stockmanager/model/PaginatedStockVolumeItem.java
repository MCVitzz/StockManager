package com.stockmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stockmanager.utils.Utilities;

public class PaginatedStockVolumeItem extends StockVolumeItem {
	public PaginatedStockVolumeItem(String company, long volume, String item) {
		super(company, volume, item);
	}

	public String getName() {
		return new Item(getCompany(), getItem()).getName();
	}
	
	public static ArrayList<PaginatedStockVolumeItem> getAllPaginatedItemsInVolume(StockVolume volume) {
		ResultSet rs = Database.select("SELECT Item FROM stockVolumeItem WHERE Company = '" + volume.getCompany() + "' AND Volume = " + volume.getVolume() + "");
		ArrayList<PaginatedStockVolumeItem> stockVolumes = new ArrayList<PaginatedStockVolumeItem>();
		try {
			while(rs.next())
				stockVolumes.add(new PaginatedStockVolumeItem(volume.getCompany(), volume.getVolume(), rs.getString("Item")));
		} catch (SQLException e) {
			Utilities.warn(e.getMessage());
			e.printStackTrace();
		}
		return stockVolumes;
	}
}
