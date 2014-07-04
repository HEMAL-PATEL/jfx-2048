/*
Copyright (C) 2014 Saeed Masoumi

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

package com.game;

import com.controller.GameController;
import com.controller.MainMenuController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author Saeed Masoumi
 */
public class StartGame {
    private Stage stage;
    private Bounds gameBounds;
    private URL palyGameUrl = asset.Asset.class.getResource("layout/playGame.fxml");
    private URL styleSheet = asset.Asset.class.getResource("css/style.css");
    private GameController controller;
    
    public StartGame(Stage stage){
        this.stage = stage;
    }
    public void start(){
        StackPane  root ;
        StackPane scorePane = new StackPane();
        StackPane tablePane = new StackPane();
        controller = new GameController(stage,scorePane,tablePane);
        FXMLLoader fxmlLoader = new FXMLLoader(palyGameUrl);
        fxmlLoader.setController(controller);
        
        try {
            root = fxmlLoader.load();
            
            scorePane.setMaxHeight(100);
            scorePane.setMaxWidth(200);
            
            tablePane.setMaxHeight(600);
            tablePane.setMaxWidth(600);
            tablePane.setTranslateY(+20);
            tablePane.setTranslateX(+20);
            root.getChildren().add(scorePane);
            root.getChildren().add(tablePane);
          //  controller.renderGame();
            
            Scene scene = new Scene(root);
            keyListener(scene);
            scene.getStylesheets().add(styleSheet.toString());
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void keyListener(Scene scene) {
        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.S)) {
                System.out.println("save");
                return;
            }
            if (keyCode.equals(KeyCode.R)) {
                
                System.out.println("restore");
                return;
            }
            if (keyCode.isArrowKey() ) {
                controller.tileAI(keyCode.name());
              //  controller.show();
               return;
            }
            else return;
        });
        
    }
}


