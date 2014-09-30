/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author AfiqAzaibi
 */
public class testingShit {
    public static void main(String args[]){
        System.out.println("EG");
        Market m = new Market(3,3);
        Character c = new Character("Johnny",4,3,4,2);
        System.out.println();
        
        Inventory mi = m.getInventory();
        Inventory ci = c.getInventory();
        
        System.out.println("Market has this much water : " + mi.getItemCount("Water"));
        System.out.println("Player has this much water : " + ci.getItemCount("Water"));        
        System.out.println();

        System.out.println("Market has this much money : " + mi.getBalance());
        System.out.println("Player has this much money : " + ci.getBalance());        
        
        m.sellItem("Water", c, 1);
        m.sellItem("Water", c, 1);
        m.sellItem("Water", c, 1);
        
        System.out.println("--------");
        
        
        
        System.out.println("Market has this much water : " + mi.getItemCount("Water"));
        System.out.println("Player has this much water : " + ci.getItemCount("Water"));        
        System.out.println();

        System.out.println("Market has this much money : " + mi.getBalance());
        System.out.println("Player has this much money : " + ci.getBalance());        
        
        
        
        System.out.println("GOEIJ");
    }
    
}
