package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

public class View {
	
	UserIO io = new UserIOImpl();
	
    enum currency {
        QUARTER, DIME, NICKEL, PENNY
    }
    
	public View(UserIO io) {
			this.io = io;
	}
	
    //Asks user to how much money to place into the vending machine. Takes in a $$.CC format
    public String askUserForAmount(){
        return io.readString("How much money would you like to put into the machine? $.CC format: ");
    }
    
	//Prints menu to user and asks for a users choice of which to chose.
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Choose Item");
        io.print("2. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }
    
    //Gets an item id from user
    public String getItemId(){
        return io.readString("Choose an item ID you would like to purchase.");
    }
    
    //Displays the change to user. Uses Change Class to calculate change and returns a hashmap with Coins as keys, and
    //amount of coins as the values. BigDecimal calculations for processing the change.
    public void displayMoney(BigDecimal money, Change change){
        HashMap<String, String> changeMap = change.getChange(money.intValue());
        io.print("======Here is your change======");
        io.print("Quarters = " + changeMap.get(currency.QUARTER.toString().toLowerCase()));
        io.print("Dimes = " + changeMap.get(currency.DIME.toString().toLowerCase()));
        io.print("Nickels = " + changeMap.get(currency.NICKEL.toString().toLowerCase()));
        io.print("Pennies = " + changeMap.get(currency.PENNY.toString().toLowerCase()));
        io.print("===============================");
        
    }
    
    //List all items in the .txt file to display to user
    public void displayItemList(List<Item> itemList) {
    	
//    	Collections.sort(itemList, new Comparator<Item>() {
//
//			@Override
//			public int compare(Item o1, Item o2) {
//				return o1.getId().compareTo(o2.getId());
//			}
//		
//    	});
    	
    	// Sort List using Lamdba through ID
		Comparator<Item> com = (s1, s2) -> s1.getId().compareTo(s2.getId());
		Collections.sort(itemList, com);

        for (Item currentItem : itemList) {
        	// If Quantity is zero, will not display in main menu
        	if(Integer.parseInt(currentItem.getCount()) != 0) {
                String itemInfo = String.format("#%s : %s : Cost: %s : Amount left: %s",
                        currentItem.getId(),
                        currentItem.getItemName(),
                        currentItem.getPrice(),
                        currentItem.getCount());
                io.print(itemInfo);
            }
        }
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
}
