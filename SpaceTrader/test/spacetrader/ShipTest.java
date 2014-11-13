
package spacetrader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sarah Alsmiller
 */
public class ShipTest {
    
    ArrayList<Ship> ships;
    
    @Before
    public void setUp() {
        Singleton.setCharacter(new Character("Joe",10,10,10,10));
        ships = new ArrayList<Ship>(9);
        //creates an arraylist containing all possible ships in the game
        ships.add(new Ship("Titan 1", 1000, 2,70, 5, 4, 600, 100, 10));
        ships.add(new Ship("Titan 2", 2000, 5, 85, 7, 4, 750, 125, 20));
        ships.add(new Ship("Titan 3", 5000, 100, 10, 10, 5, 999, 150,
                50));
        ships.add(new Ship("Banshee 1", 1000, 2, 25, 5, 5, 600, 40,
                10));
        ships.add(new Ship("Banshee 2", 2000, 5, 15, 5, 7, 750, 50,
                20));
        ships.add(new Ship("Banshee 3", 5000, 10, 5, 5, 10, 999, 60,
                30));
        ships.add(new Ship("Shiny 1", 1000, 0, 50, 3, 4, 500, 70,
                20));
        ships.add(new Ship("Shiny 2", 2000, 0, 30, 4, 5, 700, 80,
                40));
        ships.add(new Ship("Serenity 1", 1000, 0, 70, 5, 3, 600, 60,
                100));
        ships.add(new Ship("Serenity 2", 2000, 0, 75, 5, 2, 700, 70,
                200));
    }
    

 
    /**
     * Tests to ensure that the ship is removing the correct amount of fuel for 
     * an arbitrary distance.
     */
    @Test
    public void testUseFuel() {
        Random roll = new Random();
        int distance = roll.nextInt();
        for( Ship s: ships) {
            //the first parameter in subtract fuel doesn't look like its ever 
            //used...?
            s.subtractFuel(0, distance);
            assertTrue(s.getFuelLevel() == 
                    (s.getFuelCapacity() - s.calcFuelForTravel(distance)));
            assertFalse(s.getFuelLevel() < 0);
            s.fillFuel();
            assertTrue(s.getFuelCapacity() == s.getFuelLevel());
        }
    }


    /**
     * Deals random damage to each of the ships, and ensures that the proper 
     * values are being set.
     */
    @Test
    public void testDamage() {
            Random roll = new Random();
            int damage = roll.nextInt();
        for( Ship s: ships) {
            s.subtractDamage(damage);
            assertTrue(s.getHealth() == (s.getMaxHealth() - damage));
        }
    }
    
    /**
     * Deals 5 damage to each ship, then heals each ship.
     */
    public void testDamageAndHeal() {
            int damage = 5;
        for( Ship s: ships) {
            s.subtractDamage(damage);
            assertTrue(s.getHealth() == (s.getMaxHealth() - damage));
            s.restoreHealth();
            assertTrue(s.getHealth() == s.getMaxHealth());
        }
    }
    
    }