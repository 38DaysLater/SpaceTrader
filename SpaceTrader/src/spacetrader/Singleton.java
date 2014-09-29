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
    
    private Character c; 
    private Universe u; 
    
    public Singleton(Character cha, Universe uni){
        c = cha;
        u = uni; 
    }

    public Character getCharacter(){
        return c;
    }
    
    public Universe getUniverse(){
        return u;
    }
    
}
