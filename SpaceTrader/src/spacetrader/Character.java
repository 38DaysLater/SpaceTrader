package spacetrader;

import java.io.Serializable;

/**
 * This class represents a Character. A character instance has a name, fight
 * level, pilot level, trade level, and engineer level. A character also an
 * inventory, health, currentPlanet, and currentSolarySystem.
 *
 * @author lawrence, Olivia, and Afiq
 */
public class Character implements Serializable {

    //CHECKSTYLE: OFF

    private static final long serialVersionUID = 1;
    private String name;
    private int pilot, fight, trade, engineer;
    private Inventory inventory;
    private int health = 10; //arbitrary starting health

    private Ship ship = null;
    private Planet currentPlanet = null;
    private SolarSystem currentSolarSystem = null;
    private SolarSystem previousSolarSystem = null;
    private static final int STARTING_MONEY = 3000;
    private static final int AFIQ_STARTING_MONEY = 9000;
    private static final int AFIQ_STARTING_STAT = 10;
    private static final int SSARRAY_SIZE = 3;
        //CHECKSTYLE:ON

    /**
     * This is the constructor. It establishes the character special characters
     * will have this available
     *
     * @param n name
     * @param p pilot level
     * @param f flight level
     * @param t trade level
     * @param e engineer level
     */
        //Checkstyle ignored: Params should be final
    //Checkstyle ignored: Magic numbers
    public Character(String n, int p, int f, int t, int e) {
        if (n.equals("Afiq")) {
            name = n;
            pilot = AFIQ_STARTING_STAT;
            fight = AFIQ_STARTING_STAT;
            trade = AFIQ_STARTING_STAT;
            engineer = AFIQ_STARTING_STAT;
            ship = new Ship();
            inventory = new Inventory();
            inventory.addToBalance(AFIQ_STARTING_MONEY);
            inventory.setCapacity(0);
        } else {
            name = n;
            pilot = p;
            fight = f;
            trade = t;
            engineer = e;
            ship = new Ship();
            inventory = new Inventory();
            inventory.addToBalance(STARTING_MONEY);
            inventory.setCapacity(0);
        }
    }

    /**
     * Constructor without variables.
     */
    public Character() {
    }

    //Checkstyle ignored: method needs to be abstract, final, or empty
    @Override
    public String toString() {
        return ("Name = " + name + '\n' + "Pilot = " + pilot + '\n'
                + "Fight = " + fight + '\n' + "Trade = " + trade + '\n'
                + "Engineer = " + engineer);
    }

    /**
     * Sets the character's name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets pilot score.
     *
     * @param num
     */
    public void setPilot(int num) {
        pilot += num;
    }

    /**
     * Sets fight score.
     *
     * @param num
     */
    public void setFight(int num) {
        fight += num;
    }

    /**
     * Sets trade score.
     *
     * @param num
     */
    public void setTrade(int num) {
        trade += num;
    }

    /**
     * Sets engineer score.
     *
     * @param num
     */
    public void setEngineer(int num) {
        engineer += num;
    }

    /**
     * Sets health.
     *
     * @param num
     */
    public void setHealth(int num) {
        health += num;
    }

    /**
     * Sets character's ship.
     *
     * @param ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Gets the character's name.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    ;
/**
 * Gets the character's pilot score.
 * @return pilot
 */
	public int getPilot() {
        return pilot;
    }

    /**
     * Gets the character's fight score.
     *
     * @return fight
     */
    public int getFight() {
        return fight;
    }

    /**
     * Gets the character's trade score.
     *
     * @return trade
     */
    public int getTrade() {
        return trade;
    }

    /**
     * Gets the character's engineer score.
     *
     * @return engineer
     */
    public int getEngineer() {
        return engineer;
    }

    /**
     * Gets the character's health.
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the player's inventory.
     *
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Checks the distance between the player's current location and the
     * destination planet.
     *
     * @param p
     * @return d
     */
    public int checkDistance(Planet p) {
        int distance = 0;
        int[] oldLoc = currentPlanet.getLocation();
        int[] newLoc = p.getLocation();
        distance = Math.abs(oldLoc[0] - newLoc[0]) + Math.abs(oldLoc[1]
                - newLoc[1]);
        double d = Math.sqrt(distance);
        return (int) d;
    }

    /**
     * Checks the distance between the player's current location and the
     * destination solar system.
     *
     * @param s
     * @return d
     */
    public int checkDistance(SolarSystem s) {
        if (previousSolarSystem == null) {
            return 0;
        }

        int distance = 0;
        int[] oldLoc = previousSolarSystem.getLocation();
        int[] newLoc = s.getLocation();
        distance = Math.abs(oldLoc[0] - newLoc[0]) + Math.abs(oldLoc[1]
                - newLoc[1]);
        double d = Math.sqrt(distance);
        return (int) (d * 10);
    }

    /**
     * Sets the current planet.
     *
     * @param p
     */
    public void setCurrentPlanet(Planet p) {
        previousSolarSystem = currentSolarSystem;
        currentPlanet = p;
    }

    /**
     * Gets the player's ship.
     *
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Gets the current planet. The 0th index is a planet. The first index is an
     * int array of the (x,y) coordinates of the planet.
     *
     * @return array length 2.
     */
    public Object[] getCurrentPlanet() {
        Object[] returnArray = new Object[2];
        returnArray[0] = currentPlanet;
        returnArray[1] = currentPlanet.getLocation();
        return returnArray;
    }

    /**
     * Sets the current solar system.
     *
     * @param ss
     */
    public void setCurrentSolarSystem(SolarSystem ss) {
        previousSolarSystem = currentSolarSystem;
        currentSolarSystem = ss;
    }

    /**
     * Gets the current solar system.
     *
     * @return SSArray
     */
    public Object[] getCurrentSolarSystem() {
        Object[] ssArray = new Object[SSARRAY_SIZE];
        ssArray[0] = currentSolarSystem;
        ssArray[1] = currentSolarSystem.getLocation();
        ssArray[2] = currentSolarSystem.getPlanets();
        return ssArray;
    }

    /**
     * Updates the current ship.
     *
     * @param ship
     */
    public void updateShip(Ship ship) {
        this.ship = ship;
        this.getInventory().setCapacity(ship.getCapacity());
    }
}
