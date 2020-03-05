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
        pixelGrid = new boolean[29][18];
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
                    g.fillRect(x*20+5, y*20+5, 20, 20);
                }
                else {
                    g.drawRect(x*20+5, y*20+5, 20, 20);
                }
            }
        }
        g.setColor(Color.yellow);
        g.fillRect(cursorX*20+7, cursorY*20+7, 15, 15);
    }
}
