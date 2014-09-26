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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane loadingPane;
    @FXML
    private ImageView imageView;
    @FXML
    private Label label;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button optionsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    /**
     * Handles when the new game button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void handleNewGameButtonAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CharacterCreation.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("New Commander");
            stage.setScene(scene);
            stage.show();

//        //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }
    
    /**
     * Handles when the load game button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
    }

    /**
     * Handles when the Option button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void handleOptionButtonAction(ActionEvent event) {
    }
    
}
