package spacetrader;
import java.io.Serializable;

/**
 *
 * @author Afiq Azaibi, Lawrence Moore
 * This class is where ships go to planet if it's available. Only available
 * tech levels. 
 */

//refuel ship
//trade ship
//Repair ship
//upgrade ship
public class ShipYard implements Serializable{
    //be sure to display these to the user so they can make an educated decision
    private int fuelPrice;
    private int techLevel;
    private int repairCost;
    
    
    //has price for fuel
    public ShipYard(int priceAdjustment, int techLevel){
        this.fuelPrice = 10 + priceAdjustment;
        this.techLevel = techLevel;
        repairCost = 15 - techLevel;
    }
    
    //simply calculates how much it costs to refuel
    //show this when player wants to buy stuff
    public int costOfRefuelCompletely(Ship s) {
        int fuelNeeded = s.getFuelCapacity() - s.getFuelLevel();
        return fuelNeeded * fuelPrice;
    }
    
    //subtracts balance from player and refuels the ship completely
    //returns error message or nothing depending if player has enough funds
    public String refuelCompletely() {
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance = Singleton.getCharacter().getInventory().getBalance();
        Ship charShip = Singleton.getCharacter().getShip();
        
        //check to see if enough fuel
        if(currentBalance < costOfRefuelCompletely(charShip)){
            return "You don't have enough to refuel all the way";
        }
        
        //subtracts price and fills fuel up
        else {          
            playerInventory.subtractFromBalance(costOfRefuelCompletely(charShip));
            charShip.fillFuel();
            return null;
        }
    
    }
    
    
    
    //fills based on how much player is willing to spend. 
    //if doesn't have enough then bad. 
    public String refuelPartially(int fuelMoney){
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance = Singleton.getCharacter().getInventory().getBalance();
        Ship charShip = Singleton.getCharacter().getShip();
                
        if(fuelMoney > currentBalance){
            return "You don't have enough moneys";
        } else if (fuelMoney < 0) {
            return "Please enter a positive integer";
        } else if(fuelMoney > costOfRefuelCompletely(charShip)){
            return "Your ship can't handle that much fuel";
        }
        
        //if pass all those tests then add fuel. 
        int fuelToAdd = fuelMoney/fuelPrice;
        playerInventory.subtractFromBalance(fuelMoney);
        charShip.addToFuel(fuelToAdd);
        return null;
    }
    
    
    public String repairShip() {
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance = Singleton.getCharacter().getInventory().getBalance();
        Ship charShip = Singleton.getCharacter().getShip();
        
        if(repairCost > currentBalance){
            return "You don't have enough moneys";
        } else if (repairCost < 0) {
            return "Please enter a positive integer";
        } 
      
        int damage = charShip.getDamage();
        if (damage * repairCost < currentBalance) {
            playerInventory.subtractFromBalance(damage * repairCost);
            charShip.restoreHealth();
        } else {
            int healthIncrease = currentBalance / repairCost;
            charShip.increaseHealth(healthIncrease);
            playerInventory.subtractFromBalance(currentBalance);
        }
        return null;
    }
    
    public String buyShip(Ship ship) {
        Character myChar = Singleton.getCharacter();
        Inventory playerInventory = myChar.getInventory();
        int currentBalance = myChar.getInventory().getBalance();
        Ship charShip = myChar.getShip();
        
        if (ship.getPrice() > currentBalance) {
            return "You don't have enough money for this pimpin ride";
        }
        
       myChar.updateShip(ship);
       playerInventory.subtractFromBalance(ship.getPrice());
       
        return null;
    }
    public int getFuelPrice() {
        return fuelPrice;
    }
    public int getRepairCost() {
        return repairCost;
    }
    
    
}
