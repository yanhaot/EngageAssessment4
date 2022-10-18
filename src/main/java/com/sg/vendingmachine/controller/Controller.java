package com.sg.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.List;

import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.ServiceLayer;
import com.sg.vendingmachine.service.VendingInsufficientFundsException;
import com.sg.vendingmachine.service.VendingNoItemInventoryException;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOImpl;
import com.sg.vendingmachine.ui.View;

public class Controller {
	
	private View view;
    private ServiceLayer service;

    private UserIO io = new UserIOImpl();

    public Controller(ServiceLayer dao, View view) {
        this.service = dao;
        this.view = view;
    }

	public void run() throws VendingPersistenceException, VendingInsufficientFundsException, VendingNoItemInventoryException {
		boolean keepGoing = true;
		int menuSelection = 0;
		
        try {
    		while(keepGoing) {
    	        BigDecimal money = getMoney();
    	        Change change = new Change();
                menuSelection = getMenuSelection();

    	             switch (menuSelection) {
    	                 case 1:
    	                	 buyItem(money, change);
    	                     break;
    	                 case 2:
    	                	 keepGoing = false;
    	                     break;
    	                 default:
    	                     unknownCommand();
    	             }

    	        } 
    	        exitMessage();
        	} catch (VendingPersistenceException e) {
        }
	}
	
    //Asks the user how much money they are placing into the vending machine. returns amount in pennies as BigDecimal
    private BigDecimal getMoney() throws VendingPersistenceException{
       String amount = view.askUserForAmount();
       BigDecimal pennies = new BigDecimal(amount).multiply(BigDecimal.valueOf(100));
       return pennies;
    }
	
    // Process of buying from vending machine
    private void buyItem(BigDecimal money, Change change) throws VendingPersistenceException, VendingInsufficientFundsException, VendingNoItemInventoryException{
        //Displays items to user
        List<Item> itemList = service.displayAllItems();
        view.displayItemList(itemList);
        //Gets item id from user
        String itemId = view.getItemId();
        //Processing the item and throws exceptions. Removes an item from .txt file, also adds an audit
        service.selectItem(itemId, money);
        service.updateInventory(itemId);
        //Processes amount of change left and shows the user
        money = money.subtract(service.calculateItemCost(itemId));
        view.displayMoney(money, change);
        
    }
    
    //Calls view method to get user selection
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
		
	private void unknownCommand() {
	    view.displayUnknownCommandBanner();
	}

	private void exitMessage() {
//        dao.save();
	    view.displayExitBanner();
	}
}
