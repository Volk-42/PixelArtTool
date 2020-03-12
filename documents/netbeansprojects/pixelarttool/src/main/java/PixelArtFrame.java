import javax.swing.JFrame;

public class PixelArtFrame implements Runnable {
    private PixelArtPanel p;
    private boolean running;
    private PixelArtKeyboard keyboard;
    
    PixelArtFrame() {
        JFrame f = new JFrame();
        f.setSize(605,405);
        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        keyboard = new PixelArtKeyboard();
        p = new PixelArtPanel(keyboard);
        p.loadKeyboard();
        f.add(p);
        f.setVisible(true);
        running = true;
    }
    
    @Override
    public void run() {
        while(running) {
            p.update();
            if(keyboard.getState()) {
                System.out.println("repainting");
                p.repaint();
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
