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
    private static final long serialVersionUID = 1;
    private Hashtable<String, ItemWrapper> list;
    private int balance = 0;
    private int capacity = 100;
    private int totalItemCount = 0;
    private Hashtable<String, Integer> priceList;
    private final ArrayList<String> elligibleItems;
    private int techLevel = 10;

    /**
    * This helper class contains the item along with the number of times it
    * appears. Every item present in the inventory is stored in a Hashtable
    * along with the number of times it appears in the list
    * @author lsmoore
    */

    private static class ItemWrapper implements Serializable{
        private static final long serialVersionUID = 1;
        private Item item;
        private int count;

        /**
         * Wraps the item. Provides a means to store the item.
         * @param item
         */
        public ItemWrapper(Item item) {
            this.item = item;
            count = 0;
        }
        /**
         * Provides a means to store the item with its count.
         * @param item
         * @param count
         */
        public ItemWrapper(Item item, int count) {
            this.item = item;
            this.count = count;
        }
        /**
         * Increments count.
         */
        public void incrementCount() {
            count++;
        }
        /**
         * Decrements count.
         */
        public void decrementCount() {
            count--;
        }
        /**
         * Adds num to the current count.
         * @param num
         */
        public void addCount(int num) {
            count += num;
        }
        /**
         * Subtracts num from the current count.
         * @param num
         */
        public void decrementCount(int num) {
            count -= num;
        }
    }

    /**
     * Creates an inventory with a specified techlevel.
     * @param techLevel
     */
    public Inventory(int techLevel) {
        this.techLevel = techLevel;
        list = new Hashtable<String, ItemWrapper>();
        elligibleItems = Items.getElligibleItems(techLevel);
    }
    /**
     * Creates an inventory without parameters.
     */
    public Inventory() {
        list = new Hashtable<String, ItemWrapper>();
        elligibleItems = Items.getElligibleItems(9);
    }
/**
 * Sets the price list.
 * @param priceList
 */
    protected void setPriceList(Hashtable<String, Integer> priceList) {
        this.priceList = priceList;
    }
/**
 * Gets the price list.
 * @return
 */
    protected Hashtable<String, Integer> getPriceList() {
        return priceList;
    }
/**
 * Adds an item to the list (aka hashtable).
 * @param name item to add
 */

    public void add(String name) {
        if (Items.getItem(name) == null || totalItemCount >= capacity) {
            return;
        }
        //if the item is alread in the inventory, increment its count by 1
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw = list.remove(name);
            iw.incrementCount();
            list.put(name, iw);
        } else {
            // the item isn't in the inventory, so add it along with a count of 1
            Item currItem = Items.getItem(name);
            ItemWrapper iw = new ItemWrapper(currItem);
            iw.incrementCount();
            list.put(name, iw);
        }

        totalItemCount++;

    }

/**
 * Adds an item to the list a certain number of times.
 * @param name item to add
 * @param num number of that item to add
 */
    public void add(String name, int num) {
        if (Items.getItem(name) == null || totalItemCount >= capacity) {
            return;
        }
        //if the item is alread in the inventory, increment its count by 1
        Set<String> set = list.keySet();
        if (set.contains(name)) {
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

        totalItemCount += num;

    }
/**
 * Finds the number of a specific items in the list.
 * @param name the item we're looking for
 * @return iw.count the number of times it occurs in the hashtable
 */

   public int getItemCount(String name) {
       if (name == null) {
           return -1;
       }
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            ItemWrapper iw =  list.get(name);
            return iw.count;
        } else if (elligibleItems.contains(name)){
            // the item isn't in the inventory, but it could have it
            return 0;
        } else {
            return -1;
        }
    }

    public int getItemPrice(String name) {
        if (name == null) {
           return -1;
       }
        Set<String> set = list.keySet();
        if (set.contains(name)) {
            return priceList.get(name);
        } else if (elligibleItems.contains(name)){
            // the item isn't in the inventory, but it could have it
            //System.out.print(priceList.keys());
            if (priceList.containsKey(name))
                    return 0;
            return priceList.get(name);
        } else {
            return -1;
        }

    }

/**
 * Removes exactly one item from the list
 * @param name the item we're looking to release
 * @return how many items of this type are still left (-1 if not present at all)
 */

    public int removeItem(String name) {
        if (Items.getItem(name) == null) {
            return 0;
        }
        Set<String> set = list.keySet();
        if (set.contains(name)) {
        // there is at least one instance of the item in the inventory
            int count = list.get(name).count;
            ItemWrapper iw = list.remove(name);

            //if there is only one item, remove it alltogether from the hastable
            if (iw.count == 1) {
                totalItemCount --;
                return 0;
            //else, just decrement its count by one
            } else {
                iw.decrementCount();
                list.put(iw.item.getName(), iw);
                totalItemCount --;
                return count - 1;
            }
        } else if (Items.getItem(name).getMTLU() <= techLevel){
            // the item isn't in the inventory, but it could have it
            return 0;
        } else {
            return -1;
        }

    }

 /**
 * Removes a certain number of items from the list.
 * @param name the item we're looking to release, and the number of times
 * to do so
 * @param quantitySelling
 * @return how many items of this type are still left (-1 if not present at all)
 */

    public int removeItem(String name, int quantitySelling) {
        if (Items.getItem(name) == null) {
            return 0;
        }
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
     * @return
     */
    public int getBalance() {
        return balance;
    }
    /**
     * Sets the balance.
     * @param x
     */
    public void setBalance(int x) {
        balance = x;
    }
    /**
     * Adds x to the current balance.
     * @param x
     */
    public void addToBalance(int x) {
        balance += x;
    }
    /**
     * Subtracts x from the current balance.
     * @param x
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
     * @param x
     */
    public void setCapacity(int x) {
        capacity = x;
    }

    /**
     * Gets the total item count.
     * @return totalItemCount
     */
    public int totalItemCount() {
        return totalItemCount;
    }
}
