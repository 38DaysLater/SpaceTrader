/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

public class Character {

	//for constructor
	private String name;
	private int pilot, fight, trade, engineer; 
        private Inventory inventory;
	private int health = 10; //arbitrary starting health

	public Character(String n, int p, int f, int t, int e) {
		name = n;
		pilot = p;
		fight = f;
		trade = t; 
		engineer = e;
                Ship ship = new Ship();
                inventory = new Inventory();
                inventory.setBalance(5000);
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
        
        //make ship class. That has cargo hold.  That's where the inventory of the player goes
        //Every ship has an inventory, and every characater has a ship
        //when buying and selling, the character class needs methods to edit the inventory of the ship
        
        

}
