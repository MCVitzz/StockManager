package com.stockmanager.model;

import java.util.stream.Collectors;
/**
 * 
 * Possible states for a purchase and it's lines
 *
 */
public enum SaleState {
	OPEN, CLOSED, PICKING;

	public static SaleState getState(String state) {
		switch(state) {
		case "Open":
			return OPEN;
		case "Closed":
			return CLOSED;
		case "Picking":
			return PICKING;
		default:
			return null;
		}
	}

	public boolean canChange(SaleState changeTo, Sale sale) {
		switch(changeTo) {
		case PICKING:
			return (sale.getItems().stream().filter(a -> a.getState() != OPEN).count() > 0 && sale.getState() != CLOSED && sale.hasStock());
		case OPEN:
			return false;
		case CLOSED:
			return sale.getItems().stream().filter(a -> a.getState() == PICKING || a.getState() == CLOSED).count() > 0 && sale.getState() == PICKING;
		default:
			return false;
		}
	}

	public void changeTo(SaleState state, Sale sale) {
		switch(state) {
		case PICKING:
			for(SaleItem si : sale.getItems().stream().filter(a -> a.getState() == OPEN).collect(Collectors.toList()))
				si.setState(PICKING.toString());
			break;
		case CLOSED:
			for(SaleItem si : sale.getItems().stream().filter(a -> a.getState() == PICKING).collect(Collectors.toList()))
				si.setState(CLOSED.toString());
		default:
			break;
		}
	}
	
	@Override
	public String toString() {
		switch(this) {
		case OPEN:
			return "Open";
		case CLOSED:
			return "Closed";
		case PICKING:
			return "Picking";
		default:
			return null;
		}
	}
}