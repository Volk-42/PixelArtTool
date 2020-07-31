package PixelArtTool;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PixelArtKeyboard implements KeyListener {
    private int cursorX;
    private int cursorY;
    private int[] cursorPos;
    private boolean[][] pixelGrid;
    private String spriteCoordinates;
    private boolean stateChange;
    private int[][] savedGrid;
    private File saveFile;
    private boolean loadSave;
    PixelArtKeyboard() {
        cursorX = 0;
        cursorY = 0;
        cursorPos = new int[2];
        pixelGrid = new boolean[272][152];
        spriteCoordinates = "{";
        stateChange = true;
        //savedGrid = PixelSaves.wake1; //deprecated
        //loads saved sprite coordinates from file
        saveFile = new File("engineExhaust1.csv");
        try {
            FileReader fr = new FileReader(saveFile);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            //debugger
            System.out.println(s);
            String[] coordinateArray = new String[s.length()/2];
            coordinateArray = s.split(",");
            int index = 0;
            savedGrid = new int[coordinateArray.length/2][2];
            //debugger
            System.out.println("spriteGrid Length " + savedGrid.length);
            for (int i = 0; i < savedGrid.length; i++) {
                savedGrid[i][0] = Integer.parseInt(coordinateArray[index]);
                savedGrid[i][1] = Integer.parseInt(coordinateArray[index+1]);
                index+=2;
            }
            //debugger
            System.out.println("spritegrid coordinates " + savedGrid[0][0]);
            System.out.println("spritegrid coordinates " + savedGrid[0][1]);
        }
        catch(FileNotFoundException e1) {
            System.out.println(e1);
        }
        catch(EOFException e2) {
            System.out.println(e2);
        }
        catch(IOException e3) {
            System.out.println(e3);
        }    
        loadSave = true; //set to true to load sprites
        if(loadSave) {
            for(int i = 0; i < savedGrid.length; i++) {
                int x = savedGrid[i][0];
                int y = savedGrid[i][1];
                pixelGrid[x][y] = true;
            }
        }
    }    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_RIGHT:
                if (cursorX < 272) {
                    cursorX++;
                    stateChange = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (cursorX > 0) {
                    cursorX--;
                    stateChange = true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(cursorY < 152) {
                    cursorY++;
                    stateChange = true;
                }
                break;
            case KeyEvent.VK_UP:
                if(cursorY > 0) {
                    cursorY--;
                    stateChange = true;
                }
                break;
            case KeyEvent.VK_SPACE:
                pixelGrid[cursorX][cursorY] = !pixelGrid[cursorX][cursorY];
                stateChange = true;
                break;
            case KeyEvent.VK_ENTER:
                for (int y = 0; y < pixelGrid.length; y++) {
                    for (int x = 0; x < pixelGrid[y].length; x++) {
                        if(pixelGrid[y][x]) {
                            spriteCoordinates += y;
                            spriteCoordinates += ",";
                            spriteCoordinates += x;
                            spriteCoordinates += ",";
                        }
                    }
                }
                try {
                    FileWriter fw = new FileWriter("coordinates.csv");
                    fw.write(spriteCoordinates);
                    fw.close();
                }
                catch(IOException exception) {
                    System.out.println(exception);
                }
                System.out.println(spriteCoordinates);
                break;
        }
        cursorPos[0] = cursorX;
        cursorPos[1] = cursorY;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    public int[] getCursor() {
        return cursorPos;
    }
    public boolean[][] getGrid() {
        return pixelGrid;
    }
    public boolean getState() {
        return stateChange;
    }
    public void setState(boolean myBool) {
        stateChange = myBool;
    }
   
}
