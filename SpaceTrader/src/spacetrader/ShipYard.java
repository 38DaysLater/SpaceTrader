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
public class ShipYard implements Serializable {
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
        String currentShipType = currentShipName.substring(0, 1);
        int lengthCurrent = currentShipName.length();        
        int currentShipLevel = Integer.parseInt(currentShipName.substring(lengthCurrent - 1));

        
        
        String upgradeShipName = ship.getName();
        int lengthNew = upgradeShipName.length();
        String upgradeShipType = upgradeShipName.substring(0, 1);
        int upgradeShipLevel = Integer.parseInt(upgradeShipName.substring(lengthNew - 1));
        
        
        
        
        
        if (ship.getPrice() > currentBalance + refundPrice) {
            return "You don't have enough money for this pimpin ride";
        } else if (ship.getMLP() > myChar.getPilot()) {
            return "You don't have the skill to pilot this pimpin ride";
        } else if (playerInventory.totalItemCount() > ship.getCapacity()){
            return "You have too much cargo on your current ship to get this "
                    + "new one. Sell your merchandise or pick a ship that can "
                    + "accomodate your cargo";
        } else if (currentShipLevel < upgradeShipLevel - 1) {
            return "Your current ship level is " + currentShipLevel + ". "
                    + "You must upgrade to level " + (currentShipLevel + 1) + 
                    " before upgrading to level " + upgradeShipLevel;
        } else if (upgradeShipLevel > 1 && !upgradeShipType.equals(currentShipType)) {
            return "You cannot upgrade to another type of ship";
        } else if (upgradeShipName.equals(currentShipName)) {
            return "You already own that ship.";
        }
        

       //gives refund on current ship and deducts old one. 
       playerInventory.addToBalance(refundPrice);
       myChar.updateShip(ship);
       playerInventory.subtractFromBalance(ship.getPrice());
       
       //so if you uncomment this code and upgrade your ship from type 1 to 2 to 3,
       //the capacity gets really messed up. 
       /*
       
       //transfer upgrades
       ship.upgradeHealth(charShip.getHealthUpgrades());
       ship.upgradeAttack(charShip.getAttackUpgrades());
       ship.upgradeSpeed(charShip.getSpeedUpgrades());
       ship.upgradeCapacity(charShip.getCapacityUpgrades());
       //update capacity
       ship.decreaseCapacity(charShip.getHealthUpgrades() + charShip.getAttackUpgrades()
                                + charShip.getSpeedUpgrades());
       */
       return null;
    }
    /*public int getFuelPrice() {
        return fuelPrice;
    }*/
    public int getRepairCost() {
        return repairCost;
    }
    
    public String buyNeuronTorpedos() {
        // minimum tech level to produce: 5
        int MTLP = 5, torpedoCost = 300, torpedoAttackIncrease = 1;
        return upgrade(MTLP, torpedoCost, torpedoAttackIncrease, UpgradeType.attackUpgrade);
    }

    public String buyPlasmaBlasters() {
        // minimum tech level to produce: 6
        int MTLP = 6, plasmaCost = 500, plasmaAttackIncrease = 2;
        return upgrade(MTLP, plasmaCost, plasmaAttackIncrease, UpgradeType.attackUpgrade);
    }

    public String buyDeathStarLaser() {
        // minimum tech level to produce: 7
        int MTLP = 7, laserCost = 1500, laserAttackIncrease = 4;
        return upgrade(MTLP, laserCost, laserAttackIncrease, UpgradeType.attackUpgrade);
    }
    
    public String buyNitrogenBooster() {
        // minimum tech level to produce: 5
        int MTLP = 5, boostCost = 300, boosterSpeedIncrease = 1;
        return upgrade(MTLP, boostCost, boosterSpeedIncrease, UpgradeType.speedUpgrade);
    }
    
    public String buyFluxCapacitor() {
        int MTLP = 6, fluxCost = 500, fluxSpeedIncrease = 2;
        return upgrade(MTLP, fluxCost, fluxSpeedIncrease, UpgradeType.speedUpgrade);
    }
    
    public String buyWarpGenerator() {
        // minimum tech level to produce: 6
        int MTLP = 7, warpCost = 1500, warpSpeedIncrease = 4;
        return upgrade(MTLP, warpCost, warpSpeedIncrease, UpgradeType.speedUpgrade);
    }
    
    public String buyGravityShield() {
        int MTLP = 5, gravityCost = 300, gravityHealthIncrease = 1;
        return upgrade(MTLP, gravityCost, gravityHealthIncrease, UpgradeType.healthUpgrade);
    }
    
    public String buyNeuronField() {
        int MTLP = 6, fieldCost = 500, fieldHealthIncrease = 2;
        return upgrade(MTLP, fieldCost, fieldHealthIncrease, UpgradeType.healthUpgrade);
    }
    
    public String buyUnobtanium() {
        int MTLP = 7, unobtaniumCost = 1500, unobtaniumHealthIncrease = 4;
        return upgrade(MTLP, unobtaniumCost, unobtaniumHealthIncrease, UpgradeType.healthUpgrade);
    }
    
    public String buyIncreasedCargo() {
        int MTLP = 7, cargoCost = 500, cargoIncrease = 5;
        return upgrade(MTLP, cargoCost, cargoIncrease, UpgradeType.capacityUpgrade);
    }
    
    public String upgrade(int cost, int increase, int MTLP, UpgradeType up) {
        // minimum tech level to produce: 5
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance = playerInventory.getBalance();
        Ship charShip = Singleton.getCharacter().getShip();
        
        if (techLevel < MTLP) {
            return "Planet not techy enough to buy upgrade";
        } else if (cost > currentBalance) {
            return "You don't have enough moneys";
        } else if (charShip.getCapacity() <= 0) {
            return "But Lt. Dan, you ain't got no more upgrades";
        }
        
        if (up == UpgradeType.attackUpgrade) {
            charShip.upgradeAttack(increase);
        } else if (up == UpgradeType.healthUpgrade) {
            charShip.upgradeHealth(increase);
        } else if (up == UpgradeType.speedUpgrade) {
            charShip.upgradeSpeed(increase);
        } else {
            charShip.upgradeCapacity(increase);
        }
        playerInventory.subtractFromBalance(cost);
        
        return null;
    }
}
