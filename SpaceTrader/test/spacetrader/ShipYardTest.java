/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shiro_000 (Olivia LaHay)
 */
public class ShipYardTest {
    
    int techLevel;
    Inventory inventory;
    Ship ship;
    ShipYard shipYard;
    Character cha;
    Ship titan1, titan2, titan3, serenity1, serenity2, banshee1, banshee2;
    
    @Before
    public void setUp() {
        Singleton.setCharacter(new Character("name", 0, 0, 0, 0));
        cha = Singleton.getCharacter();
        inventory = cha.getInventory();
        techLevel = new Random().nextInt(8);
        ship = new Ship();
        cha.setShip(ship);
        shipYard = new ShipYard(0, techLevel);
        //Ship(name, price, MLP, weight, attack, speed, fuelCap, health, cargo)
        titan1 = new Ship("Titan 1", 1000, 2, 70, 5, 4, 600, 100, 10);
        titan2 = new Ship("Titan 2", 2000, 5, 85, 7, 4, 750, 125, 20);
        titan3 = new Ship("Titan 3", 5000, 100, 10, 10, 5, 999, 150, 50);
        banshee1 = new Ship("Banshee 1", 1000, 2, 25, 5, 5, 600, 40, 10);
        serenity1 = new Ship("Serenity 1", 1000, 0, 70, 5, 3, 600, 60, 100);
        serenity2 = new Ship("Serenity 2", 2000, 0, 75, 5, 2, 700, 70, 200);
        banshee2 = new Ship("Banshee 2", 2000, 5, 15, 5, 7, 750, 50, 20);
    }

    /**
     * Test of buyShip method, of class ShipYard.
     * checks if a ship can be bought with insufficient money.
     */
    @Test
    public void testBuyShipNotEnoughMoney() {
        inventory.setBalance(0); //makes sure there is not enough money
        String expResult = "You don't have enough money for this pimpin ride";
        String result = shipYard.buyShip(serenity1);
        assertEquals(expResult, result);
    }
    /**
     * Test of buyShip method, of class ShipYard.
     * checks if a ship can be bought with insufficient pilot level.
     */
    @Test
    public void testBuyShipPilotSkill() {
        inventory.setBalance(10000); //to make sure there is enough money to buy
        cha.setPilot(1); //to make sure the pilot level is insufficient
        String expResult = "You don't have the skill to pilot this pimpin ride";
        String result = shipYard.buyShip(titan1);
        assertEquals(expResult, result);
    }
    /**
     * Test of buyShip method, of class ShipYard.
     * checks if a ship can be bought too much cargo in inventory
     * for cargo cap of ship.
     */
    @Test
    public void testBuyShipTooMuchCargo() {
        inventory.setBalance(10000); //to make sure there is enough money to buy
        cha.setPilot(7); //to make sure the pilot level is sufficient
        inventory.setCapacity(15); //sets higher cap than ship buying
        int cap = inventory.getCapacity();
        for (int i = 0; i < cap; i++) {
            inventory.add("Water");
        }
        String expResult = "You have too much cargo on your current "
                    + "ship to get this new one. Sell your merchandise or "
                    + "pick a ship that can accomodate your cargo";
        String result = shipYard.buyShip(titan1);
        assertEquals(expResult, result);
    }

    /**
     * Test of buyShip method, of class ShipYard.
     * checks if a ship can be bought with insufficient ship level.
     */
    @Test
    public void testBuyShipInsufficientLevel() {
        inventory.setBalance(10000); //to make sure there is enough money to buy
        cha.setPilot(500); //to make sure the pilot level is sufficient
        shipYard.buyShip(titan1);
        String expResult = "Your current ship level is " + 1 + ". "
                    + "You must upgrade to level " + (2)
                    + " before upgrading to level " + 3;
        String result = shipYard.buyShip(titan3);
        assertEquals(expResult, result);
    }
    /**
     * Test of buyShip method, of class ShipYard.
     * checks if ship can be upgraded to different type.
     */
    @Test
    public void testBuyShipDifferentType() {
        inventory.setBalance(10000); //to make sure there is enough money to buy
        cha.setPilot(500); //to make sure the pilot level is sufficient
        shipYard.buyShip(titan1);
        String expResult = "You cannot upgrade to another type of ship";
        String result = shipYard.buyShip(serenity2);
        assertEquals(expResult, result);
    }
    /**
     * Test of buyShip method, of class ShipYard.
     * checks if same ship can be bought.
     */
    @Test
    public void testBuyShipSameShip() {
        inventory.setBalance(10000); //to make sure there is enough money to buy
        cha.setPilot(500); //to make sure the pilot level is sufficient
        shipYard.buyShip(titan1);
        String expResult = "You already own that ship.";
        String result = shipYard.buyShip(titan1);
        assertEquals(expResult, result);
    }
}
