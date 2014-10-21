/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class MainTownController implements Initializable {
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView character;
    private Rectangle2D visualBounds;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image("spacetrader/resources/MainTown.png");
        visualBounds = Screen.getPrimary().getVisualBounds();
        backgroundImage.setImage(image);
        backgroundImage.setFitWidth(visualBounds.getWidth());
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);
        Image image2 = new Image("spacetrader/resources/stickman.png");
        character.setImage(image2);
        character.setX(visualBounds.getWidth()/2);
        character.setY(visualBounds.getHeight()/2);
    }    

    @FXML
    private void keyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            character.setY(character.getY() - 20);
        } else if (event.getCode() == KeyCode.DOWN) {
            character.setY(character.getY() + 20);
        } 
        if (event.getCode() == KeyCode.LEFT) {
            character.setX(character.getX() - 20);
        } else if (event.getCode() == KeyCode.RIGHT) {
            character.setX(character.getX() + 20);
        }
        String hit = isHit(character.getX(), character.getY());
        
        if (hit.equals("Market")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("MarketPlace.fxml"));

                Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
                Stage stage = new Stage();
//                stage.setFullScreen(true);
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.setScene(scene);
                stage.show();

    //        //hide current window
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                System.out.println("IOExcpetion caught in MainTownController.java line:82");
            }
        }
    }
    private String isHit(double x, double y) {
        if ((x < visualBounds.getWidth()* 0.1) && (y > visualBounds.getHeight() * 0.9)) {
            return "Market";
        }
        return null;
    }
}
