/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author AfiqAzaibi
 */
public class Ship {
    private int fuel = 10000;
    
    public Ship(){
        Inventory shipInventory = new Inventory();
    }
    
    /**
     * @author Hutchinson
     * substractFuel() will decrement the fuel levels based on how far you've moved
     * @param int start_pos, end_pos
     * @return new fuel level
     */
    public int subtractFuel() { //needs params
        fuel -= 50; //will be changed to reflect  the actual position of the character
        return fuel;
        // Needs getters for the position
    }
    
    
    
    
    
}
