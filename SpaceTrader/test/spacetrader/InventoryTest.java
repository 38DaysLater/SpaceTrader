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
    ArrayList<String> notElligible;
    int techLevel;

    @Before
    public void setUp() {
        techLevel = new Random().nextInt(8);
        inventory = new Inventory(techLevel);
        elligibleItems = Items.getElligibleItems(techLevel);
        notElligible = new ArrayList<String>();
        for (Item item: Items.getList()) {
            String name = item.toString();
            if (!elligibleItems.contains(name)) {
                notElligible.add(name);
            }
        }
    }


    /**
     * Test 1 of the add method. Checks basic information
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

    /**
     * Test 2 of the add method. Checks that the capacity is enforced
     */
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

        if (elligibleItems.contains("Robots")){
            assertEquals(inventory.getItemCount("Robots"), 0);
        }
        assertEquals(inventory.getItemCount("Robots"), -1);
        assertEquals(inventory.getItemCount("Name not in the list"), -1);
    }

    /**
     * Test 3 of the add method.  Checks when multiple items are added
     */
    @Test
    public void testAdd3() {
        inventory.add("Food", 15);

        if (elligibleItems.contains("Medicine")){
            inventory.add("Medicine", 31);
            assertEquals(31, inventory.getItemCount("Medicine"));
        }
        assertEquals(inventory.getItemCount("Water"), 0);
        assertEquals(inventory.getItemCount("Food"), 15);
        assertEquals(inventory.getItemCount("Not there"), -1);
    }

    /**
     * Test 4 of the add method.  Checks what happens when nothing is added.
     */
    @Test
    public void testAdd4() {
        for (String items: elligibleItems) {
            assertEquals(inventory.getItemCount(items), 0);
        }

        for (String items: notElligible) {
            assertEquals(inventory.getItemCount(items), -1);
        }
    }

    /**
     * Test 5 of the add method.  Checks the inventory when illegible items are added
     */
    @Test
    public void testAdd5() {
        for (String item: notElligible) {
            inventory.add(item, new Random().nextInt(8));
            assertEquals(-1, inventory.getItemCount(item));
        }
    }

    /**
     * Test 6 of the add method.  Checks the inventory when null is added
     */
    @Test
    public void testAdd6() {
        inventory.add(null);
        inventory.add("Water", 4);
        inventory.add(null);
        inventory.add(null, 7);
        inventory.add("Food", 0);

        assertEquals(4, inventory.getItemCount("Water"));
        assertEquals(0, inventory.getItemCount("Food"));
    }

    /**
     * Test 7 of the add method.  Additional null testing
     */
    @Test
    public void testAdd7() {
        inventory.add(null, 100);

        assertEquals(-1, inventory.getItemCount(null));

        for (String items: elligibleItems) {
            assertEquals(inventory.getItemCount(items), 0);
        }

        for (String items: notElligible) {
            assertEquals(inventory.getItemCount(items), -1);
        }
    }



}
