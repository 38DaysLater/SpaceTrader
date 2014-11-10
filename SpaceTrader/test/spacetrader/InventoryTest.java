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
 * @author lsmoore
 */
public class InventoryTest {
    
    Inventory inventory;
    ArrayList<String> elligibleItems;
    int techLevel;
    
    @Before
    public void setUp() {
        techLevel = new Random().nextInt(8);
        inventory = new Inventory(techLevel);
        elligibleItems = Items.getElligibleItems(techLevel);
    }
    

    /**
     * Test of setPriceList method, of class Inventory.
     */
    @Test
    public void testAdd1() {
        inventory.add("Water");
        inventory.add("Ore");
        inventory.add("Ore");
        assertEquals(inventory.getItemCount("Water"), 1);
        assertEquals(inventory.getItemCount("Ore"), 2);
        if (elligibleItems.contains("Robots")){
            assertEquals(inventory.getItemCount("Robots"), 0);
        }
        assertEquals(inventory.getItemCount("Robots"), -1);
        assertEquals(inventory.getItemCount("Name not in the list"), -1);
    }
    
    @Test
    public void testAdd2() {
        inventory.add("Water");
        inventory.add("Ore");
        
        for (int i = 0; i < 100; i++) {
            inventory.add("Furs");
        }
        
        assertEquals(inventory.getItemCount("Water"), 1);
        assertEquals(inventory.getItemCount("Ore"), 1);
        assertEquals(inventory.getItemCount("Furs"), 98);
    }

}
