package com.sg.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.vendingmachine.dto.Item;

public class DaoFileImpl implements Dao{

    public final String ITEMS_FILE;
    public static final  String DELIMITER = "::";
	
    private Map<String, Item> items = new HashMap<>();
    
    public DaoFileImpl(){
    	ITEMS_FILE = "inventory.txt";
    }
    
    public DaoFileImpl(String itemTextFile){
    	ITEMS_FILE = itemTextFile;
    }
    
	@Override
	public List<Item> displayAllItems(){
		loadRoster();		
		return new ArrayList<Item>(items.values());
	}

	@Override
	public Item selectItem(String itemID){
		// TODO Auto-generated method stub
		loadRoster();
        return items.get(itemID);
	}

	@Override
	public Item updateInventory(String itemID){
		loadRoster();
		Item updateItem = items.get(itemID);
		// Take note of this line
		int itemStock = Integer.parseInt(updateItem.getCount()) - 1;
		updateItem.setCount(String.valueOf(itemStock));
        writeLibrary();
		return items.get(itemID);
	}

	@Override
	public BigDecimal calculateItemCost(String itemID){	
		loadRoster();
        Item tempItem = items.get(itemID);
        BigDecimal tempItemCost = new BigDecimal(tempItem.getPrice());
        BigDecimal itemCost = tempItemCost.multiply(BigDecimal.valueOf(100));
        writeLibrary();
        return itemCost;
    }

	@Override
	public int getItemStock(String itemID){
		loadRoster();
        Item tempItem = items.get(itemID);
        int itemStock = Integer.parseInt(tempItem.getCount());
        return itemStock;
	}
	
	
	private Item unmarshallStudent(String itemAsText){
	    // iteAsText is expecting a line read in from our file.
	    // For example, it might look like this:
		// ItemID::Name::Cost::Count::

	    //
	    // We then split that line on our DELIMITER - which we are using as ::
	    // Leaving us with an array of Strings, stored in itemTokens.
	    // Which should look like this:
	    // ____________________________
	    // |      |     |     |       |
	    // |ItemID| Name|Price| Count |
	    // |      |     |     |       |
	    // ----------------------------
	    //  [0]  [1]    [2]         [3]
	    String[] itemTokens = itemAsText.split(DELIMITER);

	    String itemID = itemTokens[0];

	    Item itemFromFile = new Item(itemID);

	    // However, there are 3 remaining tokens that need to be set into the
	    // new item object. Do this manually by using the appropriate setters.

        // However, there are 3 remaining tokens that need to be set into the
        // new item object. Do this manually by using the appropriate setters.

	    if (itemTokens.length > 1) {
            itemFromFile.setItemName(itemTokens[1]);
            itemFromFile.setPrice(itemTokens[2]);
            itemFromFile.setCount(itemTokens[3]);
        }

	    // We have now created a item! Return it!
	    return itemFromFile;
	}
	
	private void loadRoster() {
	    Scanner scanner = null;

	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(ITEMS_FILE)));
	    } catch (FileNotFoundException e) {
	        System.out.println(e);
	    }
	    // currentLine holds the most recent line read from the file
	    String currentLine;
	    // currentStudent holds the most recent student unmarshalled
	    Item currentItem;
	    // Go through ITEMS_FILE line by line, decoding each line into a 
	    // Item object by calling the unmarshallItem method.
	    // Process while we have more lines in the file
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // unmarshall the line into a Student
	        currentItem = unmarshallStudent(currentLine);

	        // We are going to use the student id as the map key for our student object.
	        // Put currentStudent into the map using student id as the key
	        items.put(currentItem.getId(), currentItem);
	    }
	    // close scanner
	    scanner.close();
	}
	
	private String marshallItem(Item item) {
        // We need to turn a item object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // ItemId::Name::Cost::Inventory::

        // Start with the ItemID, since that's supposed to be first.
        String itemAsText = item.getId() + DELIMITER;

        // add the rest of the properties in the correct order:
        // get item name
        itemAsText += item.getItemName() + DELIMITER;

        // get cost
        itemAsText += item.getPrice() + DELIMITER;

        // get inventory
        itemAsText += item.getCount() + DELIMITER;

        // We have now turned a item to text! Return it!
        return itemAsText;
    }

    /**
     * Writes all items in the item out to a item file.
     * @throws VendingPersistenceException 
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeLibrary() {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
        	System.out.println(e);
        }

        // Write out the item objects to the item file.
        String ItemAsText;
        List<Item> itemList = this.displayAllItems();
        for (Item currentItem : itemList) {
            // turn a item into a String
        	ItemAsText = marshallItem(currentItem);
            // write the item object to the file
            out.println(ItemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

	@Override
	public Item addItem(String itemID, Item item) {
	    loadRoster();
	    Item newItem = items.put(itemID, item);
	    writeLibrary();
	    return newItem;
	}


}
