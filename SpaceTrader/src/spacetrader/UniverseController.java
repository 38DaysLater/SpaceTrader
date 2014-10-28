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
    private ToggleGroup shipGroup = new ToggleGroup();
    private ArrayList<Ship> shipList;
    private ShipYard shipYard;
    @FXML
    private Tab planetNameTab;
    @FXML
    private Label planNameLabel, planInfoLabel, coinLabel, coinLabel2;
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
    private Label fuelLabel;
    @FXML
    private Label planPosUni;
    @FXML
    private Button uniButton;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label saveLabel;
    @FXML
    private Label exitLabel;
    @FXML
    private Label loadLabel;
    @FXML
    private Label titan1, titan2, titan3;
    @FXML
    private Label banshee1, banshee2, banshee3;
    @FXML
    private Label rusty1, rusty2;
    @FXML
    private Label serenity1, serenity2;
    @FXML
    private Label shipyardHeader;
    @FXML
    private Label shipLabel;
    @FXML
    private RadioButton banshee2rad, banshee3rad, rusty1rad, rusty2rad;
    @FXML
    private RadioButton serenity1rad, serenity2rad, titan2rad, titan3rad;
    @FXML
    private RadioButton banshee1rad, titan1rad;
    
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
/****************************************************
 *                   MARKET TAB                     *
 ****************************************************/
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
     * Handles when the market tab is selected
     * @param Event event
     */
    @FXML
    private void marketTabSelected(Event event) {
        //sets initial coin amount from player inventory
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        
        //sets all labels for item quantity from market and player inventory
        //if quantity for planet it -1 then the planet can't buy or sell that item
        String bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Food"));
        String bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Food"));
        String sQuan = Integer.toString(cha.getInventory().getItemCount("Food"));
        if (bQuan.equals("-1")) {
            bfoodP.setText("N/A");
            sfoodP.setText("N/A");
            bfoodQuan.setText("N/A");
            sfoodQuan.setText(sQuan);
            sfoodText.setVisible(false);
            bfoodText.setVisible(false);
        } else {
            bfoodP.setText(bPrice);
            sfoodP.setText(bPrice);
            bfoodQuan.setText(bQuan);
            sfoodQuan.setText(sQuan);
            sfoodText.setVisible(true);
            bfoodText.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Water"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Water"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Water"));
        if (bQuan.equals("-1")) {
            bWatP.setText("N/A");
            swatP.setText("N/A");
            bwatQuan.setText("N/A");
            swatQuan.setText(sQuan);
            swatText.setVisible(false);
            bwatText.setVisible(false);
        } else {
            bWatP.setText(bPrice);
            swatP.setText(bPrice);
            bwatQuan.setText(bQuan);
            swatQuan.setText(sQuan);
            swatText.setVisible(true);
            bwatText.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Medicine"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Medicine"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Medicine"));
        if (bQuan.equals("-1")) {
            bmedP.setText("N/A");
            smedP.setText("N/A");
            bmedQuan.setText("N/A");
            smedQuan.setText(sQuan);
            smedText.setVisible(false);
            bmedText.setVisible(false);
        } else {
            bmedP.setText(bPrice);
            smedP.setText(bPrice);
            bmedQuan.setText(bQuan);
            smedQuan.setText(sQuan);
            smedText.setVisible(true);
            bmedText.setVisible(true);
        } 
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Firearms"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Firearms"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Firearms"));
        if (bQuan.equals("-1")) {
            bFireP.setText("N/A");
            sFireP.setText("N/A");
            bFireQ.setText("N/A");
            sFireQ.setText(sQuan);
            sFireTxt.setVisible(false);
            bFireTxt.setVisible(false);
        } else {
            bFireP.setText(bPrice);
            sFireP.setText(bPrice);
            bFireQ.setText(bQuan);
            sFireQ.setText(sQuan);
            sFireTxt.setVisible(true);
            bFireTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Machines"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Machines"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Machines"));
        if (bQuan.equals("-1")) {
            bMacP.setText("N/A");
            sMacP.setText("N/A");
            bMacQ.setText("N/A");
            sMacQ.setText(sQuan);
            sMacTxt.setVisible(false);
            bMacTxt.setVisible(false);
        } else {
            bMacP.setText(bPrice);
            sMacP.setText(bPrice);
            bMacQ.setText(bQuan);
            sMacQ.setText(sQuan);
            sMacTxt.setVisible(true);
            bMacTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Robots"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Robots"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Robots"));
        if (bQuan.equals("-1")) {
            bRobP.setText("N/A");
            sRobP.setText("N/A");
            bRobQ.setText("N/A");
            sRobQ.setText(sQuan);
            sRobTxt.setVisible(false);
            bRobTxt.setVisible(false);
        } else {
            bRobP.setText(bPrice);
            sRobP.setText(bPrice);
            bRobQ.setText(bQuan);
            sRobQ.setText(sQuan);
            sRobTxt.setVisible(true);
            bRobTxt.setVisible(true);
        }        
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Furs"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Furs"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Furs"));
        if (bQuan.equals("-1")) {
            bFursP.setText("N/A");
            sFursP.setText("N/A");
            bFursQ.setText("N/A");
            sFursQ.setText(sQuan);
            sFursTxt.setVisible(false);
            bFursTxt.setVisible(false);
        } else {
            bFursP.setText(bPrice);
            sFursP.setText(bPrice);
            bFursQ.setText(bQuan);
            sFursQ.setText(sQuan);
            sFursTxt.setVisible(true);
            bFursTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Ore"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Ore"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Ore"));
        if (bQuan.equals("-1")) {
            bOreP.setText("N/A");
            sOreP.setText("N/A");
            bOreQ.setText("N/A");
            sOreQ.setText(sQuan);
            sOreTxt.setVisible(false);
            bOreTxt.setVisible(false);
        } else {
            bOreP.setText(bPrice);
            sOreP.setText(bPrice);
            bOreQ.setText(bQuan);
            sOreQ.setText(sQuan);
            sOreTxt.setVisible(true);
            bOreTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory().getItemCount("Games"));
        bPrice = Integer.toString(currentP().getMarket().getInventory().getItemPrice("Games"));
        sQuan = Integer.toString(cha.getInventory().getItemCount("Games"));
        if (bQuan.equals("-1")) {
            bGamP.setText("N/A");
            sGamP.setText("N/A");
            bGamQ.setText("N/A");
            sGamQ.setText(sQuan);
            sGamTxt.setVisible(false);
            bGamTxt.setVisible(false);
        } else {
            bGamP.setText(bPrice);
            sGamP.setText(bPrice);
            bGamQ.setText(bQuan);
            sGamQ.setText(sQuan);
            sGamTxt.setVisible(true);
            bGamTxt.setVisible(true);
        }
        
    }
/****************************************************
 *                   MARKET END                     *
 ****************************************************/
/****************************************************
 *                   SHIPYARD TAB                    *
 ****************************************************/    
    /**
     * Handles when the shipyard tab is selected
     * @param Event event
     */
    @FXML
    private void shipyardTabSelected(Event event) {
        shipList = Ships.getList();
        shipYard = currentP().getShipYard();
        coinLabel2.setText(Integer.toString(cha.getInventory().getBalance()));
        /*String shipString;
        or (int i = 0; i < shipList.size(); i++) {
            System.out.println(i);
            //shipLabel.setText(shipList.get(i).toString() + "\n");
            shipString = shipList.get(i) + "\n";
            shipLabel.setText(shipString);
        }*/
        shipyardHeader.setText("Name\t\tPrice\tMLP\tWeight\tAttack\tSpeed\tFuel Capacity\tHealth");
        titan1.setText(shipList.get(0).getName() + "\t\t" + shipList.get(0).toString());
        titan2.setText(shipList.get(1).getName() + "\t\t" + shipList.get(1).toString());
        titan3.setText(shipList.get(2).getName() + "\t\t" + shipList.get(2).toString());
        
        banshee1.setText(shipList.get(3).getName() + "\t" + shipList.get(3).toString());
        banshee2.setText(shipList.get(4).getName() + "\t" + shipList.get(4).toString());
        banshee3.setText(shipList.get(5).getName() + "\t" + shipList.get(5).toString());
        
        rusty1.setText(shipList.get(6).getName() + "\t\t" + shipList.get(6).toString());
        rusty2.setText(shipList.get(7).getName() + "\t\t" + shipList.get(7).toString());
        
        serenity1.setText(shipList.get(8).getName() + "\t" + shipList.get(8).toString());
        serenity2.setText(shipList.get(9).getName() + "\t" + shipList.get(9).toString());
        
        
        shipLabel.setText(Singleton.getCharacter().getShip().getName() + "\t" + Singleton.getCharacter().getShip().toString());
        
        titan1rad.setToggleGroup(shipGroup);
        titan2rad.setToggleGroup(shipGroup);
        titan3rad.setToggleGroup(shipGroup);
        banshee1rad.setToggleGroup(shipGroup);
        banshee2rad.setToggleGroup(shipGroup);
        banshee3rad.setToggleGroup(shipGroup);
        rusty1rad.setToggleGroup(shipGroup);
        rusty2rad.setToggleGroup(shipGroup);
        serenity1rad.setToggleGroup(shipGroup);
        serenity2rad.setToggleGroup(shipGroup);
        
    }
    
    @FXML
    private void refuelButtonClicked(ActionEvent event) {
        
    }

    @FXML
    private void repairButtonClicked(ActionEvent event) {
    }

    @FXML
    private void upgradeButtonClicked(ActionEvent event) {
        Toggle selected =  shipGroup.getSelectedToggle();
        //selected.equals(id of button in question)
        //do things
        if (selected.equals(titan1rad)) {
            shipYard.buyShip(shipList.get(0));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(titan2rad)) {
            shipYard.buyShip(shipList.get(1));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(titan3rad)) {
            shipYard.buyShip(shipList.get(2));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(banshee1rad)) {
            shipYard.buyShip(shipList.get(3));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(banshee2rad)) {
            shipYard.buyShip(shipList.get(4));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(banshee3rad)) {
            shipYard.buyShip(shipList.get(5));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(rusty1rad)) {
            shipYard.buyShip(shipList.get(6));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(rusty2rad)) {
            shipYard.buyShip(shipList.get(7));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(serenity1rad)) {
            shipYard.buyShip(shipList.get(8));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        } else if (selected.equals(serenity2rad)) {
            shipYard.buyShip(shipList.get(9));
            coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
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
                            //check if enough fuel to get there
                            /**
                             * bring up travel page - for events and decrement fuel
************                 * EVVEEEENNNTTTSSSS
                             */
                            
                            /*creates a travel event object to handle random events
                            * eventually it will read from a text document so the
                            * events will have different chances based on where we are in the universe
                            *and be different each time the player travels.
                            * but for now it is very simple.
                            */
                            TravelEvent events = new TravelEvent();
                            events.handleEvents();
                            
                            //set current planet and solarsystem
                            String thing = cha.getShip().checkSufficientFuel(dist);
                            if(thing == null) {
                                //player has enoughfuel to rech destination
                                cha.getShip().subtractFuel(cha.getShip().getFuelLevel(), dist);
                                cha.setCurrentPlanet(p);
                                //sets current shown tab to the planet info tab                                
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
    /**
     * Handles when the newspaper button is selected
     * inside the planet tab
     * @param ActionEvent event
     */
    @FXML
    private void newsButtHandle(ActionEvent event) {
    }
    
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
     * Helper method for buying items from market
     * @param String item
     * @return String for label update
     */
    private String upSellLab(String item) {
        String up = Integer.toString(cha.getInventory().getItemCount(item));
        if (up.equals("-1")) {
            return "N/A";
        }
        return up;
    }
    /**
     * Helper method for selling items to market
     * @param String item
     * @return String for label update
     */
    private String upBuyLab(String item) {
        String up = Integer.toString(currentP().getMarket().getInventory().getItemCount(item));
        if(up.equals("-1")) {
            return "N/A";
        }
        return up;
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
     * 
     * @param event 
     */
    @FXML
    private void labelExited(MouseEvent event) {
        ((Node)event.getSource()).setStyle("-fx-background-color: transparent;");

    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void labelEntered(MouseEvent event) {
        ((Node)event.getSource()).setStyle("-fx-background-color: gray;");
    }

    /**
     * 
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
        
        System.out.println("Save Successful");
 
        
        
    }

    /**
     * 
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
     * 
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
                
                System.out.println();
                System.out.println("Name : " + so2.getCharacter().getName());
                System.out.println("Planet: " + ((Planet) so2.getCharacter().getCurrentPlanet()[0]).getPlanetName());
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