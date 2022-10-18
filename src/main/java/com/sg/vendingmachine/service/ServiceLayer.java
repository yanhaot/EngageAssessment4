package com.sg.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;

import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Item;


public interface ServiceLayer {

	List<Item> displayAllItems() throws
    VendingPersistenceException;
	
	Item selectItem(String itemId, BigDecimal money) throws
    VendingPersistenceException, VendingInsufficientFundsException, VendingNoItemInventoryException;
	
	void updateInventory(String itemID) throws
    VendingPersistenceException;
	
	BigDecimal calculateItemCost(String itemID) throws
    VendingPersistenceException;
	
	int getItemStock(String itemID) throws
    VendingPersistenceException;
	
}
