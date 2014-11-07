/*
 * This class represents a TravelEvent object. TravelEvents have a random chance to be 
 * encountered by the player as they travel from planet to planet. When a TravelEvent
* is encountered the player will see a dialogbox and be asked for input. This
 * input will determine the consequence for encountering said TravelEvent.
 */
package spacetrader;
import java.util.Random;
import java.util.ArrayList;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 *
 * @author Sarah
 */
public class TravelEvent {
    private String dialog;
    private Random roll;
    private float chance;
    private int id;
    private ArrayList<TravelEvent> eventList;
    
    /**
     * Private constructor that creates the actual event objects.
     * @param dialog
     * @param chance
     * @param id 
     */
    private TravelEvent(String dialog, float chance, int id) {
        this.dialog = dialog;
        this.chance = chance;
        this.id = id;
    }
    
    /** Constructor initializes the public object that the game will have
     * access to.
     */
    public TravelEvent() {
        this.roll = new Random();
        this.eventList = new ArrayList<TravelEvent>();
        this.populateEvents();
    }
    
    /**
     * Checks to see whether or not an event has been encountered by the
     * player.
     * @return Whether or not this event has been encountered
     */
    public boolean rollChance() {
        roll = new Random();
        return (roll.nextFloat() < chance);
    }

    public ArrayList<TravelEvent> getEvents() {
        return eventList;
    }
    
    public String getDialog() {
        return dialog;
    }
    
    /**
     * Populates eventList with all possible events for this round.
     * Currently very simple.. Future functionality will read cards from a 
     * text file and create TravelEvents based on the planet being traveled
     * to
     */
    public void populateEvents() {
       //generates event objects. Will only be called once.
       eventList.add(new TravelEvent("There's some cargo floating in space. Bring it on board?", (float)0.5, 0));
       eventList.add(new TravelEvent("Some space debris suddenly appears in your field of vision. Try and dodge?", (float)0.5, 1));
    }
    

    /**
     * For each event contained in eventList, this method randomly checks to see
     * if it occurs. If the event occurs the method gets input from the user
     * and takes the appropriate action according to the event.
     */
    
    public void handleEvents() {
        String resultDialog = "";
        for (TravelEvent e: eventList) {
            if (e.rollChance()) {
                //start dialog box
                Action response = Dialogs.create()
                .title("Something's Happened!")
                .masthead("Something has happened!")
                .message(e.dialog)
                .showConfirm();
                //end dialog box

                //check user's response
                if (response == Dialog.Actions.YES) {
                    //handles all cases where the user answered yes.
                    switch (e.id) {
                        case 0:
                            //needs check to make sure inventory is not full
                            Singleton.getCharacter().getInventory().add("Robots");
                            resultDialog = "1 box of Robots was added to the cargobay";
                            break;
                        case 1:
                            if (roll.nextFloat() > (0.1 * Singleton.getCharacter().getPilot())) {
                               Singleton.getCharacter().getShip().subtractDamage(5);
                               resultDialog = "You tried to dodge, but your skills just weren't good enough. Hull was damaged 5 points";
                            } else {
                                  resultDialog = "You skillfully navigate your way to saftey.";
                            }
                            break;
                        }
                } else {
                    //handles all cases where the user answered no.
                    switch (e.id) {
                        case 0:
                            resultDialog = "You ignore the floating cargo";
                            break;
                        case 1:
                            Singleton.getCharacter().getShip().subtractDamage(10);
                           resultDialog = "You don't react at all and your ship takes 10 points of damage.";
                            break;
                        }
                }
                 //start dialog box
                Action result = Dialogs.create()
                .title("Something's Happened!")
                .masthead("Something has happened!")
                .message(resultDialog)
                .showWarning();
                //end dialog box
            }
        }
    }
}
