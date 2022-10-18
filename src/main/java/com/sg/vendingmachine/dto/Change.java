package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

enum Coins {
    QUARTER, DIME, NICKEL, PENNY
}

public class Change {
	
	private BigDecimal quarter, dime, nickel, penny;

    enum Coins {
        QUARTER(25), 
        DIME(10), 
        NICKEL(5), 
        PENNY(1);
        
        final double amount;

        Coins(double amount) {
            this.amount = amount;
        } 
        
        double getAmount(){
            return amount;
        }
    }

    public BigDecimal getDime() {
        return dime;
    }

    public BigDecimal getNickel() {
        return nickel;
    }

    public BigDecimal getPenny() {
        return penny;
    }

    public BigDecimal getQuarter() {
        return quarter;
    }
    
    public HashMap<String, String> getChange(int pennies){
       
        // BigDecimal is to create precision
        BigDecimal totoalPennies = new BigDecimal(pennies);
       
        // Value that is returned by BigDecimal.valueOf is equal to that resulting from invocation of Double.toString(double).
        quarter = totoalPennies.divide(BigDecimal.valueOf(25.0)).setScale(0, RoundingMode.HALF_DOWN); 
        totoalPennies = totoalPennies.remainder(BigDecimal.valueOf(25.0)).setScale(0, RoundingMode.HALF_DOWN);
        
        
        dime = totoalPennies.divide(BigDecimal.valueOf(10.0)).setScale(0, RoundingMode.HALF_DOWN); 
        totoalPennies = totoalPennies.remainder(BigDecimal.valueOf(10.0)).setScale(0, RoundingMode.HALF_DOWN);
        
        
        nickel = totoalPennies.divide(BigDecimal.valueOf(5.0)).setScale(0, RoundingMode.HALF_DOWN); 
        totoalPennies = totoalPennies.remainder(BigDecimal.valueOf(5.0)).setScale(0, RoundingMode.HALF_DOWN);
        
        
        penny = totoalPennies;
        
        
        HashMap<String, String> changeMap = new HashMap<String, String>();
        changeMap.put("quarter", quarter.toString());
        changeMap.put("dime", dime.toString());
        changeMap.put("nickel", nickel.toString());
        changeMap.put("penny", penny.toString());
       
        return changeMap;
    }
}
