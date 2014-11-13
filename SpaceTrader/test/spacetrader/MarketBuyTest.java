package spacetrader;

import java.util.ArrayList;
import java.util.Hashtable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import java.util.Hashtable;






/**
 *
 * @author AfiqAzaibi
 */
public class MarketBuyTest {
    Character testCharacter = new Character("Test Character", 10, 10, 10, 10);
    Inventory marketInventory;
    Inventory playerInventory;
    ArrayList<String> elligibleItems;
    Market market;
    int techLevel;
    SolarSystem ss;
    Planet planet;
    Hashtable<String,Integer> priceList;
    
    @Before
    public void setUp() {
        market = new Market(2, 2, planet);
        marketInventory = market.getInventory();
        playerInventory = testCharacter.getInventory();
        //player starts with 3000 moneys and 0 capacity
        elligibleItems = Items.getElligibleItems(techLevel);
        priceList = marketInventory.getPriceList();
    }
    
    
    /**
     * player is trying to sell item that he/she does not have.
     * aka market is trying to buy it.
     */
    @Test
    public void testBuyWithNoItem(){
        int cost = priceList.get("Water");
        
        int playerMoneyBefore = playerInventory.getBalance();
        int marketMoneyBefore = marketInventory.getBalance();
        assertEquals(playerInventory.getItemCount("Water"), 0);
        market.buyItem("Water", testCharacter, 5);
        
        //item was properly received
        assertEquals(playerInventory.getItemCount("Water"), 0);
        
        //balances are same since no transaction happened
        assertEquals(playerMoneyBefore, playerInventory.getBalance());
        assertEquals(marketMoneyBefore, marketInventory.getBalance());        

    }
    
    /**
     * Test sell when player has item.
     */
    @Test
    public void testBuyWithItem(){
        int cost = priceList.get("Water");
        int startingQuantity = marketInventory.getItemCount("Water");
        playerInventory.setCapacity(5);
        
        int playerMoneyBefore = playerInventory.getBalance();
        int marketMoneyBefore = marketInventory.getBalance();
        playerInventory.add("Water", 5);
        
        market.buyItem("Water", testCharacter, 5);
        

        //item was properly received therefore player has none left
        assertEquals(playerInventory.getItemCount("Water"), 0);
        assertEquals(marketInventory.getItemCount("Water"), startingQuantity + 5);
        
        
        //balances should be updated
        assertEquals(playerMoneyBefore + cost * 5, playerInventory.getBalance());
        assertEquals(marketMoneyBefore - cost * 5, marketInventory.getBalance());  
        
    }
    
    
    /**
     * player trying to sell more than he/she has.
     */
    @Test
    public void testBuyWithInsufficientItems(){
        playerInventory.setCapacity(5);
        playerInventory.add("Food", 3);
        
        int playerStartFood = playerInventory.getItemCount("Food");
        int marketStartFood = marketInventory.getItemCount("Food");
        
        int playerStartBalance = playerInventory.getBalance();
        int marketStartBalance = marketInventory.getBalance();
        
        market.buyItem("Food", testCharacter, 5);
        
        assertEquals(playerStartFood, playerInventory.getItemCount("Food"));
        assertEquals(marketStartFood, marketInventory.getItemCount("Food"));
        assertEquals(playerStartBalance, playerInventory.getBalance());
        assertEquals(marketStartBalance, marketInventory.getBalance());
    }
    
    
    
    
    
    /**
     * User inputs invalid input like negative number.
     */
    @Test
    public void testBuyWithInvalidInputNegativeNumber(){
        int playerWaterBefore = playerInventory.getItemCount("Water");
        int marketWaterBefore = marketInventory.getItemCount("Water");
        market.buyItem("Water", testCharacter, -99);
        
        //no transaction should have occured
        assertEquals(playerWaterBefore, playerInventory.getItemCount("Water"));
        assertEquals(marketWaterBefore, marketInventory.getItemCount("Water"));
        
        
    }
    
        
    
    /**
     * Test buy when vendor doesn't have enough money.
     */    
    @Test
    public void testBuyVendorInsufficientFunds(){
        playerInventory.setCapacity(10);
        playerInventory.add("Food", 10);
        int playerStartFood = playerInventory.getItemCount("Food");
        int playerStartMoney = playerInventory.getBalance();
        
        marketInventory.subtractFromBalance(3000);
        int marketStartFood = marketInventory.getItemCount("Food");
        int marketStartMoney = marketInventory.getBalance();
        
        market.buyItem("Food", testCharacter, 10);
        
        //everything should be the same since transaction didn't happen
        assertEquals(playerInventory.getItemCount("Food"), playerStartFood);
        assertEquals(marketInventory.getItemCount("Food"), marketStartFood);
        assertEquals(playerInventory.getBalance(), playerStartMoney);
        assertEquals(marketInventory.getBalance(), marketStartMoney);
        
    }
    
    
    /**
     * Test buy when vendor doesn't have enough money.
     */    
    @Test
    public void testBuyVendorInsufficientTechLevel(){
        
        playerInventory.setCapacity(5);
        playerInventory.add("Robots", 5);
        
        marketInventory.addToBalance(500000);
        
        int playerStartBalance = playerInventory.getBalance();
        int marketStartBalance = marketInventory.getBalance();
        
        int playerStartRobots = playerInventory.getItemCount("Robots");
        int marketStartRobots = marketInventory.getItemCount("Robots");
        
        System.out.println(priceList.get("Robots"));
        market.buyItem("Robots", testCharacter, 5);
        
        
        assertEquals(playerStartBalance, playerInventory.getBalance());
        assertEquals(marketStartBalance, marketInventory.getBalance());
        assertEquals(playerStartRobots, playerInventory.getItemCount("Robots"));
        assertEquals(marketStartRobots, marketInventory.getItemCount("Robots"));
        
        
    }
    
    
    
    
}
