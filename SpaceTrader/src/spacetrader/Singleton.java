/*
 * Singleton is a static class so the universe and character can be accessed
 * at all times. 
 */

package spacetrader;

/**
 *
 * @author AfiqAzaibi
 */
public class Singleton {
    
    private static Character c; 
    private static Universe u; 
    
    
     /**
     * @author Afiq
     * @param Character
     * @return none
     *set the character. should only happen once 
     */
    public static void  setCharacter(Character ch){
        c = ch;
    }

     /**
     * @author Afiq
     *set the universe. should only happen once 
     * @param Univesrse
     * @return none
     */
    public static void setUniverse(Universe uni){
        u = uni;
    }
    
    
     /**
     * @author Afiq
     *gets the character.  
     * @param none
     * @return Character
     */
    public static Character getCharacter(){
        return c;
    }
    
     /**
     * @author Afiq
     *get the universe. 
     * @param none
     * @return Universe
     */
    public static Universe getUniverse(){
        return u;
    }
    
}
