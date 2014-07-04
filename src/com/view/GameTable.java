
package com.view;

import static com.controller.GameController.CELL_SIZE;
import static com.controller.GameController.COL;
import static com.controller.GameController.ROW;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * GameTable is a singleton class for creating game table objects
 * @author Saeed Masoumi
 */
public class GameTable {
    
    private static GameTable instance = null;
    
    /* A private Constructor prevents any other
    * class from instantiating.
    */
    private GameTable(){
        
    }
    
    /* Static instance method */
    public static GameTable getInstance() {
        if(instance == null){
            
            instance = new GameTable();
        }
        
        return instance;
    }
    /**
     * Creating a Small Rectangle the same size as tiles
     * @return Give a Rectangle
     */
    public  Rectangle createGrid(){
        final double arcSize = CELL_SIZE/6d;
        Rectangle rect = new Rectangle(CELL_SIZE,CELL_SIZE);
        rect.setArcHeight(arcSize);
        rect.setArcWidth(arcSize);
        rect.getStyleClass().add("rec-cell");
        return rect;
        
    }
    
    /**
     * create a game table tile
     * @param i the value of the tile
     * @return  Label
     */
    public Label createTile(int i){
        Label a = new Label();
        final int squareSize =100 - 10;
        a.setMinSize(squareSize, squareSize);
        a.setMaxSize(squareSize, squareSize);
        a.setPrefSize(squareSize, squareSize);
        a.setAlignment(Pos.CENTER);
        
        a.setText(i+"");
        
        a.getStyleClass().add("tile-"+i);
        return a;
    }
    /**
     * Creating a Rectangle for TablePane
     * @param cellSize
     * @param cols
     * @param rows
     * @return Give a Rectangle
     */
    public  Rectangle createBackgorundRec(double cellSize,double cols,double rows){
        final double arcSize = cellSize/6d;
        Rectangle rect = new Rectangle((cols*cellSize)+10*cols,(rows*cellSize)+10*rows );
        rect.setArcHeight(arcSize);
        rect.setArcWidth(arcSize);
        rect.getStyleClass().add("rec-background");
        return rect;
    }
    /**
     * change the position of a tile Label
     * @param tile tile Label
     * @param posX (posX-1)th element of game table
     * @param posY (posY-1)th element of game table
     */
    public void setTilePos(Label tile,int posX,int posY){
        double top_left = -250+4.5; //translate label to the top right of the game table
        double X = posX*100 + posX*10;
        double Y = posY*100 + posY*10;
        tile.setTranslateX(X+top_left);
        tile.setTranslateY(Y+top_left);
    }
    /**
     * take the two tiles that created first then placing them randomly 
     * @param first first tile
     * @param second second tile
     * @param table stackPane of game board
     * @param max the maximum value for col and row (for creating random number between 0 and (max Col)) TODO:if COL!=ROW
     */
    public void tileFirstInitialize(Tile first,Tile second,StackPane table,int max){
        GameTable gameTable = GameTable.getInstance();
        //first
        Random rand = new Random();
        int X_1 = rand.nextInt((max - 0) + 1) + 0;
        int Y_1 =rand.nextInt((max - 0) + 1) + 0;
        gameTable.setTilePos(first, X_1, Y_1);
        first.setPosition(X_1, Y_1);
        //second         
        int X_2 = rand.nextInt((max - 0) + 1) + 0;
        int Y_2 =rand.nextInt((max - 0) + 1) + 0;
        while(X_2 == X_1 && Y_2==Y_1){
         X_2 = rand.nextInt((max - 0) + 1) + 0;
         Y_2 =rand.nextInt((max - 0) + 1) + 0;
        }
        gameTable.setTilePos(second, X_2, Y_2);
        second.setPosition(X_2, Y_2);
        table.getChildren().add(first);
        table.getChildren().add(second);

    }

   

}
