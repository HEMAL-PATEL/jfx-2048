
package com.controller;

import com.game.AboutApp;
import com.game.StartGame;
import com.view.TopScore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Game Menu controller
 * @author Saeed Masoumi
 */
public class MainMenuController implements Initializable{
    private Stage stage;
    @FXML private ImageView exit;
    @FXML private ImageView play;
    @FXML private ImageView aboutUs;
    @FXML private Rectangle rec;
    @FXML private Label gameName;
    @FXML private Label hsp;
    @FXML private Label hslb;
    private final TopScore topScore = TopScore.getInstance();
    public MainMenuController(Stage stage){
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameName.getStyleClass().add("game");
        hslb.getStyleClass().add("game");
        hsp.getStyleClass().add("game");
        hsp.setText(topScore.readScore()+"");
        //moved button Methods
        exit.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                exit.setEffect(new Glow(0.55));
            }
        });
        play.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                play.setEffect(new Glow(0.55));
            }
        });
        aboutUs.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                aboutUs.setEffect(new Glow(0.55));
            }
        });
        rec.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                exit.setEffect(new Glow(0));
                play.setEffect(new Glow(0));
                aboutUs.setEffect(new Glow(0));
            }
        });
        
        //pressed button method
        play.setOnMouseClicked((Event t) -> {
            new StartGame(stage).start();
        });
        exit.setOnMouseClicked((Event t) -> {
            stage.close();
        });
        aboutUs.setOnMouseClicked((Event t) -> {
            new AboutApp(stage).start();
        });
    }
    
}
