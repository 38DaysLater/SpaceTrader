/*
 * The FXML Document Controller class goversn the opening game screen
 * It provides the user with the option to create a new game, load a game, and
 * bring up the options screen
 * @author Olivia
 */
package spacetrader;

import java.io.File;
import java.io.FileInputStream;
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
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Optional;
import org.controlsfx.dialog.Dialogs;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class FXMLDocumentController implements Initializable {
    //CHECKSTYLE: OFF
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
    //CHECKSTYLE: ON
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Handles when the new game button is clicked.
     * @param event
     */
    @FXML
    private void handleNewGameButtonAction(ActionEvent event) {

        
            File Filename2 = new File("DarkParade.wav");

            try{
                InputStream in2 = new FileInputStream(Filename2);
                // Create an AudioStream object from the input stream.
                AudioStream as2 = new AudioStream(in2);   
                // Use the static class member "player" from class AudioPlayer to play
                // clip.
                AudioPlayer.player.start(as2);            
                // Similarly, to stop the audio.
                //AudioPlayer.player.stop(as); 
            }
            catch (Exception exception) {
                System.out.println(exception);
            }
        
        try {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("CharacterCreation.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("New Commander");
            stage.setScene(scene);
            stage.show();

//        //hide this current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println("new button pushed");
        }
    }

    
    
    
    
    
    
    /**
     * Handles when the load game button is clicked.
     * @param event
     */
    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        Optional<String> response = Dialogs.create()
        .title("Load Game")
        .masthead("Please enter the name of the\n"
                + "character you would like to load.")
        .message("Character Name:")
        .showTextInput("Name");

        // One way to get the response value.
        if (response.isPresent()) {
            System.out.println("Your name: " + response.get());
            try {

                String fileName = response.get() + ".bin";
                ObjectInputStream is =
                        new ObjectInputStream(new FileInputStream(fileName));
                SaveObject so2 = (SaveObject) is.readObject();
                Singleton.setCharacter(so2.getCharacter());
                Singleton.setUniverse(so2.getUniverse());
                try {
                    Parent root = FXMLLoader.load(getClass()
                            .getResource("Universe.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Space Trader");
                    stage.setScene(scene);
                    stage.show();

        //        //hide current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    System.out.println("IOExcpetion caught in"
                            + "FXMLDocumentController.java line:106");
                }

                System.out.println();
                System.out.println("Name : " + so2.getCharacter().getName());
                System.out.println("Planet: " + ((Planet) so2.getCharacter()
                        .getCurrentPlanet()[0]).getPlanetName());
                is.close();

            } catch (Exception e) {
                        
        File Filename2 = new File("DarkParade.wav");

                try{
                    InputStream in2 = new FileInputStream(Filename2);
                    // Create an AudioStream object from the input stream.
                    AudioStream as2 = new AudioStream(in2);   
                    // Use the static class member "player" from class AudioPlayer to play
                    // clip.
                    AudioPlayer.player.start(as2);            
                    // Similarly, to stop the audio.
                    //AudioPlayer.player.stop(as); 
                }
                catch (Exception exception) {
                    System.out.println(exception);
                }

                Dialogs.create()
                .title("OH NO!")
                .masthead("Could not find save file")
                .message("Please be sure a valid character name is entered.")
                .showWarning();
//              System.out.println(e + "LOAD FAILED");

            }
        }
    }

    /**
     * Handles when the Option button is clicked.
     * @param event
     */
    @FXML
    private void handleOptionButtonAction(ActionEvent event) {
        
            File Filename = new File("TDFW.wav");

            try{
                InputStream in = new FileInputStream(Filename);
                // Create an AudioStream object from the input stream.
                AudioStream as = new AudioStream(in);   
                // Use the static class member "player" from class AudioPlayer to play
                // clip.
                AudioPlayer.player.start(as);            
                // Similarly, to stop the audio.
                //AudioPlayer.player.stop(as); 
            }
            catch (Exception exception) {
                System.out.println(exception);
            }
        
    }

}
