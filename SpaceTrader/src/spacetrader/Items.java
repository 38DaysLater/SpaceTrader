/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.ArrayList;

/**
 * This class represents all the items of the space trader universe
 * @author lsmoore
 */
public class Items {
    private static ArrayList<Item> list;
    
    static {
        Item water = new Item(0, 0, 2, 30, 3, 4, RandConditions.DROUGHT, 
                RandConditions.LOTSOFWATER, RandConditions.DESERT, "Water");
        list.add(water);
        
        Item furs = new Item(0, 0, 0, 250, 10, 10, RandConditions.COLD, 
                RandConditions.RICHFAUNA, RandConditions.LIFELESS, "Furs");
        list.add(furs);
        
        Item food = new Item(1, 0, 1, 100, 5, 5, RandConditions.CROPFAIL, 
                RandConditions.RICHSOIL, RandConditions.POORSOIL, "Food");
        list.add(food);
        
        Item ore = new Item(2, 2, 3, 350, 20, 10, RandConditions.WAR, 
                RandConditions.MINERALRICH, RandConditions.MINERALPOOR, "Ore");
        list.add(ore);
        
        Item games = new Item(3, 1, 6, 250, -10, 5, RandConditions.BOREDOM, 
                RandConditions.ARTISTIC, RandConditions.NEVER, "Games");
        list.add(games);
        
        Item firearms = new Item(3, 1, 5, 1250, -75, 100, RandConditions.WAR, 
                RandConditions.WARLIKE, RandConditions.NEVER, "Firearms");
        list.add(firearms);
        
        Item medicine = new Item(4, 1, 6, 650, -20, 10, RandConditions.PLAGUE, 
                RandConditions.LOTSOFHERBS, RandConditions.NEVER, "Medicine");
        list.add(medicine);
        
        Item machines = new Item(4, 3, 5, 900, -30, 5, RandConditions.LACKOFWORKERS, 
                RandConditions.NEVER, RandConditions.NEVER, "Machines");
        list.add(machines);
        
        Item narcotics = new Item(5, 0, 5, 3500, -125, 150, RandConditions.BOREDOM, 
                RandConditions.WEIRDMUSHROOMS, RandConditions.NEVER, "Narcotics");
        list.add(narcotics);
        
        Item robots = new Item(6, 4, 7, 5000, -150, 100, RandConditions.LACKOFWORKERS, 
                RandConditions.NEVER, RandConditions.NEVER, "Robots");
        list.add(robots);
    }
    
    public static ArrayList<Item> getList() {
        return list;
    }
    
    public static Item getItem(String name) {
        if (name.equals("Water")) {
            return list.get(0);
        } else if(name.equals("Furs")){
            return list.get(1);
         } else if (name.equals("Food")){
             return list.get(2);
         } else if (name.equals("Ore")){
             return list.get(3);
         } else if (name.equals("Games")){
             return list.get(4);
         } else if (name.equals("Firearms")){
             return list.get(5);
         } else if (name.equals("Medicine")){
             return list.get(6);
         } else if (name.equals("Machines")){
             return list.get(7);
         } else if (name.equals("Narcotics")){
             return list.get(8);
         } else if (name.equals("Robots")){
             return list.get(9);
         } else {
             return null;
         }
    }
    
}