/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Sarah
 */

public class SpaceTrader extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(SpaceTrader.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
//            loader.setController(new FXMLDocumentController());
//            Pane mainPane = loader.load();
            StackPane page = (StackPane) FXMLLoader.load(SpaceTrader.class.getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SpaceTrader");
            primaryStage.show();
        } catch (Exception ex) {
            
        }
    }
}
