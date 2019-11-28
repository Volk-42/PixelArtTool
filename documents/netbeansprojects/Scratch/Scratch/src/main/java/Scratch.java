import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Graphics;

public class Scratch {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeGUI();
            }
        });
    }
    public static void initializeGUI() {
        JFrame f = new JFrame();
        f.setSize(800, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b = new JButton("Next Step");
        MyPanel p = new MyPanel();
        p.add(b);
        f.add(p);
    }
}
class MyPanel extends JPanel {
    void myPanel () {
        System.out.println("test");
    }
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawRect(20, 20, 10, 10);
        
    }
}
