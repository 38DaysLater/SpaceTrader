
package spacetrader;

import java.util.Random;
import java.io.Serializable;

/**
 * This item represents the goal of the game: the final mystery item.
 * The player can only buy this when he gets enough money
 * Uses the delegation design pattern
 * @author lsmoore
 */
public class FinalMysteryItem {
    private static final long serialVersionUID = 1;
    private static String[] listOfPossibleNames = {"Secret to Gravity",
        "Interstellar Explanation", "Quantum Mechanics meets Relativity Model",
        "A in 2340", "BMW M3", "Audi R8", "Understanding the Opposite Sex"};
    private Item item;
    private static final int PRICE_TO_BUY = 10000;
    
    /** The default constructor.  Establishes the name and price of the item 
     */
    public FinalMysteryItem() {
        int randomIndex = new Random().nextInt(7);
        item = new Item(listOfPossibleNames[randomIndex], PRICE_TO_BUY);
    }
    
    /** The method returns the price of the item.
     * @return int: the price of the item
     */
    
    public static int getPrice() {
        return PRICE_TO_BUY;
    }
    
    /** The method returns the price of the item.
     * @return String: the name of the item
     */
    
    public String getName() {
        return item.getName();
    }
    
}
