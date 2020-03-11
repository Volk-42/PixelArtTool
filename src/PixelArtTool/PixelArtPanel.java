import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class PixelArtPanel extends JPanel{
    private boolean pixelGrid[][];
    private int[] cursor;
    private int cursorX;
    private int cursorY;
    private PixelArtKeyboard keyboard;
    PixelArtPanel(PixelArtKeyboard keyboard) {
        pixelGrid = new boolean[272][152];
        cursor = new int[2];
        cursorX = 0;
        cursorY = 0;
        this.keyboard = keyboard;
    }
    public void loadKeyboard() {
        System.out.println("keyboard added");
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    public void update() {
        //System.out.println("panel updated");
        cursor = keyboard.getCursor();
        cursorX = cursor[0];
        cursorY = cursor[1];
        pixelGrid = keyboard.getGrid();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        for (int x = 0; x < pixelGrid.length; x++) {
            for (int y = 0; y < pixelGrid[x].length; y++) {
                if (pixelGrid[x][y]) {
                    g.fillRect(x*5, y*5, 5, 5);
                }
                else {
                    g.drawRect(x*5, y*5, 5, 5);
                }
            }
        }
        g.setColor(Color.yellow);
        g.fillRect(cursorX*5, cursorY*5, 5, 5);
    }
}
