package com.sg.vendingmachine.dao;

public interface AuditDao {
	
	public void writeAuditeEntry(String entry) throws VendingPersistenceException;
	
}
