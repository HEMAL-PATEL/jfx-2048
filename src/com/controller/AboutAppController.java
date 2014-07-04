
package com.controller;

import com.game.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * controller for app info
 * @author Saeed Masoumi
 */
public class AboutAppController implements Initializable{
    @FXML ImageView back;
    private Stage stage;
    
    public AboutAppController(Stage stage) {
        this.stage = stage;
    
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         back.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                back.setEffect(new Glow(0.55));
                
            }
        });
         back.setOnMouseClicked(new EventHandler() {
            
            @Override
            public void handle(Event t) {
                try {
                    new Main().startAgain(stage);
                } catch (IOException ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    }
    
}
