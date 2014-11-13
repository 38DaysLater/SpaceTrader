/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author alixp_000
 */
public class MarketSellItemTest {
    Character player = new Character("Test Character", 10, 10, 10, 10);
    Inventory marketInventory;
    Inventory playerInventory;
    ArrayList<String> elligibleItems;
    Market market;
    int techLevel;
    Planet planet;
    Hashtable<String, Integer> priceList;

    
    @Before
    public void setUp() {
        market = new Market(2, 2, planet);
        marketInventory = market.getInventory();
        marketInventory.setCapacity(25);
        playerInventory = player.getInventory();
        playerInventory.setCapacity(25);
        elligibleItems = Items.getElligibleItems(techLevel);
        priceList = marketInventory.getPriceList();
    }
    
    
    @Test
    public void testSellZeroItems() {
        int playerFoodCountBefore = playerInventory.getItemCount("Food");
        int marketFoodCountBefore = marketInventory.getItemCount("Food");
        int playerMoneyBefore = playerInventory.getBalance();
        int marketMoneyBefore = marketInventory.getBalance();
        
        
        market.sellItem("Food", player, 0);
        
        
        assertEquals(playerInventory.getItemCount("Food"),
                playerFoodCountBefore);
        assertEquals(marketInventory.getItemCount("Food"),
                marketFoodCountBefore);
        assertEquals(playerMoneyBefore, playerInventory.getBalance());
        assertEquals(marketMoneyBefore, marketInventory.getBalance());
    }
    
    @Test
    public void testSellOneItem() {
        int cost = priceList.get("Water");
        int playerWaterCountBefore = playerInventory.getItemCount("Water");
        int marketWaterCountBefore = marketInventory.getItemCount("Water");
        int playerMoneyBefore = playerInventory.getBalance();
        int marketMoneyBefore = marketInventory.getBalance();
        
        market.sellItem("Water", player, 1);
        
        assertEquals(playerInventory.getItemCount("Water"),
                playerWaterCountBefore + 1);
        assertEquals(marketInventory.getItemCount("Water"),
                marketWaterCountBefore - 1);
        assertEquals(playerMoneyBefore - (cost), playerInventory.getBalance());
        assertEquals(marketMoneyBefore + (cost), marketInventory.getBalance());
        
    }
    
    @Test
    public void testSellTwoItems() {
        int cost = priceList.get("Ore");
        int playerOreCountBefore = playerInventory.getItemCount("Ore");
        int marketOreCountBefore = marketInventory.getItemCount("Ore");
        int playerMoneyBefore = playerInventory.getBalance();
        int marketMoneyBefore = marketInventory.getBalance();

        
        market.sellItem("Ore", player, 2);
        
        assertEquals(playerInventory.getItemCount("Ore"),
                playerOreCountBefore + 2);
        assertEquals(marketInventory.getItemCount("Ore"),
                marketOreCountBefore - 2);
        assertEquals(playerMoneyBefore - (cost * 2),
                playerInventory.getBalance());
        assertEquals(marketMoneyBefore + (cost * 2),
                marketInventory.getBalance());
    }
    
    @Test
    public void testSellThreeItems() {
        int cost = priceList.get("Furs");
        int playerFursCountBefore = playerInventory.getItemCount("Furs");
        int marketFursCountBefore = marketInventory.getItemCount("Furs");
        int playerMoneyBefore = playerInventory.getBalance();
        int marketMoneyBefore = marketInventory.getBalance();
       
        market.sellItem("Furs", player, 3);
        
        assertEquals(playerInventory.getItemCount("Furs"),
                playerFursCountBefore + 3); 
        assertEquals(marketInventory.getItemCount("Furs"),
                marketFursCountBefore - 3);
        assertEquals(playerMoneyBefore - (cost * 3),
                playerInventory.getBalance());
        assertEquals(marketMoneyBefore + (cost * 3),
                marketInventory.getBalance());
    }
}
