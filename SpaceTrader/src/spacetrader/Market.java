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
    private Hashtable<String, Integer> priceList;
    private final int NUM_ITEMS = 5;
    private Planet planet;

/**
 * This is the constructor. It generates the inventory
 * @param tech level and resource level
 */
    public Market(int techLevel, int resourceLevel, Planet planet) {
        this.planet = planet;
        this.techLevel = techLevel;
        inventory = new Inventory(techLevel);
        inventory.setCapacity(100000000); //marketplace can hold whatever it wants
        inventory.addToBalance(1000);
        Random rand = new Random();
        //keeps track of the prices of each item
        priceList = new Hashtable<String, Integer>();
               
        
        //generating a random event
        int index1 = rand.nextInt(19);
        randCond1 = RandConditions.values()[index1];
        //
        
        //Add to the inventory only if the tech level is sufficient
        for (Item item: Items.getList()) {
            if (techLevel >= item.getMTLP()) {
                int price = item.calcFinalPrice(randCond1, techLevel);
                //keeping track of the final prices of itme to be placed in inventory
                priceList.put(item.getName(), price);
                
                //adding the item to the inventory
                inventory.add(item.getName(), NUM_ITEMS);
            }
        }
        inventory.setPriceList(priceList);
        System.out.println();
    }
    
    //Player is trying to buy something
    //Returns false if unable to do so or true if transaction is valid
    public String sellItem(String itemName, Character player, Object quantityWanted) {
        int price = inventory.getItemPrice(itemName);
        int quantityAvailable = inventory.getItemCount(itemName);
        Inventory playerInventory = player.getInventory();
        int playerCapacity = playerInventory.getCapacity();
        int playerNumItemsHas = playerInventory.totalItemCount();
        
        if((int)quantityWanted < 0) {
            return "Only positive integers please";
        }
        
        if((int)quantityWanted > quantityAvailable){
            //System.out.println("Vendor does not have enough to sell");
            return "Vendor does not have enough to sell";
        } else if(price*(int)quantityWanted > playerInventory.getBalance()) {
            //System.out.println("Insufficient sales by player");
            return "Insufficient sales by player";
        } else if(playerInventory.totalItemCount() + (int)quantityWanted > playerInventory.getCapacity()){
            //System.out.println("Player cannot hold that many items");
            return "Player cannot hold that many items";
        } else if(playerCapacity < playerNumItemsHas + (int)quantityWanted){
            String returnString = "You cannot hold that many items \n";
            returnString = returnString + "You have " + playerNumItemsHas + " items and can only carry " + playerCapacity + " items.";
            return returnString;
        } else if(!(quantityWanted instanceof Integer)){
            return "That's not an integer. Please try again.";
        }
        
        //adjust balances: player loses money, vendor gets money
        playerInventory.subtractFromBalance((int)quantityWanted*price);
        inventory.addToBalance((int)quantityWanted * price);
        
        //adjust items in inventories: Player gets item, vendor loses item
        playerInventory.add(itemName, (int)quantityWanted);
        inventory.removeItem(itemName, (int)quantityWanted);
        
        return null;
    }
    
    //Player is trying to sell something
    //Returns false if unable to do so or true if transaction is valid
    public String buyItem(String itemName, Character player, Object quantitySelling) {
        Inventory playerInventory = player.getInventory();
        int quantityAvailable = playerInventory.getItemCount(itemName);
        int price = playerInventory.getItemPrice(itemName);

        
        if((int)quantitySelling < 0) { 
            return "Input positive integers only";
        }
        
        if((int)quantitySelling > quantityAvailable){
            //System.out.println("Player does not have enough to sell");
            return "Player does not have enough to sell";
        } else if(price*(int)quantitySelling > inventory.getBalance()) {
            //System.out.println("Vendor has insufficient funds");
            return "Vendor has insufficient funds";
        } else if (!(quantitySelling instanceof Integer)) {
            return "That's not an integer. Please try again.";
        }
        
        //adjust balances: Player gets money, vendor losing

        playerInventory.addToBalance((int)quantitySelling * price);
        inventory.subtractFromBalance((int)quantitySelling * price);
        
        //adjust items in inventories: Player lose item, vendor gain item
        playerInventory.removeItem(itemName, (int)quantitySelling);
        inventory.add(itemName, (int)quantitySelling);
        
        
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
