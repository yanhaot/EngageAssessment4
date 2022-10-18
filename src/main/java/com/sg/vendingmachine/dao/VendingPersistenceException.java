package com.sg.vendingmachine.dao;

public class VendingPersistenceException extends Exception{
	    public VendingPersistenceException(String message){
	        super(message);
	    }
	    
	    public VendingPersistenceException(String message, Throwable cause){
	        super(message, cause);
	    }
}
