/**
 * This class is the character creation controller class.
 * It governs the FXML screen containing the set up of the character
 * The class entails sliders which set the pilot, fight, engineer, and tech level
 * It also establishes the limit of skill points available at the beginning of the game
 * @author Olivia
 */

package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

public class CharacterCreationController implements Initializable {
    @FXML
    private Slider pilotSlider;
    @FXML
    private Slider fightSlider;
    @FXML
    private Slider tradeSlider;
    @FXML
    private Slider engineerSlider;
    @FXML
    private Button okBut;
    @FXML
    private Button cancelBut;
    @FXML
    private TextField nameBox;
    @FXML
    private Label spNumLabel;
    private final int sp = 16;
    private int value = 0, pilotVal, fightVal, tradeVal, enginVal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Take in values from sliders and update skillpoint label
        pilotSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != oldValue.intValue()) {
                value += newValue.intValue() - oldValue.intValue();
                if (value > sp) {
                    spNumLabel.setText(Integer.toString(sp-value));
                } else {
                    pilotVal = newValue.intValue();
                    spNumLabel.setText(Integer.toString(sp - value));
                }
            }
        });
        fightSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != oldValue.intValue()) {
                value += newValue.intValue() - oldValue.intValue();
                if (value > sp) {       
                    spNumLabel.setText(Integer.toString(sp - value));
                } else {
                    fightVal = newValue.intValue();
                    spNumLabel.setText(Integer.toString(sp - value));
                }
            }
        });
        tradeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != oldValue.intValue()) {
                value += newValue.intValue() - oldValue.intValue();
                if (value > sp) {      
                    spNumLabel.setText(Integer.toString(sp - value));
                } else {
                    tradeVal = newValue.intValue();
                    spNumLabel.setText(Integer.toString(sp - value));
                }
            }
        });
        engineerSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != oldValue.intValue()) {
                value += newValue.intValue() - oldValue.intValue();
                if (value > sp) {
                    spNumLabel.setText(Integer.toString(sp - value));
                } else {
                    enginVal = newValue.intValue();
                    spNumLabel.setText(Integer.toString(sp - value));
                }
            }
        });
    }    

    /**
     * Handles when the ok button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void okButtonHandler(ActionEvent event) {
        //checks to see if there was a name inputed
        if (nameBox.getText() == null || nameBox.getText().isEmpty()) {
            //dialog box start
              Dialogs.create()
              .title("No name")
              .masthead("You do not have a name.")
              .message( "Please enter a name before you confirm.")
              .showWarning();
            //end dialog box
        //makes sure not too many skill points are used
        } else if (Integer.parseInt(spNumLabel.getText()) < 0 ) {
            //dialog box start
              Dialogs.create()
              .title("OH NO!")
              .masthead("You have negative skill points")
              .message( "Please be sure your points are not negative before you confirm.")
              .showWarning();
            //end dialog box
        } else {
            //instantiates character and Universe, then brings up Universe screen 
            Character ch = new Character(nameBox.getText(), pilotVal, fightVal, tradeVal, enginVal);
            Singleton.setCharacter(ch);
            try {
                Universe uni = new Universe();
                Singleton.setUniverse(uni);
                
                //this is so the character starts off same place every time. 
                Singleton.getCharacter().setCurrentPlanet(uni.getSolarSystem(0).getPlanet(0));
                
//                System.out.println(Singleton.getCharacter().getCurrentPlanet());
                Parent root = FXMLLoader.load(getClass().getResource("Universe.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Space Trader");
                stage.setScene(scene);
                stage.show();

    //        //hide current window
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                System.out.println("IOExcpetion caught in CharacterCreationController.java line:147");
            }
        }
    }
    
    /**
     * Handles when the cancel button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void cancelButtonHandel(ActionEvent event) {
        //hides this window and brings up the start screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("SpaceTrader");
            stage.setScene(scene);
            stage.show();

//        //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    /**
     * Handles when the cancel button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void nameHandler(ActionEvent event) {
        
    }
    
}
