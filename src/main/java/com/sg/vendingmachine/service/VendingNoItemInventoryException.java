package com.sg.vendingmachine.service;

public class VendingNoItemInventoryException extends Exception{
    public VendingNoItemInventoryException(String message){
        super(message);
    }
    
    public VendingNoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
