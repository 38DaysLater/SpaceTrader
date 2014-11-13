package spacetrader;

import java.util.Random;
import java.io.Serializable;

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
 * Var (variance is the maximum percentage that the price can vary above or
 * below the base)
 * IE (Radical price increase event)
 * CR (When this condition is present, the price of this resource is
 * unusually low)
 * ER (When this condition is present, the resource is expensive)
 * @author lsmoore
 */
public class Item implements Serializable {
    //CHECKSTYLE: OFF
    private static final long serialVersionUID = 1;
    private String name;
    private int basePrice;
    private int MTLP, MTLU, TTP, IPL, var;
    private RandConditions IE, CR, ER;
    //CHECKSTYLE: ON

 /**
 * This is the constructor. It establishes all of the parameters discussed above
 * @param mtlp
 * @param mtlu
 * @param ttp
 * @param baseP
 * @param ipl
 * @param var
 * @param ie
 * @param cr
 * @param er
 * @param n
 */

    public Item(int mtlp,int mtlu,int ttp, int baseP, int ipl, int var,
                RandConditions ie, RandConditions cr, RandConditions er,
                String n) {
        name = n;
        basePrice = baseP;
        MTLP = mtlp;
        MTLU = mtlu;
        TTP = ttp;
        IPL = ipl;
        this.var = var;
        IE = ie;
        CR = cr;
        ER = er;
    }

/**
 * Calculates the final price according to a formula.
 * @param rc1 a random condition
 * @param techLevel
 * @return calculation
 */

    public int calcFinalPrice(RandConditions rc1, int techLevel) {
        double mult = calcCondtionMultiplyer(rc1);
        Random rand = new Random();
        return Math.abs((int) mult * basePrice + 3*2 *
                (IPL * Math.abs(techLevel - MTLP)) + rand.nextInt(var));
    }
/**
 * Gets the name.
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

    // public int getFinalPrice() {
    //     return finalPrice;
    // }

    // public void setFinalPrice(int number) {
    //     finalPrice = number;
    // }

/**
 * A helper method that determines the effect of the random condition.
 * @param rc the random condition
 * @return mult a double of the multiplier used to calculate the final price
 */

    private double calcCondtionMultiplyer(RandConditions rc) {
        double mult = 1;

        if (rc == IE) {
            mult = mult * 3;
        } else if (rc == CR) {
            mult = mult * .5;
        } else if (rc == ER) {
            mult =  mult * 1.5;
        }

        return mult;
    }

    // @Override
    // public boolean equals(Object ob1) {
    //     if (ob1 instanceof Item){
    //         Item it2 = (Item) ob1;
    //         if (name.equals(it2.name) && basePrice == it2.basePrice && finalPrice) {
    //             return true;
    //         }
    //         return false;
    //     }
    //     return false;
    // }

/**
 * retrieves the Minimum Tech Level to Produce.
 * @return an int of the MTLP
 */
    public int getMTLP() {
        return MTLP;
    }
/**
 * retrieves the Minimum Tech Level to Use.
 * @return an int of the MTLU
 */

    public int getMTLU() {
        return MTLU;
    }
/**
 * retrieves the TTP.
 * @return an int of the TTP
 */
    public int getTTP() {
        return TTP;
    }
/**
 * retrieves the IPL.
 * @return an int of the IPL
 */

    public int getIPL() {
        return IPL;
    }
}
