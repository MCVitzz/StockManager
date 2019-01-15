package com.stockmanager.model;

import java.util.stream.Collectors;

/**
 * 
 * Possible states for a purchase and it's lines
 *
 */
public enum PurchaseState {
	OPEN, CLOSED, RECEIVING;

	public static PurchaseState getState(String state) {
		System.out.println(state);
		PurchaseState ps;
		switch(state) {
		case "Open":
			ps = OPEN;
			break;
		case "Closed":
			ps = CLOSED;
			break;
		case "Receiving":
			ps = RECEIVING;
			break;
		default:
			ps = null;
		}
		return ps;
	}

	public boolean canChange(PurchaseState changeTo, Purchase purchase) {
		System.out.println(changeTo);
		switch(changeTo) {
		case RECEIVING:
			return (purchase.getItems().stream().filter(a -> a.getState() != OPEN).count() > 0 && purchase.getState() != CLOSED);
		case OPEN:
			return true;
		case CLOSED:
			return purchase.getItems().stream().filter(a -> a.getState() == RECEIVING || a.getState() == CLOSED).count() > 0 && purchase.getState() == RECEIVING;
		default:
			return false;
		}
	}

	public void changeTo(PurchaseState state, Purchase sale) {
		switch(state) {
		case RECEIVING:
			for(PurchaseItem si : sale.getItems().stream().filter(a -> a.getState() == OPEN).collect(Collectors.toList()))
				si.setState(RECEIVING.toString());
			break;
		case CLOSED:
			for(PurchaseItem si : sale.getItems().stream().filter(a -> a.getState() == RECEIVING).collect(Collectors.toList()))
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
		case RECEIVING:
			return "Receiving";
		default:
			return null;
		}
	}
}