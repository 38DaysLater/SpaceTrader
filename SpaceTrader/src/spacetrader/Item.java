package spacetrader;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class represents an item.  Every item has a:
 * name, 
 * base price, 
 * MTLP (minimum tech level to produce),
 * MTLU (Minimum Tech Level to Use), 
 * TTP (Tech Level which produces the most of this item), 
 * IPL = (Price increase per tech level), 
 * Var (variance is the maximum percentage that the price can vary above or below the base)
 * IE (Radical price increase event)
 * CR (When this condition is present, the price of this resource is unusually low)
 * ER (When this condition is present, the resource is expensive)
 * @author lsmoore
 */
public class Item {
    
    private String name;
    private int basePrice, finalPrice;
    private int MTLP, MTLU, TTP, IPL, Var; 
    private RandConditions IE, CR, ER;
    
 /**
 * This is the constructor. It establishes all of the parameters discussed above
 * @param the MTLP, MTLU, TTP, base price, IPL, var, IE, CR, ER, and name of item
 */
    
    public Item(int mtlp,int mtlu,int ttp, int baseP, int ipl, int var, 
                RandConditions ie, RandConditions cr, RandConditions er, String n) {
        name = n;
        basePrice = baseP;
        MTLP = mtlp;
        MTLU = mtlu;
        TTP = ttp;
        IPL = ipl;
        Var = var;
        IE = ie;
        CR = cr;
        ER = er;
    }

/**
 * Calculates the final price according to a formula
 * @param a random condition and tech
 * @return None
 */
    
    public void calcFinalPrice(RandConditions rc1, int techLevel) {
        double mult = calcCondtionMultiplyer(rc1);
        Random rand = new Random();
        finalPrice = (int) mult * basePrice + 3*2 * (IPL * Math.abs(techLevel - MTLP)) + rand.nextInt(Var);
    }
/**
 * Gets the name
 * @param none
 * @return a string of the name
 */
    
    public String getName() {
        return name;
    }
    
 /**
 * Gets the final price
 * @param none
 * @return an int of the final price
 */
    
    public int getFinalPrice() {
        return finalPrice;
    }
    
    public void setFinalPrice(int number) {
        finalPrice = number;
    }

/**
 * A helper method that determines the effect of the random condition
 * @param the random condition
 * @return a double of the multiplier used to calculate the final price
 */
    
    private double calcCondtionMultiplyer(RandConditions rc){
        double mult = 1;
        
        if (rc == IE) {
            return mult * 3;
        } else if (rc == CR) {
            return mult * .5;
        } else if (rc == ER) {
            return mult * 1.5;
        }
        
        return mult;
    }
    
    @Override
    public boolean equals(Object ob1) {
        if (ob1 instanceof Item){
            Item it2 = (Item) ob1;
            if (name.equals(it2.name)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
/**
 * retrieves the MTLP
 * @param none
 * @return an int of the MTLP
 */
    public int getMTLP() {
        return MTLP;
    }
/**
 * retrieves the MTLU
 * @param none
 * @return an int of the MTLU
 */
    
    public int getMTLU() {
        return MTLU;
    }
/**
 * retrieves the TTP
 * @param none
 * @return an int of the TTP
 */    
    public int getTTP() {
        return TTP;
    }
/**
 * retrieves the IPL
 * @param none
 * @return an int of the IPL
 */
    
    public int getIPL() {
        return IPL;
    }
}
