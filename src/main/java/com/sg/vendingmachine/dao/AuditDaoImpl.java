package com.sg.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditDaoImpl implements AuditDao{

    private static final String AUDIT_FILE = "audit.txt";

	@Override
	public void writeAuditeEntry(String entry) throws VendingPersistenceException {
		
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
		} catch (IOException e) {
            throw new VendingPersistenceException("Information entered could not be persist.", e);
		}
		
		LocalDateTime timeStamp = LocalDateTime.now();
		out.println(timeStamp.toString() + " : " + entry);
		out.flush();
	}

}
