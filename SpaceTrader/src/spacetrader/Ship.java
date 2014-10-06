/*
 *This class acts as the vessel that the character has. The player
 *will have a ship at all times but this can be interchanged
 *Ship keeps track of fuel and its specs will be taken into account
 *for mini games.
 */

package spacetrader;

/**
 *
 * @author AfiqAzaibi
 */
public class Ship {
    private int fuel = 10000;
    
    public Ship(){
        Inventory shipInventory = new Inventory();
    }
    /**
     * @author Hutchinson
     * substractFuel() will decrement the fuel levels based on how far you've moved
     * @param int fuel, int distance
     * @return new fuel level
     */
    public int subtractFuel(int fuel, int distance, Character player) {
        //int val = 0;
        double pn = player.getPilot() / 10;
        /*int fn = player.getFight();
        int tn = player.getTrade();
        int en = player.getEngineer();
        int hn = player.getHealth();
        String nn = player.getName();
        nn = nn.toLowerCase();
        int[] array = new int[nn.length()];
        for (int i = 0; i <= nn.length(); i++) {
            array[i] = nn.charAt(i) - 48;
            val += array[i];
        }*/
        fuel -= distance/pn;
        return fuel;
    }
    
    public int getFuelLevel() {
        return fuel;
    }
}
