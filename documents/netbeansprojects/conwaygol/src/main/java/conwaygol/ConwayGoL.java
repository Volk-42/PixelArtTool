package conwaygol;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class ConwayGOL {
    
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        
        //create frame and components
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.setSize(762, 762);
        GridPanel g = new GridPanel();
        g.initialConditions("Acorn");
        /* above parameter will accept any of these:
        Acorn
        Glider
        Block
        Large Block
        Blinker
        */
        JButton b = new JButton("Advance One Generation");
        JButton b2 = new JButton("Start");
        JButton b3 = new JButton("Stop");
        Timer t = new Timer();
        //button 1 ticks forward one generation
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {    
                g.tickForward();
                g.repaint();
            }
        }); 
        //button 2 starts automated evolution
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {    
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        g.tickForward();
                        g.repaint();
                    }
                }, 0, 100);
            }
        }); 
        //button 3 stops automated evolution
        //no way to start it again currently
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                t.cancel();
                t.purge();
            }
        });
        //add all compents to the frame and make it visible
        g.add(b);
        g.add(b2);
        g.add(b3);
        f.add(g);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }
}

class GridPanel extends JPanel {
    
    //creates boolean 2D array
    static int rows =75;
    static int columns = 75;
    static boolean[][] grid1 = new boolean[rows][columns];
    static boolean[][] grid2 = new boolean[rows][columns];
    
    //treats array elements as grid cells. True = live, false = dead
    //live cells get filled in. Dead cells only outline painted
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        for (int rowInd = 0; rowInd < rows; rowInd++) {
            for (int colInd = 0; colInd < columns; colInd++) {
                int xPos = rowInd*10;
                int yPos = colInd*10;
                if (grid1[rowInd][colInd] == true) {
                    g.fillRect(xPos, yPos, 10, 10);
                }
                else if(grid1[rowInd][colInd] == false) {
                    g.drawRect(xPos, yPos, 10, 10);
                }
            }
        } 
    }
    
    //code for initial conditions set above
    //can add new ones as needed
    public void initialConditions (String initialCond) {
        
        //ACORN
        if (initialCond == "Acorn") {   
            grid1[10][30] = true;
            grid1[10][31] = true;
            grid1[8][31] = true;
            grid1[9][33] = true;
            grid1[10][34] = true;
            grid1[10][35] = true;
            grid1[10][36] = true;
        }
        //LARGE BLOCK
        if (initialCond == "Large Block") {
            grid1[10][30] = true;
            grid1[10][31] = true;
            grid1[10][32] = true;
            grid1[11][30] = true;
            grid1[11][31] = true;
            grid1[11][32] = true;
            grid1[12][30] = true;
            grid1[12][31] = true;
            grid1[12][32] = true;
        }
        //BLOCK
        if (initialCond == "Block") {
            grid1[10][30] = true;
            grid1[10][31] = true;
            grid1[11][30] = true;
            grid1[11][31] = true;
        }
        //BLINKER
        if (initialCond == "Blinker") {
            grid1[10][30] = true;
            grid1[10][31] = true;
            grid1[10][32] = true;
        }  
        //GLIDER
        if (initialCond == "Glider") {
            grid1[20][60] = true;
            grid1[21][61] = true;
            grid1[22][59] = true;
            grid1[22][60] = true;
            grid1[22][61] = true;
        }
    } 
    
    //calling this method advances a generation
    public void tickForward () {
        for (int rowInd = 0; rowInd < rows; rowInd++) {
            for (int colInd = 0; colInd < columns; colInd++) {
                //checks if adjacent cells fall out of bounds
                //and transfers reference to opposite side
                //i.e. non periodic boundaries
                int rowNum = grid1.length - 1;
                int colNum = grid1[0].length - 1;
                int rowMinus = rowInd > 0 ? -1 : rowNum;
                int rowPlus = rowInd < rowNum ? 1 : -rowNum;
                int colMinus = colInd > 0 ? -1 : colNum;
                int colPlus = colInd < colNum ? 1 : -colNum;
                boolean top = grid1[rowInd + rowPlus][colInd];
                boolean topR = grid1[rowInd + rowPlus][colInd + colPlus];
                boolean right = grid1[rowInd][colInd + colPlus];
                boolean botR = grid1[rowInd + rowMinus][colInd + colPlus];
                boolean bot = grid1[rowInd + rowMinus][colInd];
                boolean botL = grid1[rowInd + rowMinus][colInd + colMinus];
                boolean left = grid1[rowInd][colInd + colMinus];
                boolean topL = grid1[rowInd + rowPlus][colInd + colMinus];
  
                //counts how many cells surrounding current cell are live
                int liveCount = 0;
                boolean[] countArray = new boolean[8];
                countArray[0] = top;
                countArray[1] = topR;
                countArray[2] = right;
                countArray[3] = botR;
                countArray[4] = bot;
                countArray[5] = botL;
                countArray[6] = left;
                countArray[7] = topL;
                for (int countInd = 0; countInd < 8; countInd++)
                    {
                    if (countArray[countInd] == true)
                        liveCount += 1;
                    }
                
                //sets grid2 values according to Conway's Game of Life rules
                //can be changed to experiment with other cellular automata
                if (liveCount < 2)
                    grid2[rowInd][colInd] = false;
                else if (grid1[rowInd][colInd] == true && (liveCount == 2 || liveCount == 3))
                    grid2[rowInd][colInd] = true;
                else if (liveCount > 3)
                    grid2[rowInd][colInd] = false;
                else if (grid1[rowInd][colInd] == false && liveCount == 3)
                    grid2[rowInd][colInd] = true;
            }
        }
        //overwrites grid 1 with grid 2 values 
        for (int rowInd2 = 0; rowInd2 < rows; rowInd2++) {
            for (int colInd2 = 0; colInd2 < columns; colInd2++) {
                grid1[rowInd2][colInd2] = grid2[rowInd2][colInd2]; 
            }
        }
    }
}

