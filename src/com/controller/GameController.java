
package com.controller;

import com.game.Main;
import com.view.GameTable;
import com.view.Tile;
import com.view.TopScore;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Saeed Masoumi
 */
public class GameController implements Initializable{
    private final double SCORE_BOX_X = 220;
    private final double SCORE_BOX_Y = -600;
    private final double POINT_ANIM_X = -150;
    private final double POINT_ANIM_Y = -30;
    private final double TABLE_X = 0;
    private final double TABLE_Y = -50;
    private Stage stage;
    @FXML private ImageView reload;
    @FXML private ImageView back;
    @FXML private ImageView exit;
    @FXML private Rectangle rec;
    @FXML private StackPane mainStackPane;
    private final Label lbScore = new Label("0");
    private final Label lbXp = new Label("xp");
    private final Label lbLose = new Label("Lose");
    private final Label lbPoints = new Label();
    private final StackPane scorePane;
    private final StackPane tablePane;
    public static final double CELL_SIZE = 100;
    public static final int COL = 4;
    public static final int ROW = 4;
    private final VBox recBox =new VBox(0);
    private HBox rectangles_HBox ;
    private Tile firstInitTile ;
    private Tile secondInitTile;
    private final ArrayList<Tile> tileList = new ArrayList<>();
    private final long[][] tileSimulator = new long[COL][ROW];
    private final GameTable gameTable = GameTable.getInstance();
    private final TopScore topScore = TopScore.getInstance();

    /**
     * draw Score , Points , xp ,gameTable with their StackPanes
     * @param stage Game Stage
     * @param scoreBoard a stackPane for showing score and point
     * @param tableBoard a stackPane for showing game table
     */
    
    public GameController(Stage stage ,StackPane scoreBoard ,StackPane tableBoard){
        this.stage = stage;
        
        /*set scorePane and translate it to the top-right*/
        this.scorePane = scoreBoard;
        this.scorePane.setTranslateY(SCORE_BOX_Y);
        this.scorePane.setTranslateX(SCORE_BOX_X);
        
        /*create scorePane properties (xp / point / score)*/
        lbScore.getStyleClass().add("points");
        lbPoints.getStyleClass().add("pointsAdded");
        lbXp.getStyleClass().add("xp");
        lbLose.getStyleClass().add("lose");;                
        lbLose.setVisible(false);
        /*change position for probable conflict*/
        lbScore.setMaxSize(250, 100);
        lbXp.setTranslateX(30);
        lbPoints.setTranslateX(POINT_ANIM_X);
        lbPoints.setTranslateY(POINT_ANIM_Y);
        
        /*add properties to scorePane*/
        this.scorePane.getChildren().add(lbScore);
        this.scorePane.getChildren().add(lbXp);
        this.scorePane.getChildren().add(lbPoints);
        
        /*set tablePane and translate it to the center*/
        this.tablePane = tableBoard;
        this.tablePane.setTranslateX(TABLE_X);
        this.tablePane.setTranslateY(TABLE_Y);
        for(int i=0;i<ROW;i++){
            rectangles_HBox = new HBox(0);
            for(int j=0;j<COL;j++)
                rectangles_HBox.getChildren().add(gameTable.createGrid());
            recBox.getChildren().add(rectangles_HBox);
        }
        this.tablePane.getChildren().add(new VBox(gameTable.createBackgorundRec(CELL_SIZE,COL,ROW)));
        this.tablePane.getChildren().add(recBox);
        /**/
        int num = Math.random() < 0.9 ? 2 : 4;
        firstInitTile = new Tile(num);
        num = Math.random() < 0.9 ? 2 : 4;
        secondInitTile = new Tile(num);
        gameTable.tileFirstInitialize(firstInitTile, secondInitTile, this.tablePane,COL-1);
        init();
        //  gameTable.init(tileSimulator,tileList);
    }
    /**
     *  javafx controller for Fxml file
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //if moved cruser to :
        rec.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                reload.setEffect(new Glow(0));
                back.setEffect(new Glow(0));
                exit.setEffect(new Glow(0));
            }
        });
        reload.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                reload.setEffect(new Glow(0.55));
                
            }
        });
        exit.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                exit.setEffect(new Glow(0.55));
                
            }
        });
        back.setOnMouseMoved(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                back.setEffect(new Glow(0.55));
                
            }
        });
        //if mouse pressed on :
        exit.setOnMouseClicked(new EventHandler() {
            
            @Override
            public void handle(Event t) {
                stage.close();
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
        reload.setOnMouseClicked(new EventHandler() {
            
            @Override
            public void handle(Event t) {
                for(Tile a:tileList){
                    a.setVisible(false);
                }
                 tablePane.setOpacity(1);
                 lbLose.setVisible(false);
                firstInitTile.setVisible(false);
                secondInitTile.setVisible(false);
                int num = Math.random() < 0.9 ? 2 : 4;
                firstInitTile = new Tile(num);
                num = Math.random() < 0.9 ? 2 : 4;
                secondInitTile = new Tile(num);
                gameTable.tileFirstInitialize(firstInitTile, secondInitTile, tablePane,COL-1);
                init();
//
            }
        });
    }
    /**
     * show a 500 milliseconds animation of current point and add it to score
     * @param v1 the value of current points
     * @return PathTransition
     */
    private PathTransition animateScore(String v1) {
        lbPoints.setText("+"+v1);
        lbPoints.setOpacity(1);
        
        final Path path = new Path();
        path.getElements().add(new MoveTo(-100,-10));
        path.getElements().add(new LineTo(-50, 40));
        path.setOpacity(0.5);
        
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(600));
        pathTransition.setPath(path);
        pathTransition.setNode(lbPoints);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                lbPoints.setOpacity(0);
//                lbScore.setText((Integer.parseInt(lbScore.getText())+Integer.parseInt(v1))+"");
            }
        });
        lbPoints.setOpacity(0);
        lbScore.setText((Integer.parseInt(lbScore.getText())+Integer.parseInt(v1))+"");
        FadeTransition ft = new FadeTransition(Duration.millis(500), lbPoints);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
        
        return pathTransition;
        
    }
    
    public void renderGame() {
        
        
    }
 
    /**
     * initialize GameBoard for the first time
     */
    private void init(){
        lbScore.setText("0");
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++)
                tileSimulator[i][j] = 0;
        }
        tileSimulator[firstInitTile.getY()][firstInitTile.getX()] = firstInitTile.getValue();
        tileSimulator[secondInitTile.getY()][secondInitTile.getX()] = secondInitTile.getValue();
        tileList.add(firstInitTile);
        tileList.add(secondInitTile);
    }
    /**
     * for debugging 
     */
    public void show(){
        
        for(int i=0;i<COL;i++){
            for(int j=0;j<ROW;j++)
                System.out.print(tileSimulator[i][j]+" ");
            System.out.println("");
        }
    }
    /**
     * game base logic
     * @param name keyCode for arrow keys
     */
    public void tileAI(String name) {
        boolean move = false;
        long score = 0;
        if(name.equals("UP")){
            for(int i=0;i<ROW;i++){
                int x = 0;
                while(x<COL-1){
                    if(tileSimulator[x][i]!=0){
                        for(int j=x+1;j<=ROW-1;j++){
                            if(tileSimulator[j][i]==0)
                                continue;
                            if(tileSimulator[j][i]!=tileSimulator[x][i])
                                break;
                            if(tileSimulator[x][i]==tileSimulator[j][i]){
                                //TODO:add score
                                tileSimulator[x][i]*=2;
                                tileSimulator[j][i]=0;
                                move = true;
                                score+=tileSimulator[x][i];
                                x=j;
                                break;
                            }
                        }
                    }
                    x++;
                }
            }
            if(score!=0)
                animateScore(score+"").play();
            //sort
            for(int i=0;i<COL;i++){
                for(int j=0;j<ROW;j++){
                    if(tileSimulator[j][i]==0){
                        if(j!=ROW-1){
                            for(int z=j+1;z<ROW;z++){
                                if(tileSimulator[z][i]!=0){
                                    tileSimulator[j][i] = tileSimulator[z][i];
                                    tileSimulator[z][i] = 0;
                                    move =true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            //end sort
            if(move)
                moving();
        }
        else if(name.equals("DOWN")){
            for(int i=0;i<ROW;i++){
                int x = COL-1;
                while(x>=1){
                    if(tileSimulator[x][i]!=0){
                        for(int j=x-1;j>=0;j--){
                            if(tileSimulator[j][i]==0)
                                continue;
                            if(tileSimulator[j][i]!=tileSimulator[x][i])
                                break;
                            if(tileSimulator[x][i]==tileSimulator[j][i]){
                                //TODO:add score
                                tileSimulator[x][i]*=2;
                                tileSimulator[j][i]=0;
                                score+=tileSimulator[x][i];
                                x=j;
                                move = true ;
                                break;
                            }
                        }
                    }
                    x--;
                }
            }
            
            if(score!=0)
                animateScore(score+"").play();
            //sort
            for(int i=0;i<COL;i++){
                for(int j=ROW-1;j>=0;j--){
                    if(tileSimulator[j][i]==0){
                        if(j!=0){
                            for(int z=j-1;z>=0;z--){
                                if(tileSimulator[z][i]!=0){
                                    tileSimulator[j][i] = tileSimulator[z][i];
                                    tileSimulator[z][i] = 0;
                                    move = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            //end sotr
            if(move)
                moving();
        }
        else if(name.equals("RIGHT")){
            for(int i=0;i<ROW;i++){
                int x = COL-1;
                while(x>=1){
                    if(tileSimulator[i][x]!=0){
                        for(int j=x-1;j>=0;j--){
                            if(tileSimulator[i][j]==0)
                                continue;
                            if(tileSimulator[i][j]!=tileSimulator[i][x])
                                break;
                            if(tileSimulator[i][x]==tileSimulator[i][j]){
                                //TODO:add score
                                tileSimulator[i][x]*=2;
                                tileSimulator[i][j]=0;
                                score+=tileSimulator[i][x];
                                move = true ;
                                x=j;
                                break;
                            }
                        }
                    }
                    x--;
                }
            }
            
            if(score!=0)
                animateScore(score+"").play();
            //sort
            for(int i=0;i<COL;i++){
                for(int j=ROW-1;j>=0;j--){
                    if(tileSimulator[i][j]==0){
                        if(j!=0){
                            for(int z=j-1;z>=0;z--){
                                if(tileSimulator[i][z]!=0){
                                    tileSimulator[i][j] = tileSimulator[i][z];
                                    tileSimulator[i][z] = 0;
                                    move = true ;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            //end sort
            if(move)
                moving();
        }
        else if(name.equals("LEFT")){
            for(int i=0;i<ROW;i++){
                int x = 0;
                while(x<COL-1){
                    if(tileSimulator[i][x]!=0){
                        for(int j=x+1;j<=COL-1;j++){
                            if(tileSimulator[i][j]==0)
                                continue;
                            if(tileSimulator[i][j]!=tileSimulator[i][x])
                                break;
                            if(tileSimulator[i][x]==tileSimulator[i][j]){
                                //TODO:add score
                                tileSimulator[i][x]*=2;
                                tileSimulator[i][j]=0;
                                score+=tileSimulator[i][x];
                                x=j;
                                move = true ;
                                break;
                            }
                        }
                    }
                    x++;
                }
            }
            
            if(score!=0)
                animateScore(score+"").play();
            //sort
            for(int i=0;i<COL;i++){
                for(int j=0;j<ROW;j++){
                    if(tileSimulator[i][j]==0){
                        if(j!=ROW-1){
                            for(int z=j+1;z<ROW;z++){
                                if(tileSimulator[i][z]!=0){
                                    tileSimulator[i][j] = tileSimulator[i][z];
                                    tileSimulator[i][z] = 0;
                                    move = true ;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            //end sort
            if(move)
                moving();
        }
        if(Integer.parseInt(lbScore.getText()) > topScore.readScore()){
            topScore.writeScore(lbScore.getText());
        }
        if(tileList.size()==16){
            if(checkLosing()){
                 tablePane.setOpacity(0.5);
                lbLose.setVisible(true);
                if(!mainStackPane.getChildren().contains(lbLose)){
                    lbLose.setTranslateX(-50);
                    lbLose.setTranslateY(-250);
                    lbLose.setTextAlignment(TextAlignment.CENTER);
                    lbLose.setText("Game Over"+"\n"+"Your Score: "+lbScore.getText());
                    mainStackPane.getChildren().add(lbLose);
                }
            }
        }

    }
    /**
     * move tiles
     */
    private void moving(){
        
        Random rand = new Random();
        int X_1 = rand.nextInt((3 - 0) + 1) + 0;
        int Y_1 =rand.nextInt((3 - 0) + 1) + 0;
        while(tileSimulator[X_1][Y_1]!=0){
            X_1 = rand.nextInt((3 - 0) + 1) + 0;
            Y_1 =rand.nextInt((3 - 0) + 1) + 0;
        }
        int num = Math.random() < 0.9 ? 2 : 4;
        tileSimulator[X_1][Y_1] = num;
        show();
        for(Tile a :tileList){
            a.setVisible(false);
            
        }
        tileList.clear();
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                if(tileSimulator[i][j]!=0){
                    Tile a = new Tile(tileSimulator[i][j]);
                    a.setPosition(j, i);
                    tileList.add(a);
                }
            }
            
        }
        for(Tile a:tileList){
            gameTable.setTilePos(a,a.getX(), a.getY());
            tablePane.getChildren().add(a);
        }
        
    }
    /**
     * check end of the game
     * @return return true if gameOver
     */
    private boolean checkLosing() {
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                if(j==COL-1  && i!=ROW-1){
                    if(tileSimulator[j][i]==tileSimulator[j][i+1])
                        return false;
                }
                 else if(i==ROW-1 && j!=COL-1){
                     if(tileSimulator[j][i] == tileSimulator[j+1][i])
                         return false;
                }
                 else if( i!=ROW-1 && j!=COL-1){
                     
                    if(tileSimulator[j][i] == tileSimulator[j+1][i]
                            || tileSimulator[j][i] == tileSimulator[j][i+1])
                        return false;
                     }
            }
        }
        
        return true;
    }
    
}
