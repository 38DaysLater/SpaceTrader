/**
 * This class represents a Character. 
 * A character instance has a name, fight level, pilot level, trade level, and 
 * engineer level.  A character also an inventory, health, currentPlanet, and 
 * currentSolarySystem.  
 * @author lawrence, Olivia, and Afiq
 */

package spacetrader;

public class Character {

	//for constructor
	private String name;
	private int pilot, fight, trade, engineer; 
        private Inventory inventory;
	private int health = 10; //arbitrary starting health

        private Planet currentPlanet = null;
        private SolarSystem currentSolarSystem = null;
        
        /**
        * This is the constructor. It establishes the character
        * @param name, pilot level, flight level, fight level, trade level, engineer level
        */
        
	public Character(String n, int p, int f, int t, int e) {
		name = n;
		pilot = p;
		fight = f;
		trade = t; 
		engineer = e;
                Ship ship = new Ship();
                inventory = new Inventory();
                inventory.addToBalance(10000);
                inventory.setCapacity(6);
	}
        
        public Character() {
		
	}
        public String toString() {
            return ("Name = " + name + '\n' + "Pilot = " + pilot + '\n' + "Fight = " + fight + '\n' + "Trade = " + trade + '\n' + "Engineer = " + engineer);
        }
	//setters
        public void setName(String name) {this.name = name;}
        
	public void setPilot(int num){pilot += num;}

	public void setFight(int num){fight += num;}

	public void setTrade(int num){trade += num;}

	public void setEngineer(int num){engineer += num;}

	public void setHealth(int num){health += num;}

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
        
        public void setCurrentPlanet(Planet p){
            currentPlanet = p;
        }
        
        public Object[] getCurrentPlanet(){
            Object[] returnArray = new Object[2];
            returnArray[0] = currentPlanet;
            returnArray[1] = currentPlanet.getLocation();
            return returnArray;
        }
        
        public void setCurrentSolarSystem(SolarSystem ss) {
            currentSolarSystem = ss;
        }
        
        public SolarSystem getCurrentSolarSystem(){
            return currentSolarSystem;
        }
        

}
