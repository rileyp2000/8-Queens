import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Terri Kelly, but modified by Patrick Riley
 *
 */
public class EightQueensDisplay {

	private static final int ROWS = 8;
	private static final int COLS = 8;
	private static final int HEIGHT = 90 * ROWS;
	private static final int WIDTH = 90 * COLS;
	private static final Color FOOTER_COLOR = Color.GRAY;
	private static final Color HEADER_COLOR = Color.GRAY;
	private static final int FONTSIZE = 20;
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final Font f = new Font("Comic Sans MS", Font.PLAIN, FONTSIZE);

	private JFrame window;
	private JPanel panelOne, panelTwo, panelThree;
	ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS]; 

	/**
	 * Constructs a new EightQueens Window without a provided solution
	 */
	EightQueensDisplay() {
		buildFrame();

		panelOne = buildHeaderPanel("Eight Queens Solution");
		panelTwo = buildGridPanels();
		panelThree = buildFooterPanel();

		window.add(panelOne);
		window.add(panelTwo);
		window.add(panelThree);

		// window.pack(); // Adjusts the frame size, so - collapses it ...
		window.setVisible(true);
	}

	/**
	 * Constructs an EightQueens window with a solution
	 * 
	 * @param aSolution
	 *            The solution to the Eight Queens problem provided by the user
	 */
	public EightQueensDisplay(int[][] aSolution) {
		buildFrame();

		panelOne = buildHeaderPanel("Eight Queens Solution");
		panelTwo = buildGridPanels(aSolution);
		panelThree = buildFooterPanel();

		window.add(panelOne);
		window.add(panelTwo);
		window.add(panelThree);

		// window.pack(); // Adjusts the frame size, so - collapses it ...
		window.setVisible(true);
	}
	
	
	/**
	 * Constructs an EightQueens window with a solution
	 * 
	 * @param aSolution
	 *            The solution to the Eight Queens problem provided by the user
	 */
	public EightQueensDisplay(ArrayList<Queen> sol) {
		buildFrame();
		int[][] b = Queen.toFullBoard(sol);
		
		panelOne = buildHeaderPanel("Eight Queens Solution");
		panelTwo = buildGridPanels(b);
		panelThree = buildFooterPanel();

		window.add(panelOne);
		window.add(panelTwo);
		window.add(panelThree);

		// window.pack(); // Adjusts the frame size, so - collapses it ...
		window.setVisible(true);
	}
	
	
	/**
	 * Creates and sets up the JFrame
	 */
	private void buildFrame() {
		window = new JFrame("Practicing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		// could set min, max, and preferred dimensions, I think
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

	/**
	 * Creates the Header Jpanel, adds text to it, and returns the new JPanel
	 * 
	 * @param s
	 *            the String to put as the Header
	 * @return the created JPanel
	 */
	private JPanel buildHeaderPanel(String s) {
		JPanel p = new JPanel();
		p.setMinimumSize(new Dimension(WIDTH, 10));
		p.setMaximumSize(new Dimension(WIDTH, 50));
		p.setPreferredSize(new Dimension(WIDTH, 40));
		p.setBackground(HEADER_COLOR);
		JLabel j = new JLabel(s);
		j.setFont(f);
		p.add(j);
		return p;
	}

	/**
	 * determines if a number is even
	 * 
	 * @param x
	 *            the number to find if it is even
	 * @return if the number is even
	 */
	private boolean isEven(int x) {
		return x % 2 == 0;
	}

	/**
	 * Sets the color of the panel according to a checkerboard
	 * 
	 * @param row
	 *            the row of the panel
	 * @param col
	 *            the column of the panel
	 * @return the Color chosen for the panel
	 */
	private Color setPanelColor(int row, int col) {
		// Come up with an algorithm that will provide alternate colors
		if (isEven(row + col))
			return Color.BLACK;
		else
			return Color.WHITE;
	}

	/**
	 * Creates a blank chess board
	 * 
	 * @return The blank chess board
	 */
	private JPanel buildGridPanels() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(ROWS, COLS));
		Color bg;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				bg = setPanelColor(r, c);
				ChessSquarePanel m = new ChessSquarePanel(bg, false);
				spaces[r][c] = m; // keep a reference to the panel, so we can
									// change it
				p.add(m);
			}
		}
		return p;
	}

	/**
	 * Builds the chess board with a given solution to display
	 * 
	 * @param sol
	 *            the solution represented as a 2D array
	 * @return The new chess board with the solution on it
	 */
	private JPanel buildGridPanels(int[][] sol) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(ROWS, COLS));
		Color bg;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				bg = setPanelColor(r, c);
				ChessSquarePanel m = null;
				if (sol[r][c] == 1)
					m = new ChessSquarePanel(bg, true);
				else
					m = new ChessSquarePanel(bg, false);
				spaces[r][c] = m; // keep a reference to the panel, so we can
									// change it
				p.add(m);
			}
		}
		return p;
	}

	/**
	 * Builds the footer panel and adds a button to it
	 * 
	 * @return The created footer panel
	 */
	private JPanel buildFooterPanel() {
		JPanel p = new JPanel();
		p.setMinimumSize(new Dimension(WIDTH, 10));
		p.setMaximumSize(new Dimension(WIDTH, 50));
		p.setPreferredSize(new Dimension(WIDTH, 40));
		p.setBackground(FOOTER_COLOR);

		JButton recur = new JButton("\nRecursively find all solutions\n");
		p.add(recur);
		JButton reset = new JButton("\nReset the board\n");
		p.add(reset);
		JButton disp = new JButton("\nDisplay one solution to the problem\n");
		p.add(disp);
		return p;
	}

	/**
	 * Updates the chosen panel to switch whether it displays a queen or not
	 * 
	 * @param r
	 *            the row of the panel
	 * @param c
	 *            the column of the panel
	 */
	public void updatePanel(int r, int c) {
		// Demonstrating one way to update the panels in the grid
		// grab the reference to the ChessSquarePanel - change the fields
		spaces[r][c].setIsQueen(!spaces[r][c].getIsQueen());
		//ChessSquarePanel p = spaces[r][c];
		
	}
	
	
	public void addQueens(ArrayList<Queen> placed) {
		
	}
	
	
	
	public static void main(String[] args) {

		int[][] aSolution = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 } };

		EightQueensDisplay pg = new EightQueensDisplay(aSolution);
		while (true) {
			System.out.println("Which Cell (r c): ");
			java.util.Scanner kb = new java.util.Scanner(System.in);
			int row = kb.nextInt();
			int col = kb.nextInt();
			pg.updatePanel(row, col);
		}

	}

}
