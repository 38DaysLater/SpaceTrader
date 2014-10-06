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
    private int techLevel;
    private RandConditions randCond1;
    private Hashtable<String, Item> priceList;
    private final int NUM_ITEMS = 5;

/**
 * This is the constructor. It generates the inventory
 * @param tech level and resource level
 */
    public Market(int techLevel, int resourceLevel) {
        this.techLevel = techLevel;
        inventory = new Inventory();
        inventory.setCapacity(100000000); //marketplace can hold whatever it wants
        inventory.addToBalance(1000);
        Random rand = new Random();
        //keeps track of the prices of each item
        priceList = new Hashtable<String,Item>();
               
        
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
                inventory.add(item.getName(), NUM_ITEMS);
            }
        }
    }
    
    //Player is trying to buy something
    //Returns false if unable to do so or true if transaction is valid
    public String sellItem(String itemName, Character player, Integer quantityWanted) {
        int price = inventory.getItemPrice(itemName);
        int quantityAvailable = inventory.getItemCount(itemName);
        Inventory playerInventory = player.getInventory();
        int playerCapacity = playerInventory.getCapacity();
        int playerNumItemsHas = playerInventory.totalItemCount();
        
        if(quantityWanted < 0) {
            return "Only positive integers please";
        }
        
        if(quantityWanted > quantityAvailable){
            //System.out.println("Vendor does not have enough to sell");
            return "Vendor does not have enough to sell";
        } else if(price*quantityWanted > playerInventory.getBalance()) {
            //System.out.println("Insufficient sales by player");
            return "Insufficient sales by player";
        } else if(playerInventory.totalItemCount() + quantityWanted > playerInventory.getCapacity()){
            //System.out.println("Player cannot hold that many items");
            return "Player cannot hold that many items";
        } else if(playerCapacity < playerNumItemsHas + quantityWanted){
            String returnString = "You cannot hold that many items \n";
            returnString = returnString + "You have " + playerNumItemsHas + " items and can only carry " + playerCapacity + " items.";
            return returnString;
        } else if(quantityWanted instanceof Integer){
            return "That's not an integer. Please try again.";
            
        }
        
        //adjust balances: player loses money, vendor gets money
        playerInventory.subtractFromBalance(quantityWanted*price);
        inventory.addToBalance(quantityWanted * price);
        
        //adjust items in inventories: Player gets item, vendor loses item
        playerInventory.add(itemName, quantityWanted);
        inventory.removeItem(itemName, quantityWanted);
        
        return null;
    }
    
    //Player is trying to sell something
    //Returns false if unable to do so or true if transaction is valid
    public String buyItem(String itemName, Character player, Integer quantitySelling) {
        Inventory playerInventory = player.getInventory();
        int quantityAvailable = playerInventory.getItemCount(itemName);
        int price = playerInventory.getItemPrice(itemName);

        
        if(quantitySelling < 0) { 
            return "Input positive integers only";
        }
        
        if(quantitySelling > quantityAvailable){
            //System.out.println("Player does not have enough to sell");
            return "Player does not have enough to sell";
        } else if(price*quantitySelling > inventory.getBalance()) {
            //System.out.println("Vendor has insufficient funds");
            return "Vendor has insufficient funds";
        } else if (quantitySelling instanceof Integer) {
            return "That's not an integer. Please try again.";
        }
        
        //adjust balances: Player gets money, vendor losing

        playerInventory.addToBalance(quantitySelling * price);
        inventory.subtractFromBalance(quantitySelling * price);
        
        //adjust items in inventories: Player lose item, vendor gain item
        playerInventory.removeItem(itemName, quantitySelling);
        inventory.add(itemName, quantitySelling);
        
        
        return null;
        
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
