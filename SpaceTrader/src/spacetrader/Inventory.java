package spacetrader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Hashtable;

/**
 * This class represents an Inventory.  Every item present in the inventory is
 * stored in a Hashtable along with the number of times it appears in the list
 * @author lsmoore
 */
public class Inventory {
    
    private Hashtable<Item, Integer> list;

/**
 * This is the constructor. It creates an empty hashtable to store the items in
 * @param None
 */
    public Inventory() {
        list = new Hashtable<Item, Integer>();
    }
/**
 * Adds an item to the list (aka hashtable)
 * @param item to add
 * @return None
 */
    
    public void add(Item item) {
        //if the item is alread in the inventory, increment its count by 1
        if (list.contains(item)){
            int numInList = list.remove(item);
            list.put(item, numInList + 1);
        } else {
            // the item isn't in the inventory, so add it along with a count of 1
            list.put(item, 1);
        }
    }
    
/**
 * Adds an item to the list a certain number of times
 * @param item to add
 * @return None
 */
    
    public void add(Item item, int num) {
        //if the item is alread in the inventory, increment its count by 1
        if (list.contains(item)){
            int numInList = list.remove(item);
            list.put(item, numInList + num);
        } else {
            // the item isn't in the inventory, so add it along with a count of 1
            list.put(item, num);
        }
    }
/**
 * Finds the number of a specific items in the list
 * @param the item we're looking for
 * @return the number of times it occurs in the hashtable
 */

    public int getItemCount(Item item) {
        if (list.contains(item)){
            return list.get(item);
        } else {
            // the item isn't in the inventory, so its count is zero
            return 0;
        }
    }
    
/**
 * Removes exactly one item from the list
 * @param the item we're looking to release
 * @return how many items of this type are still left (-1 if not present at all)
 */
    
    public int removeItem(Item item) {
        if (list.contains(item)){
            // there is at least one instance of the item in the inventory
            int count = list.get(item);
            list.remove(item);
            
            //if there is only one item, remove it alltogether from the hastable
            if (count == 1) {
                return 0;
            //else, just decrement its count by one
            } else {
                list.put(item, count - 1);
                return count - 1;
            }
        } else {
            // the item isn't in the inventory, so just -1
            return -1;
        }
    }
}
