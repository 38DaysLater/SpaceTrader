/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    }

    @FXML
    private void cancelButtonHandel(ActionEvent event) {
    }

    @FXML
    private void nameHandler(ActionEvent event) {
    }
    
}
