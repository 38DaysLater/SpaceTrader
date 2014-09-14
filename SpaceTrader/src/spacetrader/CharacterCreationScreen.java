/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 *
 * @author Sarah
 */
public class CharacterCreationScreen extends Application {
    
    /**
     *  tTODO: Input validation, easy number inputing
     * better looking panel design. This is just a quick and dirty way
     * to have something ready for our demo.
     * 
     */
    
    @Override
    public void start(Stage primaryStage) {
        Button ok = new Button();
        Button cancel = new Button();
        ok.setText("Confirm");
        cancel.setText("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TitleScreen ts = new TitleScreen();
                ts.start(primaryStage);
            }
        });
        
        Label name = new Label("Name:");
        TextField nameField = new TextField ();
        
        Label pilot = new Label("Pilot:");
        TextField pilotField = new TextField ();
        
        Label engineer = new Label("Engineer:");
        TextField engineerField = new TextField ();
        
        Label fight = new Label("Fight:");
        TextField fightField = new TextField ();
        
        Label trade = new Label("Trade:");
        TextField tradeField = new TextField ();
        
        ok.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Character player = new Character();
                player.setName(nameField.getText());
                player.setPilot(Integer.parseInt(pilotField.getText()));
                player.setEngineer(Integer.parseInt(engineerField.getText()));
                player.setFight(Integer.parseInt(fightField.getText()));
                player.setTrade(Integer.parseInt(tradeField.getText()));
                System.out.println(player);
            }
        });
        

        FlowPane root = new FlowPane();
        root.getChildren().addAll(name, nameField, pilot, pilotField, engineer, engineerField, fight, fightField, trade, tradeField, ok, cancel);
        
        
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
