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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pilotDone(DragEvent event) {
    }

    @FXML
    private void flightDone(DragEvent event) {
    }

    @FXML
    private void tradeDone(DragEvent event) {
    }

    @FXML
    private void engineerDone(DragEvent event) {
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
