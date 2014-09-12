/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import impl.org.controlsfx.i18n.Localization;
import impl.org.controlsfx.i18n.Translations;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.action.Action;
import static org.controlsfx.dialog.Dialog.Actions.NO;
import static org.controlsfx.dialog.Dialog.Actions.YES;
import org.controlsfx.dialog.Dialogs;
//import org.controlsfx.dialog.Dialog.Actions;
//import org.controlsfx.dialog.Dialog;


/**
 * FXML Controller class
 *
 * @author Olivia
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane loadingPane;
    @FXML
    private ImageView imageView;
    @FXML
    private Label label;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button optionsButton;
    @FXML
    private FXMLLoader loader = new FXMLLoader();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void handleNewGameButtonAction(ActionEvent event) {
        //launches a simple dialouge box and collects user input. Just testing out
        //the controlsFX API
        
        //as far as I can tell we should be able to make the dialog box contents
        //in a seperate file, create it as a new content object, and launch it that way
        //api here: http://controlsfx.bitbucket.org/org/controlsfx/dialog/Dialog.html
        Action response = Dialogs.create()
                .title("New Game")
                .masthead("Confirm new game?")
                //.message( "")
                //.actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                .showConfirm();
        // Should direct to a new screen upon hitting "yes" but it doesn't work. Anyone have ideas?
        if (response == Dialog.Actions.YES) {
            URL url = getClass().getResource("CharacterCreation.fxml");
            loader.setLocation(url);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            try {
             loadingPane = (AnchorPane) loader.load(url.openStream());
             } catch (IOException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
        //loadingPane.getChildren().setAll(FXMLLoader.load(filename));
     
    }
    
        @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        Action response = Dialogs.create()
                .title("Load Game")
                .masthead("Load last saved game?")
                .showConfirm();
    }

    @FXML
    private void handleOptionButtonAction(ActionEvent event) {
        Action response = Dialogs.create()
                .title("Options")
                .showConfirm();
    }
    
}
