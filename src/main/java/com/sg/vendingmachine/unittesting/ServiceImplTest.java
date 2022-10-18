package com.sg.vendingmachine.unittesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.sg.vendingmachine.dao.AuditDao;
import com.sg.vendingmachine.dao.AuditDaoImpl;
import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.DaoFileImpl;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.SeriveLayerImpl;
import com.sg.vendingmachine.service.ServiceLayer;
import com.sg.vendingmachine.service.VendingInsufficientFundsException;


import java.math.BigDecimal;

import com.sg.vendingmachine.dao.AuditDao;
import com.sg.vendingmachine.dao.AuditDaoImpl;
import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.DaoFileImpl;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.SeriveLayerImpl;
import com.sg.vendingmachine.service.ServiceLayer;
import com.sg.vendingmachine.service.VendingNoItemInventoryException;

public class ServiceImplTest {
	
    Dao dao = new DaoStubImpl();
    AuditDao auditDao = new AuditDaoStubImpl();
    ServiceLayer service = new SeriveLayerImpl(dao, auditDao);

    public ServiceImplTest() {
    	
    }
    
    @Test
    public void testDisplayItems() throws Exception {
        Item testProduct = new Item("E1");
        testProduct.setItemName("Test Product");
        testProduct.setPrice("0.50");
        testProduct.setCount("5");

        // ACT & ASSERT
        assertEquals(1, service.displayAllItems().size(),
                "Should only have one item.");
        assertTrue(service.displayAllItems().contains(testProduct),
                "The one item should be Test Product.");
    }
    
    // test updating the inventory
    @Test
    public void testUpdateInventory() throws Exception {
        // ARRANGE
        Item testProduct = new Item("E1");
        testProduct.setItemName("Test Product");
        testProduct.setPrice("0.50");
        testProduct.setCount("5");
        
        // Above values already in default constructor
        Item returnedItem = service.selectItem("E1", BigDecimal.valueOf(3.00).multiply(BigDecimal.valueOf(100)));
        // ACT & ASSERT
        service.updateInventory("E1");
        assertEquals(4, Integer.parseInt(returnedItem.getCount()), " The item should have one less in inventory, should have 4 left");
    }
    
    // test insufficientFunds Exception
    @Test
    public void testInsufficientFunds() throws Exception {
        // ARRANGE
        Item testProduct = new Item("E1");
        testProduct.setItemName("Test Product");
        testProduct.setPrice("0.50");
        testProduct.setCount("5");

        Item returnedItem = service.selectItem("E1", BigDecimal.valueOf(3.00));

        // ACT 
        try {
            service.selectItem(testProduct.getId(), BigDecimal.valueOf(0.25));
        } catch (VendingInsufficientFundsException e) {
            // ASSERT
            fail("Not enough funds has been added. VendingInsufficientFundsException was thrown.");
        }
    }
    
    // test getting cost of them chosen item
    @Test
    public void testGetItemCost() throws Exception {
        // ARRANGE
        Item testProduct = new Item("E1");
        testProduct.setItemName("Test Product");
        testProduct.setPrice("0.50");
        testProduct.setCount("5");

        // ACT & ASSERT
        BigDecimal itemCost = service.calculateItemCost("E1");
        assertEquals(BigDecimal.valueOf(50.00).setScale(2, RoundingMode.CEILING), itemCost, "Should have recieved the item cost of 0.50 or 50 pennies");
    }
    
}
