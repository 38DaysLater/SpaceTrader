package spacetrader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.io.Serializable;

/**
 * This class represents an Inventory.  Every item present in the inventory is
 * stored in a Hashtable along with the number of times it appears in the list.
 * The key is the name of the item, and the value is the item itself along with
 * the number of times it appears
 * @author lsmoore
 */
public class Inventory implements Serializable {
    //CHECKSTYLE: OFF
    private Hashtable<String, ItemWrapper> list;
    private int balance = 0;
    private int capacity = 100;
    private int totalItemCount = 0;
    private Hashtable<String, Integer> priceList;
    private final ArrayList<String> elligibleItems;
    private int techLevel = 10;
    //CHECKSTYLE: ON

    /**
    * This helper class contains the item along with the number of times it
    * appears. Every item present in the inventory is stored in a Hashtable
    * along with the number of times it appears in the list.
    * @author lsmoore
    */

    private class ItemWrapper implements Serializable {
        //CHECKSTYLE: OFF
        private Item item;
        private int count;
        //CHECKSTYLE: ON
/**
 * Represents an item in the inventory.
 * @param item
 */
        public ItemWrapper(Item item) {
            this.item = item;
            count = 0;
        }
/**
 * Keeps up with the count of each item.
 * @param item
 * @param count 
 */
        public ItemWrapper(Item item, int count) {
            this.item = item;
            this.count = count;
        }
/**
 * Increments the number of an item.
 */
        public void incrementCount() {
            count++;
        }
/**
 * Decrements the number of an item.
 */
        public void decrementCount() {
            count--;
        }
/**
 * Add num to the current count.
 * @param num
 */
        public void addCount(int num) {
            count += num;
        }
/**
 * Subtract num from the current count.
 * @param num
 */
        public void decrementCount(int num ) {
            count -= num;
        }
    }

/**
 * This is the constructor. It creates an empty hashtable in which
 * to store the items.
 * @param None
 */
    public Inventory(int techLevel) {
        this.techLevel = techLevel;
        list = new Hashtable<String, ItemWrapper>();
        elligibleItems = Items.getElligibleItems(techLevel);
    }
/**
 * Constructor without parameters. 
 */
    public Inventory() {
        list = new Hashtable<String, ItemWrapper>();
        elligibleItems = Items.getElligibleItems(9);
    }
/**
 * Sets the inventory's price list.
 * @param priceList
 */
    public void setPriceList(Hashtable<String, Integer> priceList) {
        this.priceList = priceList;
    }
/**
 * Gets the inventory's price list.
 * @return priceList
 */
    public Hashtable<String, Integer> getPriceList() {
        return priceList;
    }
/**
 * Adds an item to the list (aka hashtable).
 * @param name
 */
    public void add(String name) {
        if (Items.getItem(name) == null) {
            return;
        }
        //if the item is alread in the inventory, increment its count by 1
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw = list.remove(name);
            iw.incrementCount();
            list.put(name, iw);
        } else {
            // the item isn't in the inventory, so add it along with
            // a count of 1
            Item currItem = Items.getItem(name);
            ItemWrapper iw = new ItemWrapper(currItem);
            list.put(name, iw);
        }

        totalItemCount++;

    }

/**
 * Adds an item to the list a certain number of times.
 * @param name
 * @param num
 */

    public void add(String name, int num) {
        if (Items.getItem(name) == null) {
            return;
        }
        //if the item is alread in the inventory, increment its count by 1
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw = list.remove(name);
            iw.addCount(num);
            list.put(name, iw);
        } else {
            // the item isn't in the inventory, so add it along with
            // a count of 1
            Item currItem = Items.getItem(name);
            ItemWrapper iw = new ItemWrapper(currItem);
            iw.addCount(num);
            list.put(name, iw);
        }

        totalItemCount += num;

    }
/**
 * Finds the number of a specific items in the list.
 * @param name name of item
 * @return the number of times it occurs in the hashtable
 */

   public int getItemCount(String name) {
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw =  list.get(name);
            return iw.count;
        } else if (elligibleItems.contains(name)) {
            // the item isn't in the inventory, but it could have it
            return 0;
        } else {
            return -1;
        }
    }
/**
 * Gets an item's price.
 * @param name name of item
 * @return priceList.get(name)
 */
    public int getItemPrice(String name) {
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            return priceList.get(name);
        } else if (elligibleItems.contains(name)) {
            // the item isn't in the inventory, but it could have it
            //System.out.print(priceList.keys());
            if (priceList.contains(name)) {
                    return 0;
            }
            return priceList.get(name);
        } else {
            return -1;
        }

    }

/**
 * Removes exactly one item from the list.
 * @param name name of item
 * @return count how many items of this type are still left (-1 if not
 * present at all)
 */

    public int removeItem(String name) {
        Set<String> set = list.keySet();
        if (set.contains(name)) {
        // there is at least one instance of the item in the inventory
            int count = list.get(name).count;
            ItemWrapper iw = list.remove(name);

            //if there is only one item, remove it alltogether from the hastable
            if (iw.count == 1) {
                totalItemCount--;
                return 0;
            //else, just decrement its count by one
            } else {
                iw.decrementCount();
                list.put(iw.item.getName(), iw);
                totalItemCount--;
                return count - 1;
            }
        } else if (Items.getItem(name).getMTLU() <= techLevel) {
            // the item isn't in the inventory, but it could have it
            return 0;
        } else {
            return -1;
        }

    }

 /**
 * Removes a certain number of items from the list
 * @param the item we're looking to release, and the number of times to do so
 * @return how many items of this type are still left (-1 if not present at all)
 */

    public int removeItem(String name, int quantitySelling) {
        Set<String> set = list.keySet();
        if (set.contains(name)) {
        // there is at least one instance of the item in the inventory
            int count = list.get(name).count;
            ItemWrapper iw = list.remove(name);

            //if there is only one item, remove it alltogether from the hastable
            if (iw.count == quantitySelling) {
                totalItemCount -= count;
                return 0;
            //else, just decrement its count by one
            } else if (iw.count > quantitySelling) {
                iw.decrementCount(quantitySelling);
                list.put(iw.item.getName(), iw);
                totalItemCount -= quantitySelling;
                return count - quantitySelling;
            } else {
                return -1;
            }
        } else if (Items.getItem(name).getMTLU() <= techLevel) {
            // the item isn't in the inventory, but it could have it
            return 0;
        } else {
            return -1;
        }

    }

    /**
     * Gets the balance.
     * @return balance
     */
    public int getBalance() {
        return balance;
    }
/**
 * Adds to the current balance
 * @param x number to add to current balance
 */
    public void addToBalance(int x) {
        balance += x;
    }
/**
 * Subtracts from the current balance.
 * @param x number to subtract from current balance
 */
    public void subtractFromBalance(int x) {
        balance -= x;
    }


/**
 * Gets the capacity.
 * @return capacity
 */
    public int getCapacity() {
        return capacity;
    }
/**
 * Sets the capacity.
 * @param x value to set capacity to
 */
    //set capacity
    public void setCapacity(int x){
        capacity = x;
    }
/**
 * Gets the total item count.
 * @return totalitemcount
 */

    public int totalItemCount() {
        return totalItemCount;
    }
    /**
     * Gets all the items, and puts them into a hashtable.
     * @return 
     */
     public Hashtable<String, ItemWrapper> getItems() {
         return list;
     }
}
