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
public class SaveObject implements Serializable {
    private Character c;
    private Universe u; 

    /**
     * Saves the character and the universe it's in.
     * @param c character
     * @param u  universe
     */
    public SaveObject(Character c, Universe u) {
        this.c = c;
        this.u = u;
    }
    /**
     * Gets the character.
     * @return c character
     */
    public Character getCharacter() {
        return c;
    }
/**
 * Gets the universe.
 * @return u universe
 */
    public Universe getUniverse() {
        return u;
    }
}


// import data base package
//