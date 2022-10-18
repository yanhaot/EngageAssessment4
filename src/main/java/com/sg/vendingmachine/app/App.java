package com.sg.vendingmachine.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sg.vendingmachine.controller.Controller;
import com.sg.vendingmachine.dao.AuditDao;
import com.sg.vendingmachine.dao.AuditDaoImpl;
import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.DaoFileImpl;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.service.SeriveLayerImpl;
import com.sg.vendingmachine.service.ServiceLayer;
import com.sg.vendingmachine.service.VendingInsufficientFundsException;
import com.sg.vendingmachine.service.VendingNoItemInventoryException;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOImpl;
import com.sg.vendingmachine.ui.View;

public class App {

	public static void main(String[] args) throws VendingPersistenceException, VendingInsufficientFundsException, VendingNoItemInventoryException{
//        UserIO myIo = new UserIOImpl();
//        View myView = new View(myIo);
//        Dao myDao = new DaoFileImpl();
//        AuditDao myAuditDao = new AuditDaoImpl();
//        ServiceLayer myService = new SeriveLayerImpl(myDao, myAuditDao);
//        Controller controller = new Controller(myService, myView);
//        controller.run();
		
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
             Controller controller = 
                ctx.getBean("controller", Controller.class);
             controller.run();
             
	}

}
