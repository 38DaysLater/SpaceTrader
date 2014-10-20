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
    private final int FUEL_CAPACITY;
    private String name;
    private int price;
    private int MLP;
    private int weight;
    private int atk;
    private int speed;
        
    //this is your starting ship. 
    public Ship(){
        FUEL_CAPACITY = fuel;
    }
    
    //constructor for real ship. This is one that you purchase. 
    public Ship(String name, int price, int MLP, int weight, int attack, int speed, int fuelCapacity){
        this.name = name;
        this.price = price;
        this.MLP = MLP;
        this.weight = weight;
        atk = attack;
        this.speed = speed;
        FUEL_CAPACITY = fuelCapacity;
        fuel = FUEL_CAPACITY;
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
        if(player.getPilot() > 5) {
            double pn = player.getPilot() / 3; //Stores the player's pilot score / 3        
            fuel -= distance/pn;  //Decrements the fuel based on distance and pilot score
            return fuel;
        }
        
        return fuel -= distance;
    }
    
    //rusn a check to see if player can travel a distance.
    public String checkSufficientFuel(int distance){
        
        if(calcFuelForTravel(distance) > fuel) {
            return "Insufficient fuel";
        }
        return null;
    }
    
    //checks to see how much fuel will use.
    public int calcFuelForTravel(int dist){
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
    
    public int getFuelCapacity(){
        return FUEL_CAPACITY;
    }
    
    public void fillFuel() {
        fuel = FUEL_CAPACITY;
    }
    
    
    public void addToFuel(int x){
        fuel += x;
    }
    
    
}
