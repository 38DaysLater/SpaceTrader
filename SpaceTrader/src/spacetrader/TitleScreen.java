package spacetrader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author Sarah
 */
public class TitleScreen extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button newB = new Button();
        Button load = new Button();
        Button options = new Button();
        newB.setText("New Game");
        load.setText("Load Game");
        options.setText("Options");
        
        newB.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                CharacterCreationScreen ccs = new CharacterCreationScreen();
                ccs.start(primaryStage);
            }
        });
        
        FlowPane root = new FlowPane();
        root.getChildren().add(newB);
        root.getChildren().add(load);
        root.getChildren().add(options);
        
        Scene scene = new Scene(root, 500, 300);
        
        primaryStage.setTitle("SpaceTrader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
