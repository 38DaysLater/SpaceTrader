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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import static javafx.embed.swing.SwingFXUtils.toFXImage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
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
    private Label planNameLabel, planInfoLabel, coinLabel;
    @FXML
    private Button newsButt;
    @FXML
    private Tab marketTab;
    @FXML
    private Button bcButt;
    @FXML
    private TextField bfoodText, bwatText, bmedText;
    @FXML
    private Label bfoodP, bWatP, bmedP;
    @FXML
    private Label bfoodQuan, bwatQuan, bmedQuan;
    @FXML
    private Button scButt;
    @FXML
    private TextField sfoodText, swatText, smedText;
    @FXML
    private Label sfoodP, swatP, smedP;
    @FXML
    private Label sfoodQuan, swatQuan, smedQuan;
    @FXML
    private Button bEButt;
    @FXML
    private TextField bFireTxt, bMacTxt, bRobTxt;
    @FXML
    private Label bFireP, bMacP, bRobP;
    @FXML
    private Label bFireQ, bMacQ, bRobQ;
    @FXML
    private Button sEButt;
    @FXML
    private TextField sFireTxt, sMacTxt, sRobTxt;
    @FXML
    private Label sFireP, sMacP, sRobP;
    @FXML
    private Label sFireQ, sMacQ, sRobQ;
    @FXML
    private Button bMButt;
    @FXML
    private TextField bFursTxt, bOreTxt, bGamTxt;
    @FXML
    private Label bFursP, bOreP, bGamP;
    @FXML
    private Label bFursQ, bOreQ, bGamQ;
    @FXML
    private Button sMButt;
    @FXML
    private TextField sFursTxt, sOreTxt, sGamTxt;
    @FXML
    private Label sFursP, sOreP, sGamP;
    @FXML
    private Label sFursQ, sOreQ, sGamQ;
    @FXML
    private Label currentSSLabelCanvas;
    @FXML
    private Label planPosUni;
    @FXML
    private Button uniButton;
    @FXML
    private TabPane tabPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        planNameLabel.setText(currentP().getPlanetName());
        String text = currentP().toString();
        int index = text.indexOf("\n");
        planInfoLabel.setText(currentP().toString().substring(index + 1));
    }    
    /**
     * Handles when the ok button is clicked inside the buy
     * consumables section of the market
     * @param ActionEvent event
     */
    @FXML
    private void buyConHandle(ActionEvent event) {
        try {
            if (!bfoodText.getText().isEmpty()) {
                success = currentP().getMarket().sellItem("Food", cha, Integer.parseInt(bfoodText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!bwatText.getText().isEmpty()) {
                success = currentP().getMarket().sellItem("Water", cha, Integer.parseInt(bwatText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!bmedText.getText().isEmpty()) {
                success = currentP().getMarket().sellItem("Medicine", cha, Integer.parseInt(bmedText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
        } catch(NumberFormatException e) {
            dialog("Please enter a valid Integer.");
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        sfoodQuan.setText(upSellLab("Food"));
        swatQuan.setText(upSellLab("Water"));
        smedQuan.setText(upSellLab("Medicine"));
        bfoodQuan.setText(upBuyLab("Food"));
        bwatQuan.setText(upBuyLab("Water"));
        bmedQuan.setText(upBuyLab("Medicine"));
        //clear textboxes
        bfoodText.clear();
        bwatText.clear();
        bmedText.clear();
    }

    /**
     * Handles when the ok button is clicked inside the sell
     * consumables section of the market
     * @param ActionEvent event
     */
    @FXML
    private void sellConHandle(ActionEvent event) {
        try {
            if (!sfoodText.getText().isEmpty()) {
                success = currentP().getMarket().buyItem("Food", cha, Integer.parseInt(sfoodText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!swatText.getText().isEmpty()) {
                success = currentP().getMarket().buyItem("Water", cha, Integer.parseInt(swatText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!smedText.getText().isEmpty()) {
                success = currentP().getMarket().buyItem("Medicine", cha, Integer.parseInt(smedText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
        } catch (NumberFormatException e) {
            dialog("Please enter a valid Integer.");
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        sfoodQuan.setText(upSellLab("Food"));
        swatQuan.setText(upSellLab("Water"));
        smedQuan.setText(upSellLab("Medicine"));
        bfoodQuan.setText(upBuyLab("Food"));
        bwatQuan.setText(upBuyLab("Water"));
        bmedQuan.setText(upBuyLab("Medicine"));
        //clear textboxes
        sfoodText.clear();
        swatText.clear();
        smedText.clear();
    }

    /**
     * Handles when the ok button is clicked inside the buy
     * equipment section of the market
     * @param ActionEvent event
     */
    @FXML
    private void buyEquiHandle(ActionEvent event) {
        if (!bFireTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Firearms", cha, Integer.parseInt(bFireTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bMacTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Machines", cha, Integer.parseInt(bMacTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bRobTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Robots", cha, Integer.parseInt(bRobTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        sFireQ.setText(upSellLab("Firearms"));
        sMacQ.setText(upSellLab("Machines"));
        sRobQ.setText(upSellLab("Robots"));
        bFireQ.setText(upBuyLab("Firearms"));
        bMacQ.setText(upBuyLab("Machines"));
        bRobQ.setText(upBuyLab("Robots"));
        //clear textboxes
        bFireTxt.clear();
        bMacTxt.clear();
        bRobTxt.clear();
    }

    /**
     * Handles when the ok button is clicked inside the sell
     * equipment section of the market
     * @param ActionEvent event
     */
    @FXML
    private void sellEquiHandle(ActionEvent event) {
        if (!sFireTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Firearms", cha, Integer.parseInt(sFireTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sMacTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Machines", cha, Integer.parseInt(sMacTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sRobTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Robots", cha, Integer.parseInt(sRobTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        sFireQ.setText(upSellLab("Firearms"));
        sMacQ.setText(upSellLab("Machines"));
        sRobQ.setText(upSellLab("Robots"));
        bFireQ.setText(upBuyLab("Firearms"));
        bMacQ.setText(upBuyLab("Machines"));
        bRobQ.setText(upBuyLab("Robots"));
        //clear textboxes
        sFireTxt.clear();
        sMacTxt.clear();
        sRobTxt.clear();
    }

    /**
     * Handles when the ok button is clicked inside the buy
     * misc. section of the market
     * @param ActionEvent event
     */
    @FXML
    private void buyMiscHandle(ActionEvent event) {
        if (!bFursTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Furs", cha, Integer.parseInt(bFursTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bOreTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Ore", cha, Integer.parseInt(bOreTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bGamTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Games", cha, Integer.parseInt(bGamTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        sFursQ.setText(upSellLab("Furs"));
        sOreQ.setText(upSellLab("Ore"));
        sGamQ.setText(upSellLab("Games"));
        bFursQ.setText(upBuyLab("Furs"));
        bOreQ.setText(upBuyLab("Ore"));
        bGamQ.setText(upBuyLab("Games"));
        //clear textboxes
        bFursTxt.clear();
        bOreTxt.clear();
        bGamTxt.clear();
    }

    /**
     * Handles when the ok button is clicked inside the sell
     * misc. section of the market
     * @param ActionEvent event
     */
    @FXML
    private void sellMiscHandle(ActionEvent event) {
        if (!sFursTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Furs", cha, Integer.parseInt(sFursTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sOreTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Ore", cha, Integer.parseInt(sOreTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sGamTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Games", cha, Integer.parseInt(sGamTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        sFursQ.setText(upSellLab("Furs"));
        sOreQ.setText(upSellLab("Ore"));
        sGamQ.setText(upSellLab("Games"));
        bFursQ.setText(upBuyLab("Furs"));
        bOreQ.setText(upBuyLab("Ore"));
        bGamQ.setText(upBuyLab("Games"));
        //clear textboxes
        sFursTxt.clear();
        sOreTxt.clear();
        sGamTxt.clear();
    }
    /**
     * Handles when the planet tab is selected
     * @param Event event
     */
    @FXML
    private void PlanetNameTabSelected(Event event) {
        planNameLabel.setText(currentP().getPlanetName());
        String text = currentP().toString();
        int index = text.indexOf("\n");
        planInfoLabel.setText(currentP().toString().substring(index + 1));
       
    }
    
    /**
     * Handles when the market tab is selected
     * @param Event event
     */
    @FXML
    private void marketTabSelected(Event event) {
        //sets initial coin amount from player inventory
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        
        //sets all labels for item price and quantity from market and player inventory
        int check = currentP().getMarket().getInventory().getItemPrice("Food");
        if (check != -1) {
            bfoodP.setText(Integer.toString(check));
            sfoodP.setText(Integer.toString(check));
            sfoodText.setVisible(true);
            bfoodText.setVisible(true);
        } else {
            bfoodP.setText("N/A");
            sfoodP.setText("N/A");
            sfoodText.setVisible(false);
            bfoodText.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Water");
        if (check != -1) {
            bWatP.setText(Integer.toString(check));
            swatP.setText(Integer.toString(check));
            swatText.setVisible(true);
            bwatText.setVisible(true);
        } else {
            bWatP.setText("N/A");
            swatP.setText("N/A");
            swatText.setVisible(false);
            bwatText.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Medicine");
        if (check != -1) {
            bmedP.setText(Integer.toString(check));
            smedP.setText(Integer.toString(check));
            smedText.setVisible(true);
            bmedText.setVisible(true);
        } else {
            bmedP.setText("N/A");
            smedP.setText("N/A");
            smedText.setVisible(false);
            bmedText.setVisible(false);
        } 
        check = currentP().getMarket().getInventory().getItemPrice("Firearms");
        if (check != -1) {
            bFireP.setText(Integer.toString(check));
            sFireP.setText(Integer.toString(check));
            sFireTxt.setVisible(true);
            bFireTxt.setVisible(true);
        } else {
            bFireP.setText("N/A");
            sFireP.setText("N/A");
            sFireTxt.setVisible(false);
            bFireTxt.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Machines");
        if (check != -1) {
            bMacP.setText(Integer.toString(check));
            sMacP.setText(Integer.toString(check));
            sMacTxt.setVisible(true);
            bMacTxt.setVisible(true);
        } else {
            bMacP.setText("N/A");
            sMacP.setText("N/A");
            sMacTxt.setVisible(false);
            bMacTxt.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Robots");
        if (check != -1) {
            bRobP.setText(Integer.toString(check));
            sRobP.setText(Integer.toString(check));
            sRobTxt.setVisible(true);
            bRobTxt.setVisible(true);
        } else {
            bRobP.setText("N/A");
            sRobP.setText("N/A");
            sRobTxt.setVisible(false);
            bRobTxt.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Furs");
        if (check != -1) {
            bFursP.setText(Integer.toString(check));
            sFursP.setText(Integer.toString(check));
            sFursTxt.setVisible(true);
            bFursTxt.setVisible(true);
        } else {
            bFursP.setText("N/A");
            sFursP.setText("N/A");
            sFursTxt.setVisible(false);
            bFursTxt.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Ore");
        if (check != -1) {
            bOreP.setText(Integer.toString(check));
            sOreP.setText(Integer.toString(check));
            sOreTxt.setVisible(true);
            bOreTxt.setVisible(true);
        } else {
            bOreP.setText("N/A");
            sOreP.setText("N/A");
            sOreTxt.setVisible(false);
            bOreTxt.setVisible(false);
        }
        check = currentP().getMarket().getInventory().getItemPrice("Games");
        if (check != -1) {
            bGamP.setText(Integer.toString(check));
            sGamP.setText(Integer.toString(check));
            sGamTxt.setVisible(true);
            bGamTxt.setVisible(true);
        } else {
            bGamP.setText("N/A");
            sGamP.setText("N/A");
            sGamTxt.setVisible(false);
            bGamTxt.setVisible(false);
        }
        //sets quantities of each item for market and player
        bfoodQuan.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Food")));
        sfoodQuan.setText(Integer.toString(cha.getInventory().getItemCount("Food")));
        bwatQuan.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Water")));
        swatQuan.setText(Integer.toString(cha.getInventory().getItemCount("Water")));
        bmedQuan.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Medicine")));
        smedQuan.setText(Integer.toString(cha.getInventory().getItemCount("Medicine")));
        bFireQ.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Firearms")));
        sFireQ.setText(Integer.toString(cha.getInventory().getItemCount("Firearms")));
        bMacQ.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Machines")));
        sMacQ.setText(Integer.toString(cha.getInventory().getItemCount("Machines")));
        bRobQ.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Robots")));
        sRobQ.setText(Integer.toString(cha.getInventory().getItemCount("Robots")));
        bFursQ.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Furs")));
        sFursQ.setText(Integer.toString(cha.getInventory().getItemCount("Furs")));
        bOreQ.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Ore")));
        sOreQ.setText(Integer.toString(cha.getInventory().getItemCount("Ore")));
        bGamQ.setText(Integer.toString(currentP().getMarket().getInventory().getItemCount("Games")));
        sGamQ.setText(Integer.toString(cha.getInventory().getItemCount("Games")));
        
    }

    /**
     * Handles when the universe tab is selected
     * @param Event event
     */
    @FXML
    private void universeTabSelected(Event event) {
        GraphicsContext gc = universeCanvas.getGraphicsContext2D();
        drawSolarSystem(gc);
    }
    /**
     * Helper method that draws the current Solar System on the canvas
     * @param GraphicsContext gc
     */
    private void drawSolarSystem(GraphicsContext gc) {
        gc.clearRect(0, 0, universeCanvas.getWidth(), universeCanvas.getHeight());
        Object[] ss = cha.getCurrentSolarSystem();
        currentSSLabelCanvas.setText(((SolarSystem)ss[0]).getName());
        List<Planet> planets = (List<Planet>)ss[2];
        planets.stream().forEach((p) -> {
            Image image = p.getPlanetPic();
            gc.drawImage(image, p.getLocation()[0]+ (universeCanvas.getWidth() - image.getWidth())/2, p.getLocation()[1]+ (universeCanvas.getHeight() - image.getHeight())/2);
        });
        //eventually draw fuel circle
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
                        Action response = Dialogs.create()
                        .title("Confirm Travel")
                        .masthead("Do you want to continue?")
                        .message("You have chosen to travel to " + p.getPlanetName() + " in the " + currentSSLabelCanvas.getText() + " System.")
                        .showConfirm();

                        if (response == Dialog.Actions.YES) {
                            // ... user chose YES
                            //check if enough fuel to get there
                            //bring up travel page - for events and decrement fuel
                            //set current planet and solarsystem
                            int dist = cha.setCurrentPlanet(p);
                            cha.getShip().subtractFuel(cha.getShip().getFuelLevel(), dist);
                            //sets current shown tab to the planet info tab
                            tabPane.getSelectionModel().select(0);
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
    
    @FXML
    private void uniButtonHandler(ActionEvent event) {
        GraphicsContext gc = universeCanvas.getGraphicsContext2D();
        drawUniverse(gc);
        //hide button
    }
    /**
     * Helper method that draws the all of the Solar Systems on the canvas
     * @param GraphicsContext gc
     */
    private void drawUniverse(GraphicsContext gc) {
        gc.clearRect(0, 0, universeCanvas.getWidth(), universeCanvas.getHeight());
        SolarSystem[] solar = uni.getAllSolarSystems();
        currentSSLabelCanvas.setText("Universe");
        for (SolarSystem solar1 : solar) {
            Image image = solar1.getSSPic();
            gc.drawImage(image, solar1.getLocation()[0] + (universeCanvas.getWidth() - image.getWidth())/2, solar1.getLocation()[1] + (universeCanvas.getHeight() - image.getHeight())/2);
        }
        
    }
    /**
     * Handles when the newspaper button is selected
     * inside the planet tab
     * @param ActionEvent event
     */
    @FXML
    private void newsButtHandle(ActionEvent event) {
    }
    
    /**
     * Helper method for buying items from market
     * @param String item
     * @return String for label update
     */
    private String upSellLab(String item) {
        return Integer.toString(cha.getInventory().getItemCount(item));
    }
    /**
     * Helper method for selling items to market
     * @param String item
     * @return String for label update
     */
    private String upBuyLab(String item) {
        return Integer.toString(currentP().getMarket().getInventory().getItemCount(item));
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

    
}
