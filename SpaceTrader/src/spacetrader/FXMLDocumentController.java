/*
 * The FXML Document Controller class goversn the opening game screen
 * It provides the user with the option to create a new game, load a game, and 
 * bring up the options screen
 * @author Olivia
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
import java.util.Optional;
import org.controlsfx.dialog.Dialogs;

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
        Optional<String> response = Dialogs.create()
        .title("Load Game")
        .masthead("Please enther the name of the character you would like to load.")
        .message("Character Name:")
        .showTextInput("Name");

        // One way to get the response value.
        if (response.isPresent()) {
            System.out.println("Your name: " + response.get());
        }
    }

    /**
     * Handles when the Option button is clicked
     * @param ActionEvent event
     */
    @FXML
    private void handleOptionButtonAction(ActionEvent event) {
    }
    
}
