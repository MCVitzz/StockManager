package com.stockmanager.model;

import java.util.stream.Collectors;

/**
 * 
 * Possible states for a purchase and it's lines
 *
 */
public enum PurchaseState {
	OPEN, CLOSED, RECIEVING;

	public static PurchaseState getState(String state) {
		switch(state) {
		case "Open":
			return OPEN;
		case "Closed":
			return CLOSED;
		case "Picking":
			return RECIEVING;
		default:
			return null;
		}
	}

	public boolean canChange(PurchaseState changeTo, Purchase purchase) {
		switch(changeTo) {
		case RECIEVING:
			return (purchase.getItems().stream().filter(a -> a.getState() != OPEN).count() > 0 && purchase.getState() != CLOSED);
		case OPEN:
			return false;
		case CLOSED:
			return purchase.getItems().stream().filter(a -> a.getState() == RECIEVING || a.getState() == CLOSED).count() > 0 && purchase.getState() == RECIEVING;
		default:
			return false;
		}
	}

	public void changeTo(PurchaseState state, Purchase sale) {
		switch(state) {
		case RECIEVING:
			for(PurchaseItem si : sale.getItems().stream().filter(a -> a.getState() == OPEN).collect(Collectors.toList()))
				si.setState(RECIEVING.toString());
			break;
		case CLOSED:
			for(PurchaseItem si : sale.getItems().stream().filter(a -> a.getState() == RECIEVING).collect(Collectors.toList()))
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
		case RECIEVING:
			return "Recieving";
		default:
			return null;
		}
	}
}