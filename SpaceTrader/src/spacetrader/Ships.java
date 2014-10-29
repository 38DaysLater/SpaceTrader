/**
 * This class represents ships.  Every ship has a:
 * name, 
 * base price, 
 * MPL minimum pilot level
 * weight
 * attack
 * speed
 * fuel capacity
 * @author aazaibi
 */
package spacetrader;

import java.util.ArrayList;

/**
 *This is where all the ships in the game will go. 
 * @author AfiqAzaibi
 */
public class Ships {
    
    
        private static ArrayList<Ship> list;
    
    static {
        list = new ArrayList<Ship>();
    //    public Ship(String name, int price, int MLP, int weight, int attack, int speed, int fuelCapacity, int health){
        Ship tankShipOne = new Ship("Titan 1", 1000, 2, 70, 5, 4, 600, 100, 10);
        list.add(tankShipOne);
        
        Ship tankShipTwo = new Ship("Titan 2", 2000, 5, 85, 7, 4, 750, 125, 20);
        list.add(tankShipTwo);

        Ship tankShipThree = new Ship("Titan 3", 5000, 100, 10, 10, 5, 1000, 150, 50);
        list.add(tankShipThree);        
        
        
        
        Ship speedShipOne = new Ship("Banshee 1", 1000, 2, 25, 5, 5, 600, 40, 10); 
        list.add(speedShipOne);

        Ship speedShipTwo = new Ship("Banshee 2", 2000, 5, 15, 5, 7, 750, 50, 20);
        list.add(speedShipTwo);

        Ship speedShipThree = new Ship("Banshee 3", 5000, 10, 5, 5, 10, 1000, 60, 30);
        list.add(speedShipThree);        
        
        
        
        //ships that for people who don't invest in pilot
        Ship normalShipOne = new Ship("Shiny 1", 1000, 0, 50, 3, 4, 500, 70, 20);
        list.add(normalShipOne);
        
        Ship normalShipTwo = new Ship("Shiny 2", 2000, 0, 30, 4, 5, 700, 80, 40);
        list.add(normalShipTwo);

        
        
        Ship tradeShipOne = new Ship("Serenity 1", 1000, 0, 70, 5, 3, 600, 60, 100);
        list.add(tradeShipOne);

        Ship tradeShipTwo = new Ship("Serenity 2", 2000, 0, 75, 5, 2, 700, 70, 200);
        list.add(tradeShipTwo);        
        
        
    }
    
    public static ArrayList<Ship> getList() {
        return list;
    }
   
    public static Ship getShip(String name) {
        if (name.equals("HeavyOne")) {
            return list.get(0);
        } else if(name.equals("HeavyTwo")){
            return list.get(1);
         } else if (name.equals("HeavyThree")){
             return list.get(2);
         } else if (name.equals("FastOne")){
             return list.get(3);
         } else if (name.equals("FastTwo")){
             return list.get(4);
         } else if (name.equals("FastThree")){
             return list.get(5);
         } else if (name.equals("NormalOne")){
             return list.get(6);
         } else if (name.equals("NormalTwo")){
             return list.get(7);
         } else if (name.equals("TradeOne")){
             return list.get(8);
         } else if (name.equals("TradeTwo=")){
             return list.get(9);
         } else {
             return null;
         }
    }
    
    /*
    public static ArrayList<Item> getElligibleItems(int tl) {
        ArrayList<Item> newList = new ArrayList<Item>();
        for (int i = 0; i <= tl; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
    */

    
    
    
}
