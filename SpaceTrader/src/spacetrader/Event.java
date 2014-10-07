/*
 * This class represents an event object. Events have a random chance to be 
 * encountered by the player as they travel from planet to planet. The event
 * constructor is private so only the event class can construct new event
 * objects. There is also a boolean value that returns true if the event
 * has been encountered.
 */
package spacetrader;
import java.util.Random;

/**
 *
 * @author Sarah
 */
public class Event {
    private String dialog;
    private boolean encountered;
    private Random roll;
    private float chance;
    private Event(String dialog, float chance) {
        this.dialog = dialog;
        this.chance = chance;
    }
    
    public boolean rollChance() {
        if (roll.nextFloat() <= chance) {
            encountered = true;
        } else {
            encountered = false;
        }
        return encountered;
    }
    
    public boolean getEncountered() {
        return encountered;
    }
    
    public String getDialog() {
        return dialog;
    }
}
