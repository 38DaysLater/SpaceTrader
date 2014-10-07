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
    private int fuel = 500;
    
    public Ship(){
        
    }
    /**
     * @author Hutchinson
     * substractFuel() will decrement the fuel levels based on how far you've moved and your pilot score
     * @param int fuel, int distance, Character player
     * @return new fuel level
     */
    public int subtractFuel(int f, int distance) {
        //int val = 0;
        
        Character player = Singleton.getCharacter(); //Grabs current character
        double pn = player.getPilot() / 3; //Stores the player's pilot score / 3        
        fuel -= distance/pn;  //Decrements the fuel based on distance and pilot score
        return fuel;
    }
    
    //rusn a check to see if player can travel a distance.
    public String checkSufficientFuel(int distance){
        
        if(calcFuelForTravel(distance) > fuel) {
            return "Insufficient fuel";
        }
        return null;
    }
    
    //checks to see how much fuel will use. 
    //private since only use in class
    private int calcFuelForTravel(int dist){
        Character player = Singleton.getCharacter(); //Grabs current character
        double pn = player.getPilot() / 3; //Stores the player's pilot score / 3
        double returnValue = dist/pn;
        return (int)returnValue;
    }
    
    /**
     * @author Hutchinson
     * getFuelLevel() gets the current level of fuel
     * @param none
     * @return current fuel level
     */
    public int getFuelLevel() {
        return fuel;
    }
}
