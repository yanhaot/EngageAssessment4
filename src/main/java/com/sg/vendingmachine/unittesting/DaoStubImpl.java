package com.sg.vendingmachine.unittesting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Item;

public class DaoStubImpl implements Dao{
	
	private Item onlyItem;
	
	public DaoStubImpl() {
		onlyItem = new Item("E1");
		onlyItem.setItemName("Test Product");
        onlyItem.setPrice("0.50");
        onlyItem.setCount("5");
	}

    public DaoStubImpl(Item item) {
        this.onlyItem = item;
    }
    
	@Override
	public Item addItem(String itemID, Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> displayAllItems() throws VendingPersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
	}

	@Override
	public Item selectItem(String itemID) throws VendingPersistenceException {
        if (itemID.equals(onlyItem.getId())) {
            return onlyItem;
        }
        return null;
	}

	@Override
	public Item updateInventory(String itemID) throws VendingPersistenceException {
        if (itemID.equals(onlyItem.getId())) {
            int inventory = Integer.parseInt(onlyItem.getCount()) - 1;
            onlyItem.setCount(String.valueOf(inventory));
            return onlyItem;
        }
        return null;
	}

	@Override
	public BigDecimal calculateItemCost(String itemID) throws VendingPersistenceException {
        if (itemID.equals(onlyItem.getId())) {
            BigDecimal tempItemCost = new BigDecimal(onlyItem.getPrice());
            BigDecimal itemCost = tempItemCost.multiply(BigDecimal.valueOf(100));
            return itemCost;
        }

        return null;
	}

	@Override
	public int getItemStock(String itemID) throws VendingPersistenceException {
        if (itemID.equals(onlyItem.getId())) {
            int itemInventory = Integer.parseInt(onlyItem.getCount());
            return itemInventory;
        }

        return 0;
	}

}
