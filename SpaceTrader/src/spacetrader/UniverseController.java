/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class UniverseController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        planNameLabel.setText(Singleton.getUniverse().getCurrentPlanet(0, 0).getPlanetName());
        String text = Singleton.getUniverse().getCurrentPlanet(0, 0).toString();
        int index = text.indexOf("\n");
        planInfoLabel.setText(Singleton.getUniverse().getCurrentPlanet(0, 0).toString().substring(index + 1));
    }    
    
    @FXML
    private void buyConHandle(ActionEvent event) {
        
        
    }

    @FXML
    private void sellConHandle(ActionEvent event) {
    }

    @FXML
    private void buyEquiHandle(ActionEvent event) {
    }

    @FXML
    private void sellEquiHandle(ActionEvent event) {
    }

    @FXML
    private void buyMiscHandle(ActionEvent event) {
    }

    @FXML
    private void sellMiscHandle(ActionEvent event) {
    }
    
    @FXML
    private void PlanetNameTabSelected(Event event) {
        planNameLabel.setText(Singleton.getUniverse().getCurrentPlanet(0, 0).getPlanetName());
        String text = Singleton.getUniverse().getCurrentPlanet(0, 0).toString();
        int index = text.indexOf("\n");
        planInfoLabel.setText(Singleton.getUniverse().getCurrentPlanet(0, 0).toString().substring(index + 1));
    }
    
    @FXML
    private void marketTabSelected(Event event) {
        
        coinLabel.setText(Integer.toString(Singleton.getCharacter().getInventory().getBalance()));
        
        int check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Food");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Water");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Medicine");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Firearms");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Machines");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Robots");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Furs");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Ore");
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
        check = Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemPrice("Games");
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
        bfoodQuan.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Food")));
        sfoodQuan.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Food")));
        bwatQuan.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Water")));
        swatQuan.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Water")));
        bmedQuan.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Medicine")));
        smedQuan.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Medicine")));
        bFireQ.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Firearms")));
        sFireQ.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Firearms")));
        bMacQ.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Machines")));
        sMacQ.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Machines")));
        bRobQ.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Robots")));
        sRobQ.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Robots")));
        bFursQ.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Furs")));
        sFursQ.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Furs")));
        bOreQ.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Ore")));
        sOreQ.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Ore")));
        bGamQ.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Games")));
        sGamQ.setText(Integer.toString(Singleton.getCharacter().getInventory().getItemCount("Games")));
        
    }

    @FXML
    private void universeTabSelected(Event event) {
    }

    @FXML
    private void newsButtHandle(ActionEvent event) {
    }
    
}
