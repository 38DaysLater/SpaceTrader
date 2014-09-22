/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
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
        // TODO
        pilotSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != oldValue.intValue()) {
                value += newValue.intValue() - oldValue.intValue();
                if (value > sp) {
                    //dialog box maybe                    
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
                    //dialog box maybe        
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
                    //dialog box maybe        
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
                    //dialog box maybe
                    spNumLabel.setText(Integer.toString(sp - value));
                } else {
                    enginVal = newValue.intValue();
                    spNumLabel.setText(Integer.toString(sp - value));
                }
            }
        });
    }    

    @FXML
    private void okButtonHandler(ActionEvent event) {
        Character ch = new Character(nameBox.getText(), pilotVal, fightVal, tradeVal, enginVal);
        
//        Stage stage = (Stage) okBut.getScene().getWindow();
//        // do what you have to do
//        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Universe.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Space Trader");
            stage.setScene(scene);
            stage.show();

//        //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    @FXML
    private void cancelButtonHandel(ActionEvent event) {
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

    @FXML
    private void nameHandler(ActionEvent event) {
        
    }
    
}
