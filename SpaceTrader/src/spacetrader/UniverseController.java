/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class is the universe controller class.
 * It governs the FXML screen containing the space trader universe
 * The class entails universe, market, and current planet tabs
 * It also sets up everything the user needs to play the game
 * @author Olivia
 */
package spacetrader;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

//imports for saving/loading game
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import javafx.scene.layout.Pane;

//import java.awt.Image;

import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class UniverseController implements Initializable {
    @FXML
    private Canvas universeCanvas;// = new Canvas(796.0, 530.0);
    private Universe uni = Singleton.getUniverse();
    private Character cha = Singleton.getCharacter();
    private String success;
  
    @FXML
    private Tab planetNameTab;
    @FXML
    private Tab marketTab;
    @FXML
    private Label currentSSLabelCanvas;
    @FXML
    private Label fuelLabel;
    @FXML
    private Label planPosUni;
    @FXML
    private Button uniButton;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label saveLabel, exitLabel, loadLabel;
    @FXML
    private Tab shipyardTab;
    @FXML
    private Pane planetPane;
    @FXML
    private AnchorPane marketPane;
    @FXML
    private Pane shipyardPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        planetPane.getChildren().clear();
        planetPane.getChildren().add(FXMLLoader.load(
                getClass().getResource("PlanetPage.fxml")));
        } catch (IOException e) {
        }
        if (((Planet) cha.getCurrentPlanet()[0]).hasShipYard()) {
            shipyardTab.setDisable(false);
        } else {
            shipyardTab.setDisable(true);
        }
    }
    /**
     * Handles when the planet tab is selected
     * @param Event event
     */
    @FXML
    private void PlanetNameTabSelected(Event event) {
        if (planetNameTab.isSelected()) {
            System.out.println("planet tab");
            try {
//                planetPane.getChildren().clear();
                planetPane.getChildren().add(FXMLLoader.load(
                    getClass().getResource("PlanetPage.fxml")));
            } catch (IOException e) {
            }
        }
    }
   /**
     * Handles when the market tab is selected.
     * @param event
     */
    @FXML
    private void marketTabSelected(Event event) {
        if (marketTab.isSelected()) {
            System.out.println("market tab");
            try {
//                marketPane.getChildren().clear();
                marketPane.getChildren().add(FXMLLoader.load(
                    getClass().getResource("MarketPage.fxml")));
            } catch (IOException e) {
                System.out.println("Exception caught in market");
            }
        }
    }
    /**
     * Handles when the shipyard tab is selected
     * @param Event event
     */
    @FXML
    private void shipyardTabSelected(Event event) {
        if (shipyardTab.isSelected()) {
            System.out.println("ship tab");
            try {
//                shipyardPane.getChildren().clear();
                shipyardPane.getChildren().add(FXMLLoader.load(
                    getClass().getResource("ShipyardPage.fxml")));
            } catch (IOException e) {
                System.out.println("Exception caught in shipyard");
            }
        }
    }
    
/****************************************************
 *                  SHIPYARD END                    *
 ****************************************************/

/****************************************************
 *                  UNIVERSE TAB                    *
 ****************************************************/
    /**
     * Handles when the universe tab is selected
     * @param Event event
     */
    @FXML
    private void universeTabSelected(Event event) {
        GraphicsContext gc = universeCanvas.getGraphicsContext2D();
        drawSolarSystem(gc);
        int fuelLevel = Singleton.getCharacter().getShip().getFuelLevel();
        String fuelString = Integer.toString(fuelLevel);
        fuelLabel.setText("Fuel level: " + fuelString);
    }
    /**
     * Handles when the universeCanvas is clicked
     * @param MouseEvent event
     */
    @FXML
    private void uniCanvasMouseClicked(MouseEvent event) {
        if (!currentSSLabelCanvas.getText().equals("Universe")) {
            Object[] ss = cha.getCurrentSolarSystem();
            List<Planet> planets = (List<Planet>)ss[2];
            for (Planet p : planets) {
                if (p.isHit(event.getX() - universeCanvas.getWidth()/2, event.getY() - universeCanvas.getHeight()/2)) {
                    //dialog box to travel to planet
                    if (p != cha.getCurrentPlanet()[0]) {
                        int dist = cha.checkDistance(p);
                        int dist1 = cha.checkDistance(p.getSolarSystem());
                        dist = dist + dist1;
                        Action response = Dialogs.create()
                        .title("Confirm Travel")
                        .masthead("It will cost " + cha.getShip().calcFuelForTravel(dist) + " fuel to travel here. \nDo you want to continue?")
                        .message("You have chosen to travel to:\n" + p.toString())
                        .showConfirm();

                        if (response == Dialog.Actions.YES) {
                            // ... user chose YES
                            /**check if enough fuel to get there
                             * bring up travel page - for events and decrement fuel
                             */
                            //set current planet and solarsystem
                            String thing = cha.getShip().checkSufficientFuel(dist);
                            if(thing == null) {
                                //player has enoughfuel to rech destination
                                //trigger event
                                /*creates a travel event object to handle random events
                                * eventually it will read from a text document so the
                                * events will have different chances based on where we are in the universe
                                *and be different each time the player travels.
                                * but for now it is very simple.
                                */
                                TravelEvent events = new TravelEvent();
                                events.handleEvents();
                                cha.getShip().subtractFuel(cha.getShip().getFuelLevel(), dist);
                                cha.setCurrentPlanet(p);
                                //sets current shown tab to the planet info tab
                                if (((Planet) cha.getCurrentPlanet()[0]).hasShipYard()) {
                                    shipyardTab.setDisable(false);
                                } else {
                                    shipyardTab.setDisable(true);
                                }
                                tabPane.getSelectionModel().select(0);
                            } else {
                                Dialogs.create()
                                .title("WARNING!")
                                .masthead("You cannot travel here")
                                .message(thing)
                                .showWarning();                            }
                        } else {
                            // ... user chose NO, CANCEL, or closed the dialog
                        }
                    } else {
                        Dialogs.create()
                        .title("Current Planet")
                        .masthead("You are on this planet.")
                        .message(p.toString())
                        .showInformation();
                    }
                }
            }
        } else {
            SolarSystem[] solar = uni.getAllSolarSystems();
            for (SolarSystem p : solar) {
                if (p.isHit(event.getX() - universeCanvas.getWidth()/2, event.getY() - universeCanvas.getHeight()/2)) {
                    //dialog box for going to solar system and then set current solarsystem
                    cha.setCurrentSolarSystem(p);
                    drawSolarSystem(universeCanvas.getGraphicsContext2D());
                    //show button
                }
            }
        }
    }

    /**
     * Handles when the mouse is moved on the universe canvas
     * @param MouseEvent event
     */
    @FXML
    private void uniCanvasMouseMoved(MouseEvent event) {
        boolean hit;
        if (!currentSSLabelCanvas.getText().equals("Universe")) {
            hit = false;
            Object[] ss = cha.getCurrentSolarSystem();
            List<Planet> planets = (List<Planet>)ss[2];
            for (Planet p : planets) {
                if (p.isHit(event.getX() - universeCanvas.getWidth()/2, event.getY() - universeCanvas.getHeight()/2)) {
                    planPosUni.setText(p.getPlanetName() + ": [" + p.getLocation()[0] + ", " + p.getLocation()[1] + "]");
                    hit = true;
                }
            }
            if(!hit) {
                planPosUni.setText(" ");
            }
        } else {
            hit = false;
            SolarSystem[] solar = uni.getAllSolarSystems();
            for (SolarSystem p : solar) {
                if (p.isHit(event.getX() - universeCanvas.getWidth()/2, event.getY() - universeCanvas.getHeight()/2)) {
                    hit = true;
                    planPosUni.setText(p.getName() + ": [" + p.getLocation()[0] + ", " + p.getLocation()[1] + "]");
                }
            }
            if (!hit) {
                planPosUni.setText(" ");
            }
        }
    }
    /**
     * Handles when the universe button is pressed
     * brings up the drawing of the solar systems
     * @param event
     */
    @FXML
    private void uniButtonHandler(ActionEvent event) {
        GraphicsContext gc = universeCanvas.getGraphicsContext2D();
        drawUniverse(gc);
        //hide button
    }
/****************************************************
 *                  UNIVERSE END                    *
 ****************************************************/

/****************************************************
 *                HELPER METHODS                    *
 ****************************************************/

    /**
     * Helper method that draws the current Solar System on the canvas
     * @param GraphicsContext gc
     */
    private void drawSolarSystem(GraphicsContext gc) {
        uniButton.setVisible(true);
        gc.clearRect(0, 0, universeCanvas.getWidth(), universeCanvas.getHeight());
        Object[] ss = cha.getCurrentSolarSystem();
        currentSSLabelCanvas.setText(((SolarSystem)ss[0]).getName());
        List<Planet> planets = (List<Planet>)ss[2];
        planets.stream().forEach((Planet p) -> {
            Image image = p.getPlanetPic();
            double h = image.getHeight();
            double w = image.getWidth();
            double x = p.getLocation()[0]+ (universeCanvas.getWidth() - w)/2;
            double y = p.getLocation()[1]+ (universeCanvas.getHeight() - h)/2;
            if (p == cha.getCurrentPlanet()[0]) {
                gc.setFill(Color.YELLOW);
                gc.setFont(new Font(18.0));
                gc.fillText("Current Planet", x, y - 2, w);
            }
            gc.drawImage(image, x, y);
        });
        //eventually draw fuel circle
    }
    /**
     * Helper method that draws the all of the Solar Systems on the canvas
     * @param GraphicsContext gc
     */
    private void drawUniverse(GraphicsContext gc) {
        uniButton.setVisible(false);
        gc.clearRect(0, 0, universeCanvas.getWidth(), universeCanvas.getHeight());
        SolarSystem[] solar = uni.getAllSolarSystems();
        currentSSLabelCanvas.setText("Universe");
        for (SolarSystem solar1 : solar) {
            Image image = solar1.getSSPic();
            gc.drawImage(image, solar1.getLocation()[0] + (universeCanvas.getWidth() - image.getWidth())/2, solar1.getLocation()[1] + (universeCanvas.getHeight() - image.getHeight())/2);
        }
    }
    

    /**
     * Helper method for getting the current planet
     * @param String item
     * @return Planet
     */
    private Planet currentP() {
        return ((Planet)cha.getCurrentPlanet()[0]);
    }

    /**
     * Helper method for creating dialogs
     * takes in string of the message to display
     * @param String mess
     */
    private void dialog(String mess) {
        //dialog box start
        Dialogs.create()
        .title("OH NO!")
        .masthead("There was an error with your transaction")
        .message(mess)
        .showWarning();
        //end dialog box
    }
/****************************************************
 *                 HELPERS END                      *
 ****************************************************/

/****************************************************
 *                 OPTIONS TAB                      *
 ****************************************************/

    /**
     * Changes the background of the label when the mouse
     * exits back to transparent
     * @param events
     */
    @FXML
    private void labelExited(MouseEvent event) {
        ((Node)event.getSource()).setStyle("-fx-background-color: transparent;");

    }

    /**
     * Changes the background of the label when the mouse
     * enters it
     * @param event
     */
    @FXML
    private void labelEntered(MouseEvent event) {
        ((Node)event.getSource()).setStyle("-fx-background-color: gray;");
    }

    /**
     * Handles when the save label is clicked in the options tab
     * @param event
     */
    @FXML
    private void saveLabelClicked(MouseEvent event) {
        SaveObject so = new SaveObject(Singleton.getCharacter(), Singleton.getUniverse());
        String name = Singleton.getCharacter().getName();

        String fileName = name + ".bin";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            //should be singleton but don't worry about it now.
            os.writeObject(so);
            os.close();

        } catch (Exception e) {
            System.out.println(e + " SOMETHING WENT WRONG IN SAVE");
            e.printStackTrace();
        }
    }

    /**
     * Handles when the exit label is clicked
     * brings up the initial loading page
     * @param event
     */
    @FXML
    private void exitLabelClicked(MouseEvent event) {
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
     * Handles when the load label is clicked
     * brings up the save file of the character name entered
     * @param event
     */
    @FXML
    private void loadLabelClicked(MouseEvent event) {
        Optional<String> response = Dialogs.create()
        .title("Load Game")
        .masthead("Please enter the name of the\ncharacter you would like to load.")
        .message("Character Name:")
        .showTextInput("Name");

        // One way to get the response value.
        if (response.isPresent()) {
            System.out.println("Your name: " + response.get());

            try {

                String fileName = response.get() + ".bin";
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
                SaveObject so2 = (SaveObject) is.readObject();
                Singleton.setCharacter(so2.getCharacter());
                Singleton.setUniverse(so2.getUniverse());
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Universe.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Space Trader");
                    stage.setScene(scene);
                    stage.show();

        //        //hide current window
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    System.out.println("IOExcpetion caught in UniverseController.java line:954");
                }
                is.close();
            } catch (Exception e){
//                System.out.println(e + "LOAD FAILED");
                Dialogs.create()
                .title("OH NO!")
                .masthead("Could not find save file")
                .message( "Please be sure a valid character name is entered.")
                .showWarning();
            }
        }
    }

/**************************************************
*                END OPTIONS TAB                 *
**************************************************/
}