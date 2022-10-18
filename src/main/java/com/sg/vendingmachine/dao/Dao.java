package com.sg.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.List;

import com.sg.vendingmachine.dto.Item;

public interface Dao {

	Item addItem(String itemID, Item item);
	
	List<Item> displayAllItems() throws VendingPersistenceException;
	
	Item selectItem(String itemID) throws VendingPersistenceException;
	
	Item updateInventory(String itemID) throws VendingPersistenceException;
	
	BigDecimal calculateItemCost(String itemID) throws VendingPersistenceException;
	
	int getItemStock(String itemID) throws VendingPersistenceException;
}
