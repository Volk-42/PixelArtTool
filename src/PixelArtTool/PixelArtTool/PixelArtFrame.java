package PixelArtTool;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class PixelArtFrame implements Runnable {
    private PixelArtPanel p1;
    private boolean running;
    private PixelArtKeyboard keyboard;
    
    PixelArtFrame() {
        JFrame f = new JFrame();
        f.setTitle("Pixel Art Tool");
        f.setSize(1360,760);
        f.setResizable(false);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        keyboard = new PixelArtKeyboard();
        p1 = new PixelArtPanel(keyboard);
        p1.loadKeyboard();
        JFileChooser fileChooser = new JFileChooser();
        JButton b1 = new JButton("load sprite");
        JPanel p2 = new JPanel();
        p2.add(b1);
        p2.add(fileChooser);
        f.add(p1, BorderLayout.CENTER);
        f.add(p2, BorderLayout.NORTH);
        f.setVisible(true);
        running = true;
    }
    
    @Override
    public void run() {
        while(running) {
            p1.update();
            if(keyboard.getState()) {
                System.out.println("repainting");
                p1.repaint();
                keyboard.setState(false);
            }
        }
        try {
            Thread.sleep(90);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
