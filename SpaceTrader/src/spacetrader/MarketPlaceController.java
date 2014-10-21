/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class MarketPlaceController implements Initializable {
    @FXML
    private ImageView backgroundImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image("spacetrader/resources/Market.JPG");
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        backgroundImage.setImage(image);
        backgroundImage.setFitWidth(visualBounds.getWidth());
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);
    }    

    
}
