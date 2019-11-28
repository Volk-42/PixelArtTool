import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class NewMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeGUI();
            }
        });
    }
    public void initializeGUI() {
        JFrame f = new JFrame();
        MyPanel p = new MyPanel();
    }
}
class MyPanel extends JPanel {
    public static void myPanel() {
        System.out.println("test");
    }
}
    

