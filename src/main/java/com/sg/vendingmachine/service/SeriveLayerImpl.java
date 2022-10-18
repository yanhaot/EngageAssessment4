package com.sg.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;

import com.sg.vendingmachine.dao.AuditDao;
import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Item;

public class SeriveLayerImpl implements ServiceLayer{

	private Dao dao;
	private AuditDao auditDao;
	
	public SeriveLayerImpl(Dao dao, AuditDao auditDao) {
		this.dao = dao;
		this.auditDao = auditDao;
	}
	
	public List<Item> displayAllItems() throws VendingPersistenceException {
        return dao.displayAllItems();
	}

	public Item selectItem(String itemID, BigDecimal money) throws VendingPersistenceException, VendingInsufficientFundsException, VendingNoItemInventoryException {
        if(money.compareTo(dao.calculateItemCost(itemID)) == -1){
            throw new VendingInsufficientFundsException("Insufficent funds " + money + ", " + dao.calculateItemCost(itemID));
        }		
        if(getItemStock(itemID) <= 0){
            throw new VendingNoItemInventoryException("No items left");
        }
        auditDao.writeAuditeEntry("Item: " + itemID + " has been purchased.");
        return dao.selectItem(itemID);
	}

	public void updateInventory(String itemID) throws VendingPersistenceException {
        auditDao.writeAuditeEntry("Item: " + itemID + " inventory was changed."); 
		dao.updateInventory(itemID);
	}

	public BigDecimal calculateItemCost(String itemID) throws VendingPersistenceException {
	       return dao.calculateItemCost(itemID);
	}

	public int getItemStock(String itemID) throws VendingPersistenceException {
        return dao.getItemStock(itemID);
	}

}
