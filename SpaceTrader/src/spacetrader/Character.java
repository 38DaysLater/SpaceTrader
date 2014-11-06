/**
 * This class represents a Character. 
 * A character instance has a name, fight level, pilot level, trade level, and 
 * engineer level.  A character also an inventory, health, currentPlanet, and 
 * currentSolarySystem.  
 * @author lawrence, Olivia, and Afiq
 */

package spacetrader;
import java.io.Serializable;

public class Character implements Serializable{

	//for constructor
	private String name;
	private int pilot, fight, trade, engineer; 
        private Inventory inventory;
	private int health = 10; //arbitrary starting health

        private Ship ship = null;
        private Planet currentPlanet = null;
        private SolarSystem currentSolarSystem = null;
        private SolarSystem previousSolarSystem = null;


        /**
         * This is the constructor. It establishes the character
         * special characters will have this available
         * @param n name
         * @param p pilot level
         * @param f flight level
         * @param t trade level
         * @param e engineer level
         */
	public Character(String n, int p, int f, int t, int e) {
            if(n.equals("Afiq")) {
                name = n; 
                pilot = 10;
                fight = 10;
                trade = 10;
                engineer = 10;
                ship = new Ship();
                inventory = new Inventory();
                inventory.addToBalance(9000);
                inventory.setCapacity(0);
            } else {
                    name = n;
                    pilot = p;
                    fight = f;
                    trade = t;
                    engineer = e;
                    ship = new Ship();
                    inventory = new Inventory();
                    inventory.addToBalance(3000);
                    inventory.setCapacity(0);
            }
        }

        public Character() {
		
	}
        public String toString() {
            return ("Name = " + name + '\n' + "Pilot = " + pilot + '\n' + "Fight = " + fight + '\n' + "Trade = " + trade + '\n' + "Engineer = " + engineer);
        }
	//setters
        public void setName(String name) {this.name = name;}

	public void setPilot(int num) {pilot += num;}

	public void setFight(int num) {fight += num;}

	public void setTrade(int num) {trade += num;}

	public void setEngineer(int num) {engineer += num;}

	public void setHealth(int num) {health += num;}

        public void setShip(Ship ship) {
            this.ship = ship;
        }

	//getters
        public String getName() {return name;};

	public int getPilot(){return pilot;}

	public int getFight(){return fight;}

	public int getTrade(){return trade;}

	public int getEngineer(){return engineer;}

	public int getHealth(){return health;}

        public Inventory getInventory(){
            return inventory;
        }

        public int checkDistance(Planet p){
            int distance = 0;
            int[] oldLoc = currentPlanet.getLocation();
            int[] newLoc = p.getLocation();
            distance = Math.abs(oldLoc[0] - newLoc[0])
                    + Math.abs(oldLoc[1] - newLoc[1]);
            double d = Math.sqrt(distance);
            return (int) d;
        }

        public int checkDistance(SolarSystem s) {
            if (previousSolarSystem == null) {
                return 0;
            }

            int distance = 0;
            int[] oldLoc = previousSolarSystem.getLocation();
            int[] newLoc = s.getLocation();
            distance = Math.abs(oldLoc[0] - newLoc[0])
                    + Math.abs(oldLoc[1] - newLoc[1]);
            double d = Math.sqrt(distance);
            return (int) (d * 10);
        }

        public void setCurrentPlanet(Planet p){
            previousSolarSystem = currentSolarSystem;
            currentPlanet = p;
        }

        public Ship getShip(){
            return ship;
        }

        /**
         * Returns the current planet.
         * @return array length 2. 
         * 0th index is planet. 
         * 1st index is int array of xy coordinate of planet
         **/
        public Object[] getCurrentPlanet(){
            Object[] returnArray = new Object[2];
            returnArray[0] = currentPlanet;
            returnArray[1] = currentPlanet.getLocation();
            return returnArray;
        }
        
        
        /*
        sets the solar system by just passing one in. 
        */
        public void setCurrentSolarSystem(SolarSystem ss) {
            previousSolarSystem = currentSolarSystem;
            currentSolarSystem = ss;
        }
        
        public Object[] getCurrentSolarSystem(){
            Object[] SSArray = new Object[3];
            SSArray[0] = currentSolarSystem;
            SSArray[1] = currentSolarSystem.getLocation();
            SSArray[2] = currentSolarSystem.getPlanets();
            return SSArray;
        }
        
        public void updateShip(Ship ship) {
            this.ship = ship;
            this.getInventory().setCapacity(ship.getCapacity());
        }

}
