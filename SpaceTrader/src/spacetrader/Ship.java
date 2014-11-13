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
        if (player.getPilot() > 5) {
            //Stores the player's pilot score / 3
            double pn = player.getPilot() / 3;
            //Decrements the fuel based on distance and pilot score
            fuel -= distance/pn;

        } else {
            fuel -= distance;
        }

        return fuel;
    }

    //rusn a check to see if player can travel a distance.
    public String checkSufficientFuel(int distance){
        String returnString = null;
        if (calcFuelForTravel(distance) > fuel) {
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
     * Gets fuel level.
     * @return fuel
     */
    public int getFuelLevel() {
        return fuel;
    }
/**
 * Gets fuel capacity.
 * @return FUEL_CAPACITY.
 */
    public int getFuelCapacity(){
        return FUEL_CAPACITY;
    }
/**
 * Fills fuel.
 */
    public void fillFuel() {
        fuel = FUEL_CAPACITY;
    }
/**
 * Adds x to current fuel.
 * @param x
 */
    public void addToFuel(int x){
        fuel += x;
    }
/**
 * Increases current health by x
 * @param x
 */
    public void increaseHealth(int x){
        health += x;
    }
/**
 * Gets the ship's attack score.
 * @return
 */
    public int getAttack() {
        return atk;
    }
/**
 * Gets the ship's speed score.
 * @return
 */
    public int getSpeed() {
        return speed;
    }
/**
 * Gets the ship's health.
 * @return
 */
    public int getHealth() {
        return health;
    }
/**
 * Subtracts health from the ship when it takes damage.
 * @param damage
 */
    public void subtractDamage(int damage) {
        this.health = health - damage;
    }
/**
 * Gets the max health of the ship.
 * @return maxHealth
 */
    public int getMaxHealth() {
        return maxHealth;
    }
/**
 * Gets the damage done to the ship.
 * @return maxHealth - health
 */
    public int getDamage() {
        return maxHealth - health;
    }
/**
 * Restores the health of the ship.
 */
    public void restoreHealth() {
        health = maxHealth;
    }
/**
 * Gets the price of ship.
 * @return price
 */
    public int getPrice() {
        return price;
    }
/**
 * Gets the name of ship.
 * @return name
 */
    public String getName() {
        return name;
    }
/**
 * Gets MLP of the ship.
 * @return MLP
 */
    public int getMLP(){
        return MLP;
    }
/**
 * Gets capacity.
 * @return capacity
 */
    public int getCapacity(){
        return capacity;
    }
/**
 * Gets the remaining upgrades a ship can have.
 * @return upgradesLeft
 */
    public int getUpgradesLeft() {
        return upgradesLeft;
    }
/**
 * Upgrades the health of the ship.
 * @param increase amount to increase ship health
 */
    public void upgradeHealth(int increase) {
        maxHealth += increase;
        upgradesLeft--;
        healthUpgrade++;
    }
/**
 * Upgrades the attack of the ship.
 * @param increase amount to increase ship attack
 */
    public void upgradeAttack(int increase) {
        atk += increase;
        upgradesLeft--;
        atkUpgrade++;
    }
/**
 * Upgrades the capacity of the ship.
 * @param increase amount to increase ship capacity
 */
    public void upgradeCapacity(int increase) {
        capacity += increase;
        Singleton.getCharacter().getInventory().setCapacity(capacity);
        upgradesLeft--;
        capacityUpgrade++;
    }
/**
 * Upgrades the speed of the ship.
 * @param increase amount to increase ship capacity
 */
    public void upgradeSpeed(int increase) {
        speed += increase;
        upgradesLeft--;
        speedUpgrade++;
    }
/**
 * Updates number of upgrades left.
 * @param upgradesLeft
 */
    public void updateUpgradesLeft(int upgradesLeft) {
        this.upgradesLeft = upgradesLeft;
    }
/**
 * Gets health upgrades.
 * @return healthUpgrade
 */
    public int getHealthUpgrades() {
        return healthUpgrade;
    }
/**
 * Gets attack upgrades.
 * @return atkUpgrade
 */
    public int getAttackUpgrades() {
        return atkUpgrade;
    }
/**
 * Gets speed upgrades.
 * @return speedUpgrade
 */
    public int getSpeedUpgrades() {
        return speedUpgrade;
    }
/**
 * Gets capacity upgrades.
 * @return capacityUpgrade
 */
    public int getCapacityUpgrades() {
        return capacityUpgrade;
    }

    @Override
    public String toString(){
        String tab = "\t\t";
        String tabTab = "\t\t\t";
        String returnString = price + tab + MLP + tab + weight + tabTab
                + atk + tab + speed + tab + FUEL_CAPACITY + tab
                + capacity + tab + health;
        return returnString;
    }


}
