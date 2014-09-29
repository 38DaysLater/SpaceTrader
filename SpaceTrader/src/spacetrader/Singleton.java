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
public class Singleton {
    
    private static Character c; 
    private static Universe u; 
    
    public static void  setCharacter(Character ch){
        c = ch;
    }
    
    public static void setUniverse(Universe uni){
        u = uni;
    }
    
    

    public static Character getCharacter(){
        return c;
    }
    
    public static Universe getUniverse(){
        return u;
    }
    
}
