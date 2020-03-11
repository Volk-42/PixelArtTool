import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
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
    private boolean loadSave;
    PixelArtKeyboard() {
        cursorX = 0;
        cursorY = 0;
        cursorPos = new int[2];
        pixelGrid = new boolean[272][152];
        spriteCoordinates = "{";
        stateChange = true;
        savedGrid = PixelSaves.building1;
        loadSave = false;
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
                            spriteCoordinates += "{";
                            spriteCoordinates += y;
                            spriteCoordinates += ",";
                            spriteCoordinates += x;
                            spriteCoordinates += "},";
                        }
                    }
                }
                spriteCoordinates += "}";
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
