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

import com.controller.AboutAppController;
import com.controller.GameController;
import com.controller.MainMenuController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Saeed
 */
public class AboutApp {
    private final Stage stage;
    private final URL aboutUrl = asset.Asset.class.getResource("layout/aboutApp.fxml");
    private AboutAppController controller;
    public AboutApp(Stage stage){
        this.stage = stage;
    }
    public void start(){
        controller = new AboutAppController(this.stage);
        StackPane  root ;
        StackPane scorePane = new StackPane();
        StackPane tablePane = new StackPane();
        FXMLLoader fxmlLoader = new FXMLLoader(aboutUrl);
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
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
