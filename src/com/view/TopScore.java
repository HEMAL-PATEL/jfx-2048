
package com.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * save or read the value in score.txt for high score
 * @author Saeed Masoumi
 */
public class TopScore {
    private static TopScore instance = null;
    final static String FILE_NAME = "src/db/score.txt";
    private final File file = new File(FILE_NAME);
    
    private TopScore(){
        
    }
    public static TopScore getInstance(){
        if(instance == null){
            
            instance = new TopScore();
        }
        return instance;
    }
    public int readScore() {
        Scanner input;
        try {
            input = new Scanner(new FileInputStream(file));
            String getInput = input.nextLine();
            return Integer.parseInt(getInput);
    
        } catch (FileNotFoundException ex) {
        }
        return 0;
    }
    public void writeScore(String score) {
        try (FileWriter wr = new FileWriter(file)) {
            wr.write(score);
        } catch (IOException ex) {
        }
    }
}
