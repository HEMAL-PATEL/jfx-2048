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

import com.controller.MainMenuController;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Saeed Masoumi
 */
public class Main extends Application {
    private StackPane root;
    private String icon = "file:src/asset/img/icon.png" ;
    private URL styleSheet = asset.Asset.class.getResource("css/style.css");
    
    private URL mainMenuUrl = asset.Asset.class.getResource("layout/mainMenu.fxml");
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Create stage with fxml file
        
        FXMLLoader fxmlLoader = new FXMLLoader(mainMenuUrl);
        fxmlLoader.setController(new MainMenuController(primaryStage));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(styleSheet.toString());
        
        primaryStage.getIcons().add(new Image(icon));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public void startAgain(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(mainMenuUrl);
        fxmlLoader.setController(new MainMenuController(stage));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(styleSheet.toString());
        
        stage.setScene(scene);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
