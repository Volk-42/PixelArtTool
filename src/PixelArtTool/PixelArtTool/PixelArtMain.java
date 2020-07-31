package PixelArtTool;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelArtMain {
    final private static PixelArtFrame PIXELART_FRAME = new PixelArtFrame();
    final private static Thread PIXELART_THREAD = new Thread(PIXELART_FRAME);
    public static void main(String[] args) {
        PIXELART_THREAD.start();
    }
}
