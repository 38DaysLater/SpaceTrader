package spacetrader;

/**
 *
 * @author AfiqAzaibi
 * This class is where ships go to planet if it's available. Only available
 * tech levels. 
 */

//refuel ship
//trade ship
//Repair ship
//upgrade ship
public class ShipYard {
    int fuelPrice;
    
    //has price for fuel
    public ShipYard(int fuelPrice){
        this.fuelPrice = fuelPrice;
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
    
    
    
    
    
}
