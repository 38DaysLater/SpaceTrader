/*
 *This class acts as the vessel that the character has. The player
 *will have a ship at all times but this can be interchanged
 *Ship keeps track of fuel and its specs will be taken into account
 *for mini games.
 */

package spacetrader;
import java.io.Serializable;
/**
 *
 * @author AfiqAzaibi, Lawrence Moore
 */

public class Ship implements Serializable{
    private static final long serialVersionUID = 1;
    private int fuel = 500;
    private final int FUEL_CAPACITY;
    private String name;
    private int price;
    private int MLP;
    private int weight;
    private int atk;
    private int speed;
    private int maxHealth;
    private int health;   
    private int capacity;
    private int upgradesLeft = 10;
    private int healthUpgrade;
    private int atkUpgrade;
    private int speedUpgrade;
    private int capacityUpgrade;

    //this is your starting ship. 
    public Ship() {
        name = "Rusty 0";
        price = 0;
        FUEL_CAPACITY = 0;
        maxHealth = 0;
        MLP = 0;
        weight = 0;
        atk = 0;
        health = 0;
        fuel = 0;
        capacity = 0;
    }
 
    //constructor for real ship. This is one that you purchase. 
    public Ship(String name, int price, int MLP, int weight, int attack, int speed, int fuelCapacity, int health, int cargo){
        this.name = name;
        this.price = price;
        this.MLP = MLP;
        this.weight = weight;
        atk = attack;
        this.speed = speed;
        FUEL_CAPACITY = fuelCapacity;
        fuel = FUEL_CAPACITY;
        this.health = health;
        maxHealth = health;
        capacity = cargo;
    }

    /**
     * will decrement the fuel levels based on how far you've moved
     * and your pilot score.
     * @param int fuel, int distance, Character player
     * @return new fuel level
     */
    public int subtractFuel(int f, int distance) {
        //int val = 0;
        
        Character player = Singleton.getCharacter(); //Grabs current character
        if(player.getPilot() > 5) {
            double pn = player.getPilot() / 3; //Stores the player's pilot score / 3        
            fuel -= distance/pn;  //Decrements the fuel based on distance and pilot score

        } else {
            fuel -= distance;
        }
        
        return fuel;
    }
    
    //rusn a check to see if player can travel a distance.
    public String checkSufficientFuel(int distance){
        String returnString = null;
        if(calcFuelForTravel(distance) > fuel) {
            returnString = "Insufficient fuel";
        }
        return returnString;
    }
    
    //checks to see how much fuel will use.
    public int calcFuelForTravel(int dist){

        double pn = 3; //Stores the player's pilot score / 3
        double returnValue = dist/pn;
        return (int)returnValue*2;
    }
    
    /**
     * gets the current level of fuel.
     * @return int current fuel level
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
    
    public void increaseHealth(int x){
        health += x;
    }
    
    public int getAttack() {
        return atk;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void subtractDamage(int damage) {
        this.health = health - damage;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public int getDamage() {
        return maxHealth - health;
    }
    
    public void restoreHealth() {
        health = maxHealth;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMLP(){
        return MLP;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public int getUpgradesLeft() {
        return upgradesLeft;
    }
    
    public void upgradeHealth(int increase) {
        maxHealth += increase;
        upgradesLeft--;
        healthUpgrade++;
    }
    
    public void upgradeAttack(int increase) {
        atk += increase;
        upgradesLeft--;
        atkUpgrade++;
    }
    
    public void upgradeCapacity(int increase) {
        capacity += increase;
        Singleton.getCharacter().getInventory().setCapacity(capacity);
        upgradesLeft--;
        capacityUpgrade++;
    }

    public void upgradeSpeed(int increase) {
        speed += increase;
        upgradesLeft--;
        speedUpgrade++;
    }
    
    public void updateUpgradesLeft(int upgradesLeft) {
        this.upgradesLeft = upgradesLeft;
    }
    
    public int getHealthUpgrades() {
        return healthUpgrade;
    }
    
    public int getAttackUpgrades() {
        return atkUpgrade;
    }
    
    public int getSpeedUpgrades() {
        return speedUpgrade;
    }
    
    public int getCapacityUpgrades() {
        return capacityUpgrade;
    }
    
    public String toString(){
        String tab = "\t\t";
        String tabTab = "\t\t\t";
        String returnString = price + tab + MLP + tab + weight + tabTab
                + atk + tab + speed + tab + FUEL_CAPACITY + tab
                + capacity + tab + health;
        return returnString;
    }
    
    
}
