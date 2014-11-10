/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shiro_000
 */
public class ShipYardTest {
    
    public ShipYardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of costOfRefuelCompletely method, of class ShipYard.
     */
    @Test
    public void testCostOfRefuelCompletely() {
        System.out.println("costOfRefuelCompletely");
        Ship s = null;
        ShipYard instance = null;
        int expResult = 0;
        int result = instance.costOfRefuelCompletely(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refuelCompletely method, of class ShipYard.
     */
    @Test
    public void testRefuelCompletely() {
        System.out.println("refuelCompletely");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.refuelCompletely();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refuelPartially method, of class ShipYard.
     */
    @Test
    public void testRefuelPartially() {
        System.out.println("refuelPartially");
        int fuelMoney = 0;
        ShipYard instance = null;
        String expResult = "";
        String result = instance.refuelPartially(fuelMoney);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repairShip method, of class ShipYard.
     */
    @Test
    public void testRepairShip() {
        System.out.println("repairShip");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.repairShip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyShip method, of class ShipYard.
     */
    @Test
    public void testBuyShip() {
        System.out.println("buyShip");
        Ship ship = null;
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyShip(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRepairCost method, of class ShipYard.
     */
    @Test
    public void testGetRepairCost() {
        System.out.println("getRepairCost");
        ShipYard instance = null;
        int expResult = 0;
        int result = instance.getRepairCost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyNeuronTorpedos method, of class ShipYard.
     */
    @Test
    public void testBuyNeuronTorpedos() {
        System.out.println("buyNeuronTorpedos");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyNeuronTorpedos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyPlasmaBlasters method, of class ShipYard.
     */
    @Test
    public void testBuyPlasmaBlasters() {
        System.out.println("buyPlasmaBlasters");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyPlasmaBlasters();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyDeathStarLaser method, of class ShipYard.
     */
    @Test
    public void testBuyDeathStarLaser() {
        System.out.println("buyDeathStarLaser");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyDeathStarLaser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyNitrogenBooster method, of class ShipYard.
     */
    @Test
    public void testBuyNitrogenBooster() {
        System.out.println("buyNitrogenBooster");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyNitrogenBooster();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyFluxCapacitor method, of class ShipYard.
     */
    @Test
    public void testBuyFluxCapacitor() {
        System.out.println("buyFluxCapacitor");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyFluxCapacitor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyWarpGenerator method, of class ShipYard.
     */
    @Test
    public void testBuyWarpGenerator() {
        System.out.println("buyWarpGenerator");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyWarpGenerator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyGravityShield method, of class ShipYard.
     */
    @Test
    public void testBuyGravityShield() {
        System.out.println("buyGravityShield");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyGravityShield();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyNeuronField method, of class ShipYard.
     */
    @Test
    public void testBuyNeuronField() {
        System.out.println("buyNeuronField");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyNeuronField();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyUnobtanium method, of class ShipYard.
     */
    @Test
    public void testBuyUnobtanium() {
        System.out.println("buyUnobtanium");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyUnobtanium();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyIncreasedCargo method, of class ShipYard.
     */
    @Test
    public void testBuyIncreasedCargo() {
        System.out.println("buyIncreasedCargo");
        ShipYard instance = null;
        String expResult = "";
        String result = instance.buyIncreasedCargo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upgrade method, of class ShipYard.
     */
    @Test
    public void testUpgrade() {
        System.out.println("upgrade");
        int mTLP = 0;
        int cost = 0;
        int increase = 0;
        UpgradeType up = null;
        ShipYard instance = null;
        String expResult = "";
        String result = instance.upgrade(mTLP, cost, increase, up);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
