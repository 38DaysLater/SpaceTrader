/*
 * Singleton is a static class so the universe and character can be accessed
 * at all times.
 */

package spacetrader;
import java.io.Serializable;

/**
 *
 * @author AfiqAzaibi
 */
public class Singleton implements Serializable {
    //CHECKSTYLE: OFF
    private static Character c;
    private static Universe u;
    //CHECKSTYLE: ON

     /**
     * @author Afiq
     * @param ch
     *set the character. should only happen once
     */
    public static void  setCharacter(Character ch) {
        c = ch;
    }

     /**
     * @author Afiq
     *set the universe. should only happen once
     * @param uni
     */
    public static void setUniverse(Universe uni) {
        u = uni;
    }


     /**
     * @author Afiq
     *gets the character.
     * @return c character
     */
    public static Character getCharacter() {
        return c;
    }

     /**
     * @author Afiq
     *get the universe.
     * @return u Universe
     */
    public static Universe getUniverse() {
        return u;
    }

}
