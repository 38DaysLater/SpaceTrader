/*
 * The soul purpose of this is to make the game saveable and not
 * static. It's pretty lame. 
 */

package spacetrader;
import java.io.Serializable;
/**
 *
 * @author AfiqAzaibi
 */
public class SaveObject implements Serializable{
    Character c;
    Universe u; 
    
    public SaveObject(Character c, Universe u) {
        this.c = c;
        this.u = u;
    }
    
    public Character getCharacter() {
        return c;
    }

    public Universe getUniverse() {
        return u;
    }
}


// import data base package
//