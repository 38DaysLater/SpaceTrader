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
    private int fuel = 50000000;
    
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
        double pn = player.getPilot() / 10; //Stores the player's pilot score
        
        /*int fn = player.getFight();
        int tn = player.getTrade();
        int en = player.getEngineer();
        int hn = player.getHealth();
        String nn = player.getName();
        nn = nn.toLowerCase();
        int[] array = new int[nn.length()];
        for (int i = 0; i <= nn.length(); i++) {
            array[i] = nn.charAt(i) - 48;
            val += array[i];
        }*/
        
      //  fuel -= distance/pn;  //Decrements the fuel based on distance and pilot score
        fuel -= 200;
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
        //double pn = player.getPilot() / 10; //Stores the player's pilot score
        //double returnValue = dist/pn;
       // return (int)returnValue;
        return player.getShip().getFuelLevel();
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
