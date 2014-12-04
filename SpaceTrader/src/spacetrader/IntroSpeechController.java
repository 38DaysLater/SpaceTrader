/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class IntroSpeechController implements Initializable {

    @FXML
    private Label textLabel;

    /**
     * Initializes the controller class. Reads the text out from the label.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InputStream sound = null;
        try {
            Audio audio = Audio.getInstance();
            sound = audio.getAudio("Hello," + Singleton.getCharacter().getName()
                    + ", and welcome to Space Trader", Language.ENGLISH);
            audio.play(sound);
        } catch (IOException | JavaLayerException ex) {
            Logger.getLogger(IntroSpeechController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                sound.close();
            } catch (IOException ex) {
                Logger.getLogger(IntroSpeechController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
         System.out.println("mouse clicked");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Universe.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Space Trader");
            stage.setScene(scene);
            stage.show();

            //        //hide current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println("IOExcpetion caught in IntroSpeechController.java line:74");
        }
    }
}
