package spacetrader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Hashtable;
import java.util.Random;
/**
 * This class represents a Market. 
 * @author lsmoore
 */
public class Market {
    private Inventory inventory;
    private int techLevel, money;
    private RandConditions randCond1;
    private Hashtable<String, Item> priceList;
    private final int NUM_ITEMS = 5;
    private final int MONEY_MULT = 50;

/**
 * This is the constructor. It generates the inventory
 * @param tech level and resource level
 */
    public Market(int techLevel, int resourceLevel) {
        this.techLevel = techLevel;
        inventory = new Inventory();
        Random rand = new Random();
        //keeps track of the prices of each item
        priceList = new Hashtable<String,Item>();
        
        //preallocating a certain amount of money for the store
        money = MONEY_MULT * resourceLevel;
        
        
        //generating a random event
        int index1 = rand.nextInt(19);
        randCond1 = RandConditions.values()[index1];
        //
        
        //Add to the inventory only if the tech level is sufficient
        for (Item item: Items.getList()) {
            if (techLevel >= item.getMTLP()) {
                item.calcFinalPrice(randCond1, techLevel);
                //keepign track of the final prices of itme to be placed in inventory
                priceList.put(item.getName(), item);
                
                //adding the item to the inventory
                inventory.add(item, NUM_ITEMS);
            }
        }
    }
    
    //incomplete
    public void sellItem(String itemName, Character player) {
        if (priceList.containsKey(itemName)){
            Item currItem = priceList.get(itemName);
            int price = currItem.getFinalPrice();
            money += price;
            inventory.removeItem(currItem);
        }
    }
    
    //incomplete
    public void BuyItem(Item item, Character player) {
        if (item.getMTLU() < techLevel) {
            
        }
    }
    
/**
 * retrieves the inventory
 * @param none
 * @return the inventory
 */
    public Inventory getInventory() {
        return inventory;
    }
}
