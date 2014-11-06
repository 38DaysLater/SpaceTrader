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

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class PlanetPageController implements Initializable {
    @FXML
    private Label planNameLabel;
    @FXML
    private Label planInfoLabel;
    @FXML
    private Button newsButt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planNameLabel.setText(currentP().getPlanetName());
        String text = currentP().toString();
        int index = text.indexOf("\n");
        planInfoLabel.setText(currentP().toString().substring(index + 1));
    }    

    @FXML
    private void newsButtHandle(ActionEvent event) {
    }

    /**
     * Helper method for getting the current planet
     * @param String item
     * @return Planet
     */
    private Planet currentP() {
        return ((Planet)Singleton.getCharacter().getCurrentPlanet()[0]);
    }
}
