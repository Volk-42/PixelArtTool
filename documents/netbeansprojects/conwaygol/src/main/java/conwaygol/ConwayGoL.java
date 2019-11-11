
package connectfour;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.*;
//adding in some text to save for git

public class ConnectFour {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable () {
            public void run() {
                initializeGUI();
            }
        });
    }
    public static void initializeGUI() {
        JFrame f = new JFrame();
        MyPanel p = new MyPanel();
        p.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed");
                p.repaint();
            }
        });
        p.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                System.out.println("dragged");
            }
        });
        f.setSize(900, 600);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}


