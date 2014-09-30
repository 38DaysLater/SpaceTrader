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
    private Label planNameLabel;
    @FXML
    private Label planInfoLabel;
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
    }
    
    @FXML
    private void marketTabSelected(Event event) {
        
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
        bfoodQuan.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Food")));
        bwatQuan.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Water")));
        bmedQuan.setText(Integer.toString(Singleton.getUniverse().getCurrentPlanet(0, 0).getMarket().getInventory().getItemCount("Medicine")));
        
        
    }

    @FXML
    private void universeTabSelected(Event event) {
    }

    @FXML
    private void newsButtHandle(ActionEvent event) {
    }
    
}
