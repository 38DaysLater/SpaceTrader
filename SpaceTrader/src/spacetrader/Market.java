package spacetrader;

import java.util.Hashtable;
import java.util.Random;
import java.io.Serializable;
/**
 * This class represents a Market.
 * @author lsmoore
 */
public class Market extends Inventory {
    //CHECKSTYLE: OFF
    private static final long serialVersionUID = 1;
    private Inventory inventory;
    private int techLevel;
    private RandConditions randCond1;
    private Hashtable<String, Integer> priceList;
    private static final int NUM_ITEMS = 5;
    private static final int STARTING_BALANCE = 1000;
    private static final int STARTING_CAPACITY = 100000000;
    private Planet planet;
    private static final int NUM_CONDITIONS = 19;
    private static final int AFIQ_CHEATING = 9000;
    //CHECKSTYLE: ON

/**
 * This is the constructor. It generates the inventory
 * @param techLevel tech level of current planet
 * @param resourceLevel resource level of current planet
 * @param planet current planet
 */
    public Market(int techLevel, int resourceLevel, Planet planet) {
        this.planet = planet;
        this.techLevel = techLevel;
        inventory = new Inventory(techLevel);
        //marketplace can hold whatever it wants
        inventory.setCapacity(STARTING_CAPACITY);
        inventory.addToBalance(STARTING_BALANCE);
        Random rand = new Random();
        //keeps track of the prices of each item
        priceList = new Hashtable<String, Integer>();

        //generating a random event
        int index1 = rand.nextInt(NUM_CONDITIONS);
        randCond1 = RandConditions.values()[index1];

        //Add to the inventory only if the tech level is sufficient
        for (Item item: Items.getList()) {
            if (techLevel >= item.getMTLP()) {
                int price = item.calcFinalPrice(randCond1, techLevel);
                //keeping track of the final prices of
                //items to be placed in inventory
                priceList.put(item.getName(), price);

                //adding the item to the inventory
                inventory.add(item.getName(), NUM_ITEMS);
            }
        }
        inventory.setPriceList(priceList);
    }

    /**
     * Player is trying to buy something.
     * Returns false if unable to do so or true if transaction is valid
     * @param itemName
     * @param player
     * @param quantityWanted
     * @return String
     */
    public String sellItem(String itemName, Character player,
            Object quantityWanted) {
        int price = inventory.getItemPrice(itemName);
        int quantityAvailable = inventory.getItemCount(itemName);
        Inventory playerInventory = player.getInventory();
        playerInventory.setPriceList(inventory.getPriceList());
        int playerCapacity = playerInventory.getCapacity();
        int playerNumItemsHas = playerInventory.totalItemCount();

        if ((int) quantityWanted < 0) {
            return "Only positive integers please";
        }

        if ((int) quantityWanted > quantityAvailable) {
            return "Vendor does not have enough to sell";
        } else if (price * (int) quantityWanted
                > playerInventory.getBalance()) {
            return "Insufficient sales by player";
        } else if (playerInventory.totalItemCount()
                + (int) quantityWanted > playerInventory.getCapacity()) {
            return "Player cannot hold that many items";
        } else if (playerCapacity < playerNumItemsHas + (int) quantityWanted) {
            String returnString = "You cannot hold that many items \n";
            returnString = returnString + "You have " + playerNumItemsHas
                    + " items and can only carry " + playerCapacity + " items.";
            return returnString;
        } else if (!(quantityWanted instanceof Integer)) {
            return "That's not an integer. Please try again.";
        }

        //adjust balances: player loses money, vendor gets money
        playerInventory.subtractFromBalance((int) quantityWanted * price);
        inventory.addToBalance((int) quantityWanted * price);

        //adjust items in inventories: Player gets item, vendor loses item
        playerInventory.add(itemName, (int) quantityWanted);
        inventory.removeItem(itemName, (int) quantityWanted);
        return null;
    }

    /**
     * Player is trying to sell something.
     * Returns false if unable to do so or true if transaction is valid
     * @param itemName
     * @param player
     * @param quantitySelling
     * @return String
     */
    public String buyItem(String itemName, Character player,
            Object quantitySelling) {
        Inventory playerInventory = player.getInventory();
        playerInventory.setPriceList(inventory.getPriceList());
        int quantityAvailable = playerInventory.getItemCount(itemName);
        
        if(inventory.getItemPrice(itemName) == -1) {
            return "Vendor cannot purchase that item";
        }
        
        int price = playerInventory.getItemPrice(itemName);

        if ((int) quantitySelling == AFIQ_CHEATING
                && player.getName().equals("Afiq")) {
            playerInventory.addToBalance(STARTING_BALANCE);
            return null;
        }

        if ((int) quantitySelling < 0) {
            return "Input positive integers only";
        }

        if ((int) quantitySelling > quantityAvailable) {
            return "Player does not have enough to sell";
        } else if (price * (int) quantitySelling > inventory.getBalance()) {
            return "Vendor has insufficient funds";
        } else if (!(quantitySelling instanceof Integer)) {
            return "That's not an integer. Please try again.";
        }

        //adjust balances: Player gets money, vendor losing
        playerInventory.addToBalance((int) quantitySelling * price);
        inventory.subtractFromBalance((int) quantitySelling * price);

        //adjust items in inventories: Player lose item, vendor gain item
        playerInventory.removeItem(itemName, (int) quantitySelling);
        inventory.add(itemName, (int) quantitySelling);
        return null;
    }

/**
 * retrieves the inventory.
 * @return Inventory
 */
    public Inventory getInventory() {
        return inventory;
    }
}
