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
        //inventory.setCapacity(100000000); //marketplace can hold whatever it wants
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
    public String sellItem(String itemName, Character player, int quantityWanted) {
        int price = inventory.getItemPrice(itemName);
        int quantityAvailable = inventory.getItemCount(itemName);
        Inventory playerInventory = player.getInventory();
        
        if(quantityWanted > quantityAvailable){
            //System.out.println("Vendor does not have enough to sell");
            return "Vendor does not have enough to sell";
        } else if(price*quantityWanted > playerInventory.getBalance()) {
            //System.out.println("Insufficient sales by player");
            return "Insufficient sales by player";
        } else if(playerInventory.totalItemCount() + quantityWanted > playerInventory.getCapacity()){
            //System.out.println("Player cannot hold that many items");
            return "Player cannot hold that many items";
        } 
        
        //adjust balances
        playerInventory.setBalance(-1*quantityWanted*price);
        inventory.setBalance(quantityWanted*price);
        
        //adjust items in inventories
        playerInventory.add(itemName, quantityWanted);
        inventory.add(itemName, -1 * quantityWanted);
        
        
        return null;
    }
    
    //Player is trying to sell something
    //Returns false if unable to do so or true if transaction is valid
    public String buyItem(String itemName, Character player, int quantitySelling) {
        int price = inventory.getItemPrice(itemName);
        Inventory playerInventory = player.getInventory();
        int quantityAvailable = playerInventory.getItemCount(itemName);

        
        if(quantitySelling > quantityAvailable){
            //System.out.println("Player does not have enough to sell");
            return "Player does not have enough to sell";
        } else if(price*quantitySelling > inventory.getBalance()) {
            //System.out.println("Vendor has insufficient funds");
            return "Vendor has insufficient funds";
        }
        
        //adjust balances
        playerInventory.setBalance(quantitySelling * price);
        inventory.setBalance(-1 * quantitySelling * price);
        
        //adjust items in inventories
        playerInventory.add(itemName, -1 *  quantitySelling);
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
