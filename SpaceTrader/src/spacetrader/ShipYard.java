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
    //CHECKSTYLE: OFF
    private int fuelPrice;
    private int techLevel;
    private int repairCost;
    //CHECKSTYLE: ON


    /**
     * Constructor that sets the price adjustment and the tech level
     * of a planet's shipyard.
     * @param priceAdjustment
     * @param techLevel
     */
    public ShipYard(int priceAdjustment, int techLevel){
        this.fuelPrice = 10 + priceAdjustment;
        this.techLevel = techLevel;
        repairCost = 15 - techLevel;
    }

    /**
     * Simply calculates how much it costs to refuel.
     * show this when player wants to buy stuff
     * @param s Ship
     * @return int fuel cost
     */
    public int costOfRefuelCompletely(Ship s) {
        int fuelNeeded = s.getFuelCapacity() - s.getFuelLevel();
        return fuelNeeded * fuelPrice;
    }

    /**
     * subtracts balance from player and refuels the ship completely.
     * returns error message or nothing depending if player has enough funds
     * @return String error with transaction
     */
    public String refuelCompletely() {
        String returnString = null;
        
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance
                = Singleton.getCharacter().getInventory().getBalance();
        Ship charShip = Singleton.getCharacter().getShip();

        //check to see if enough fuel
        if (currentBalance < costOfRefuelCompletely(charShip)) {
            returnString = "You don't have enough to refuel all the way";
        }
        //subtracts price and fills fuel up
        else {
            playerInventory
                    .subtractFromBalance(costOfRefuelCompletely(charShip));
            charShip.fillFuel();
            
        }
        return returnString;
    }


/**
 * Refuels the fuel tank partially if the player has enough money.
 * If the player doesn't have enough money, returns an error message.
 * @param fuelMoney
 * @return
 */
    public String refuelPartially(int fuelMoney){
        String returnString = null;
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance =
                Singleton.getCharacter().getInventory().getBalance();
        Ship charShip = Singleton.getCharacter().getShip();

        if (fuelMoney > currentBalance) {
            returnString = "You don't have enough moneys";
        } else if (fuelMoney < 0) {
            returnString = "Please enter a positive integer";
        } else if (fuelMoney > costOfRefuelCompletely(charShip)) {
            returnString = "Your ship can't handle that much fuel";
        }

        //if pass all those tests then add fuel.
        int fuelToAdd = fuelMoney / fuelPrice;
        playerInventory.subtractFromBalance(fuelMoney);
        charShip.addToFuel(fuelToAdd);
        return returnString;
    }

/**
 * Repairs the ship if the character has enough money. If the player doesn't
 * have enough money, returns an error message.
 * @return String
 */
    public String repairShip() {
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance =
                Singleton.getCharacter().getInventory().getBalance();
        Ship charShip = Singleton.getCharacter().getShip();
        String returnString = null;
        if (repairCost > currentBalance) {
            returnString = "You don't have enough moneys";
        } else if (repairCost < 0) {
            returnString = "Please enter a positive integer";
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
        return returnString;
    }

    /**
     * If the player has enough money, buys the ship. If the player
     * doesn't have enough money, returns an error message.
     * @param ship
     * @return String
     */
    public String buyShip(Ship ship) {
        Character myChar = Singleton.getCharacter();
        Inventory playerInventory = myChar.getInventory();
        int currentBalance = myChar.getInventory().getBalance();
        Ship charShip = myChar.getShip();
        int refundPrice = charShip.getPrice() / 2;

        String currentShipName = charShip.getName();
        String currentShipType = currentShipName.substring(0, 1);
        int lengthCurrent = currentShipName.length();
        int currentShipLevel =
                Integer.parseInt(currentShipName.substring(lengthCurrent - 1));

        String upgradeShipName = ship.getName();
        int lengthNew = upgradeShipName.length();
        String upgradeShipType = upgradeShipName.substring(0, 1);
        int upgradeShipLevel =
                Integer.parseInt(upgradeShipName.substring(lengthNew - 1));
        String returnString = null;
        if (ship.getPrice() > currentBalance + refundPrice) {
            returnString = "You don't have enough money for this pimpin ride";
        } else if (ship.getMLP() > myChar.getPilot()) {
            returnString = "You don't have the skill to pilot this pimpin ride";
        } else if (playerInventory.totalItemCount() > ship.getCapacity()) {
            returnString = "You have too much cargo on your current ship to get this "
                    + "new one. Sell your merchandise or pick a ship that can "
                    + "accomodate your cargo";
        } else if (currentShipLevel < upgradeShipLevel - 1) {
            returnString = "Your current ship level is " + currentShipLevel + ". "
                    + "You must upgrade to level " + (currentShipLevel + 1)
                    + " before upgrading to level " + upgradeShipLevel;
        } else if (upgradeShipLevel > 1
                && !upgradeShipType.equals(currentShipType)) {
            returnString = "You cannot upgrade to another type of ship";
        } else if (upgradeShipName.equals(currentShipName)) {
            returnString = "You already own that ship.";
        }


       //gives refund on current ship and deducts old one.
       playerInventory.addToBalance(refundPrice);
       myChar.updateShip(ship);
       playerInventory.subtractFromBalance(ship.getPrice());

       //so if you uncomment this code and upgrade your ship from type 1 to 2 to 3,
       /* */

       //transfer upgrades
       ship.upgradeHealth(charShip.getHealthUpgrades());
       ship.upgradeAttack(charShip.getAttackUpgrades());
       ship.upgradeSpeed(charShip.getSpeedUpgrades());
       ship.upgradeCapacity(charShip.getCapacityUpgrades());
       //update capacity
       ship.updateUpgradesLeft(charShip.getUpgradesLeft());
       /* */
       return returnString;
    }
    /*public int getFuelPrice() {
        return fuelPrice;
    }*/
    /**
     * Gets the repair cost.
     * @return repairCost
     */
    public int getRepairCost() {
        return repairCost;
    }

    /**
     * Method to buy Neuron Torpedos.
     * @return String error
     */
    public String buyNeuronTorpedos() {
        // minimum tech level to produce: 5
        int mTLP = 5, torpedoCost = 300, torpedoAttackIncrease = 1;
        return upgrade(mTLP, torpedoCost, torpedoAttackIncrease,
                UpgradeType.attackUpgrade);
    }

    /**
     * Method to buy Plasma Blasters.
     * @return String error
     */
    public String buyPlasmaBlasters() {
        // minimum tech level to produce: 6
        int mTLP = 6, plasmaCost = 500, plasmaAttackIncrease = 2;
        return upgrade(mTLP, plasmaCost, plasmaAttackIncrease,
                UpgradeType.attackUpgrade);
    }

    /**
     * Method to buy Death Star Laser.
     * @return String error
     */
    public String buyDeathStarLaser() {
        // minimum tech level to produce: 7
        int mTLP = 7, laserCost = 1500, laserAttackIncrease = 4;
        return upgrade(mTLP, laserCost, laserAttackIncrease,
                UpgradeType.attackUpgrade);
    }

    /**
     * Method to buy Nitrogen Booster.
     * @return String error
     */
    public String buyNitrogenBooster() {
        // minimum tech level to produce: 5
        int mTLP = 5, boostCost = 300, boosterSpeedIncrease = 1;
        return upgrade(mTLP, boostCost, boosterSpeedIncrease,
                UpgradeType.speedUpgrade);
    }

    /**
     * Method to buy Flux Capacitor.
     * @return String error
     */
    public String buyFluxCapacitor() {
        int mTLP = 6, fluxCost = 500, fluxSpeedIncrease = 2;
        return upgrade(mTLP, fluxCost, fluxSpeedIncrease,
                UpgradeType.speedUpgrade);
    }

    /**
     * Method to buy Warp Generator.
     * @return String error
     */
    public String buyWarpGenerator() {
        // minimum tech level to produce: 6
        int mTLP = 7, warpCost = 1500, warpSpeedIncrease = 4;
        return upgrade(mTLP, warpCost, warpSpeedIncrease,
                UpgradeType.speedUpgrade);
    }

    /**
     * Method to buy gravity shield.
     * @return String error
     */
    public String buyGravityShield() {
        int mTLP = 5, gravityCost = 300, gravityHealthIncrease = 1;
        return upgrade(mTLP, gravityCost, gravityHealthIncrease,
                UpgradeType.healthUpgrade);
    }

    /**
     * Method to buy Neuron field.
     * @return String error
     */
    public String buyNeuronField() {
        int mTLP = 6, fieldCost = 500, fieldHealthIncrease = 2;
        return upgrade(mTLP, fieldCost, fieldHealthIncrease,
                UpgradeType.healthUpgrade);
    }

    /**
     * method to buy Unobtanium.
     * @return String error
     */
    public String buyUnobtanium() {
        int mTLP = 7, unobtaniumCost = 1500, unobtaniumHealthIncrease = 4;
        return upgrade(mTLP, unobtaniumCost, unobtaniumHealthIncrease,
                UpgradeType.healthUpgrade);
    }

    /**
     * Method to buy more Cargo space.
     * @return String error
     */
    public String buyIncreasedCargo() {
        int mTLP = 7, cargoCost = 500, cargoIncrease = 5;
        return upgrade(mTLP, cargoCost, cargoIncrease,
                UpgradeType.capacityUpgrade);
    }

    /**
     * Method that adds an upgrade to the inventory.
     * returns string error for various reasons
     * @param mTLP
     * @param cost
     * @param increase
     * @param up
     * @return String error
     */
    public String upgrade(int mTLP, int cost, int increase, UpgradeType up) {
        // minimum tech level to produce: 5
        Inventory playerInventory = Singleton.getCharacter().getInventory();
        int currentBalance = playerInventory.getBalance();
        Ship charShip = Singleton.getCharacter().getShip();
        String returnString = null;
        if (techLevel < mTLP) {
            returnString = "Planet not techy enough to buy upgrade";
        } else if (cost > currentBalance) {
            returnString = "You don't have enough moneys";
        } else if (charShip.getUpgradesLeft() <= 0) {
            returnString = "But Lt. Dan, you ain't got no more upgrades";
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

        return returnString;
    }
}
