import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author rileyp
 *
 */
public class EightQueensDisplay {

	 private static final int ROWS = 8;
	   private static final int COLS = 8;   
	   private static final int HEIGHT = 90*ROWS;
	   private static final int WIDTH = 90*COLS;
	   private static final Color FOOTER_COLOR = Color.MAGENTA;
	   private static final Color HEADER_COLOR = Color.CYAN;
	   
	   private JFrame window;
	   private JPanel panelOne, panelTwo, panelThree;
	   ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS]; // In order to update the panels later
	                                                 // you must keep a reference to them

	   EightQueensDisplay() {
	      buildFrame();
	      
	      panelOne = buildHeaderPanel();
	      panelTwo = buildGridPanels();
	      panelThree = buildFooterPanel();
	   
	      window.add(panelOne);
	      window.add(panelTwo);
	      window.add(panelThree);
	   
	   //     window.pack();  // Adjusts the frame size, so - collapses it ...
	      window.setVisible(true);
	   }
	   
	   private void buildFrame() {
	      window = new JFrame("Practicing");
	      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      window.setSize(new Dimension(WIDTH, HEIGHT));
	      // could set min, max, and preferred dimensions, I think
	      window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	   } 
	   
	   private JPanel buildHeaderPanel() {
	      JPanel p = new JPanel();
	      p.setMinimumSize(new Dimension(WIDTH, 10));
	      p.setMaximumSize(new Dimension(WIDTH, 50));
	      p.setPreferredSize(new Dimension(WIDTH, 40));
	      p.setBackground(HEADER_COLOR);
	      p.add(new JLabel("A Checkerboard of Sorts"));
	      return p;
	   }
	      
	   private boolean isEven(int x) {
	      return x % 2 == 0;
	   }
	   
	   private Color setPanelColor(int row, int col) {
	           // Come up with an algorithm that will provide alternate colors
	           if (isEven(row + col))
	             return Color.BLACK;
	           else
	             return Color.WHITE;
	     }
	   
	   private JPanel buildGridPanels() {
	      JPanel p = new JPanel();
	      p.setLayout(new GridLayout(ROWS,COLS));
	      Color bg;
	      for (int r = 0; r < ROWS; r++) {
	         for (int c = 0; c < COLS; c++) {
	            bg = setPanelColor(r,c);           
	            ChessSquarePanel m = new ChessSquarePanel(bg, true);
	            spaces[r][c] = m;  // keep a reference to the panel, so we can change it
	            p.add(m);
	         }
	      }
	      return p;
	   }
	   
	      private JPanel buildFooterPanel() {
	      JPanel p = new JPanel();
	      p.setMinimumSize(new Dimension(WIDTH, 10));
	      p.setMaximumSize(new Dimension(WIDTH, 50));
	      p.setPreferredSize(new Dimension(WIDTH, 40));
	      p.setBackground(FOOTER_COLOR);
	      p.add(new JLabel("What will we put here"));
	      return p;
	   }
	   
	   public void updatePanel(int r, int c) {
	   // Demonstrating one way to update the panels in the grid
	   // grab the reference to the ChessSquarePanel - change the fields
	     ChessSquarePanel p = spaces[r][c];
	  //   window.repaint();  // because the set fields update the graphics, this isn't needed
	   }

	   public static void main(String[] args) {
	      EightQueensDisplay pg = new EightQueensDisplay();  
	      System.out.println("Which Cell (r c): ");
	      java.util.Scanner kb = new java.util.Scanner(System.in);
	      int row = kb.nextInt();
	      int col = kb.nextInt();
	      pg.updatePanel(row,col);
	      
	   }

	
}
