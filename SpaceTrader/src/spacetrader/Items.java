/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class represents all the items of the space trader universe.
 * @author lsmoore
 */
public class Items implements Serializable {
    private static final long serialVersionUID = 1;
    private static ArrayList<Item> list;

    static {
        list = new ArrayList<Item>();

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
                RandConditions.ARTISTIC, RandConditions.NONE, "Games");
        list.add(games);

        Item firearms = new Item(3, 1, 5, 1250, -75, 100, RandConditions.WAR,
                RandConditions.WARLIKE, RandConditions.NONE, "Firearms");
        list.add(firearms);

        Item medicine = new Item(4, 1, 6, 650, -20, 10, RandConditions.PLAGUE,
                RandConditions.LOTSOFHERBS, RandConditions.NONE, "Medicine");
        list.add(medicine);

        Item machines = new Item(4, 3, 5, 900, -30, 5, 
                RandConditions.LACKOFWORKERS, RandConditions.NONE,
                RandConditions.NONE, "Machines");
        list.add(machines);

        Item narcotics = new Item(5, 0, 5, 3500, -125, 150,
                RandConditions.BOREDOM, RandConditions.WEIRDMUSHROOMS,
                RandConditions.NONE, "Narcotics");
        list.add(narcotics);

        Item robots = new Item(6, 4, 7, 5000, -150, 100,
                RandConditions.LACKOFWORKERS, RandConditions.NONE,
                RandConditions.NONE, "Robots");
        list.add(robots);
    }
/**
 * Gets the list.
 * @return list an array list of items
 */
    public static ArrayList<Item> getList() {
        return list;
    }
/**
 * Gets an item.
 * @param name name of item
 * @return element of list, or null
 */
    public static Item getItem(String name) {
        if (name == null) {
            return null;
        }
        if (name.equals("Water")) {
            return list.get(0);
        } else if (name.equals("Furs")) {
            return list.get(1);
         } else if (name.equals("Food")) {
             return list.get(2);
         } else if (name.equals("Ore")) {
             return list.get(3);
         } else if (name.equals("Games")) {
             return list.get(4);
         } else if (name.equals("Firearms")) {
             return list.get(5);
         } else if (name.equals("Medicine")) {
             return list.get(6);
         } else if (name.equals("Machines")) {
             return list.get(7);
         } else if (name.equals("Narcotics")) {
             return list.get(8);
         } else if (name.equals("Robots")) {
             return list.get(9);
         } else {
             return null;
         }
    }
/**
 * Gets potential items that can be added to the planet's market.
 * @param tl
 * @return newList
 */
    public static ArrayList<String> getElligibleItems(int tl) {
        ArrayList<String> newList = new ArrayList<String>();
        for (int i = 0; i <= tl; i++) {
            newList.add(list.get(i).getName());
        }
        return newList;
    }
}