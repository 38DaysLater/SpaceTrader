package spacetrader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Hashtable;
import java.util.Set;

/**
 * This class represents an Inventory.  Every item present in the inventory is
 * stored in a Hashtable along with the number of times it appears in the list
 * @author lsmoore
 */
public class Inventory {
    
    private Hashtable<String, ItemWrapper> list;
    
    private class ItemWrapper {
        private Item item;
        private int count;
        
        public ItemWrapper(Item item) {
            this.item = item;
            count = 0;
        }
        
        public ItemWrapper(Item item, int count) {
            this.item = item;
            this.count = count;
        }
        
        public void incrementCount() {
            count++;
        }
        
        public void decrementCount() {
            count--;
        }
        
        public void addCount(int num) {
            count += num;
        }
    }

/**
 * This is the constructor. It creates an empty hashtable to store the items in
 * @param None
 */
    public Inventory() {
        list = new Hashtable<String, ItemWrapper>();
    }
/**
 * Adds an item to the list (aka hashtable)
 * @param item to add
 * @return None
 */
    
    public void add(String name) {
        if (Items.getItem(name) == null) {
            return;
        }
        //if the item is alread in the inventory, increment its count by 1
        if (list.contains(name)){
            ItemWrapper iw = list.remove(name);
            iw.incrementCount();
            list.put(name, iw);
        } else {
            // the item isn't in the inventory, so add it along with a count of 1
            Item currItem = Items.getItem(name);
            ItemWrapper iw = new ItemWrapper(currItem);
            list.put(name, iw);
        }
    }
    
/**
 * Adds an item to the list a certain number of times
 * @param item to add
 * @return None
 */
    
    public void add(String name, int num) {
        if (Items.getItem(name) == null) {
            return;
        }
        //if the item is alread in the inventory, increment its count by 1
        if (list.contains(name)){
            ItemWrapper iw = list.remove(name);
            iw.addCount(num);
            list.put(name, iw);
        } else {
            // the item isn't in the inventory, so add it along with a count of 1
            Item currItem = Items.getItem(name);
            ItemWrapper iw = new ItemWrapper(currItem);
            iw.addCount(num);
            list.put(name, iw);
        }
    }
/**
 * Finds the number of a specific items in the list
 * @param the item we're looking for
 * @return the number of times it occurs in the hashtable
 */

    public int getItemCount(String name) {
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw =  list.get(name);
            return iw.count;
        } else {
            // the item isn't in the inventory, so its count is zero
            return 0;
        }
    }
    
    public int getItemPrice(String name) {
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw =  list.get(name);
            return iw.item.getFinalPrice();
        } else {
            // the item isn't in the inventory, so its count is zero
            return -1;
        }
    }
   
/**
 * Removes exactly one item from the list
 * @param the item we're looking to release
 * @return how many items of this type are still left (-1 if not present at all)
 */
    
    public int removeItem(String name) {
        if (list.contains(name)){
            // there is at least one instance of the item in the inventory
            int count = list.get(name).count;
            ItemWrapper iw = list.remove(name);
            
            //if there is only one item, remove it alltogether from the hastable
            if (iw.count == 1) {
                return 0;
            //else, just decrement its count by one
            } else {
                iw.decrementCount();
                list.put(iw.item.getName(), iw);
                return count - 1;
            }
        } else {
            // the item isn't in the inventory, so just -1
            return -1;
        }
    }
}
