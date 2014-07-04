package com.view;

import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author Saeed Masoumi
 */
public class Tile extends Label{
    private long  value;
    private double tileSize = 100;
    private int pos_x;
    private int pos_y;
    public Tile(long value ){
        this.value = value;
        final int squareSize =100 - 10;
        setMinSize(squareSize, squareSize);
        setMaxSize(squareSize, squareSize);
        setPrefSize(squareSize, squareSize);
        setAlignment(Pos.CENTER);
        
        setText(value+"");
        
        getStyleClass().add("tile-"+value);
      }
    public long getValue(){
        return this.value;
    }
    public void setPosition(int x,int y){
        this.pos_x = x;
        this.pos_y = y;
    }
    public int getX(){
        return this.pos_x;
    }
    public int getY(){
        return this.pos_y;
    }
//    public static Tile newRandomTile() {
//        int value = new Random().nextDouble() < 0.9 ? 2 : 4;
//        return 
//    }
//    public static Tile newTile(int value) {
//        return new Tile(value);
//    }
}
