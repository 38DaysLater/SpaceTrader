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
    
    //this increaes the ship's health to max when applicable. 
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
    
    
    //buys and replaces the current ship that you have. 
    //checks to make sure that you have enough money and skill
    //and not too much cargo. 
    
    //SELL CURRENT SHIP FOR WHAT IT'S WORTH
    public String buyShip(Ship ship) {
        Character myChar = Singleton.getCharacter();
        Inventory playerInventory = myChar.getInventory();
        int currentBalance = myChar.getInventory().getBalance();
        Ship charShip = myChar.getShip();
        int refundPrice = charShip.getPrice()/2;
        
        

        
        String currentShipName = charShip.getName();
        String currentShipType = currentShipName.substring(0);
        int lengthCurrent = currentShipName.length();        
        int currentShipLevel = Integer.parseInt(currentShipName.substring(lengthCurrent - 1));

        
        
        String upgradeShipName = ship.getName();
        int lengthNew = upgradeShipName.length();
        String upgradeShipType = upgradeShipName.substring(0);
        int upgradeShipLevel = Integer.parseInt(upgradeShipName.substring(lengthNew - 1));
        
        System.out.println(currentShipLevel);
        System.out.println(upgradeShipLevel);
        
        
        
        
        if (ship.getPrice() > currentBalance + refundPrice) {
            return "You don't have enough money for this pimpin ride";
        } else if (ship.getMLP() > myChar.getPilot()) {
            return "You don't have the skill to pilot this pimpin ride";
        } else if (playerInventory.totalItemCount() > ship.getCapacity()){
            return "You have too much cargo on your current ship to get this "
                    + "new one. Sell your merchandise or pick a ship that can "
                    + "accomodate your cargo";
        } /*else if (currentShipLevel <= upgradeShipLevel - 1) {
            return "Your current ship level is " + currentShipLevel + ". "
                    + "You must upgrade to level " + (currentShipLevel + 1) + 
                    " before upgrading to level " + upgradeShipLevel;
        } */
        

       //gives refund on current ship and deducts old one. 
       playerInventory.addToBalance(refundPrice);
       myChar.updateShip(ship);
       playerInventory.subtractFromBalance(ship.getPrice());
       
       return null;
    }
    /*public int getFuelPrice() {
        return fuelPrice;
    }*/
    public int getRepairCost() {
        return repairCost;
    }
    
    
}
