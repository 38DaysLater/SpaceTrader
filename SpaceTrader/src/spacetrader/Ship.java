/*
 *This class acts as the vessel that the character has. The player
 *will have a ship at all times but this can be interchanged
 *Ship keeps track of fuel and its specs will be taken into account
 *for mini games.
 */

package spacetrader;

/**
 *
 * @author AfiqAzaibi
 */
public class Ship {
    private int fuel = 10000;
    
    public Ship(){
        
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
