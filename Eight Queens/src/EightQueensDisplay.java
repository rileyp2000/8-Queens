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
	private static final Color PANEL_COLOR1 = Color.BLUE;
	private static final Color PANEL_COLOR2 = Color.YELLOW;
	private static final int FONTSIZE = 20;
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final Font f = new Font("Comic Sans MS", Font.PLAIN, FONTSIZE);
	private static final ArrayList<Queen> SOL = Queen
			.toQueenList(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
					{ 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 } });

	private JFrame window;
	private JPanel panelOne, panelTwo, panelThree;
	private ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS];
	private ArrayList<Queen> onBoard;

	/**
	 * Constructs a new EightQueens Window without a provided solution
	 */
	EightQueensDisplay() {
		
		buildFrame();

		panelOne = buildHeaderPanel("Eight Queens Solution");
		panelTwo = buildGridPanels();
		panelThree = buildFooterPanel();

		currentBoard();

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

	private void currentBoard() {
		ArrayList<Queen> ret = new ArrayList<Queen>();

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (spaces[r][c].getIsQueen())
					ret.add(new Queen(r, c));
			}
		}
		onBoard = ret;
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
			return PANEL_COLOR1;
		else
			return PANEL_COLOR2;
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
		recur.addActionListener(e -> {
			try {
				recurFind();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		p.add(recur);
		JButton reset = new JButton("\nReset the board\n");
		reset.addActionListener(e -> reset());
		p.add(reset);
		JButton disp = new JButton("\nDisplay one solution to the problem\n");
		disp.addActionListener(e -> presetSolution());
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
		// ChessSquarePanel p = spaces[r][c];
		currentBoard();
	}

	/**
	 * Resets the board to a blank board
	 */
	public void reset() {
		for (int r = 0; r < spaces.length; r++) {
			for (int c = 0; c < spaces[0].length; c++) {
				spaces[r][c].setIsQueen(false);
			}
		}
		currentBoard();
	}

	/**
	 * Wipes the board and displays the given solution
	 */
	public void presetSolution() {
		reset();
		for (Queen q : SOL) {
			updatePanel(q.getR(), q.getC());
		}
		currentBoard();
	}

	public void recurFind() throws InterruptedException {
		reset();
		recurQueens(0, 0);

	}

	public void recurQueens(int r, int c) throws InterruptedException {
		System.out.println("Recursive at: " + r + " , " + c);
		if (onBoard.size() == 8)
			displaySolve();
		else {

			if (c == 7) {
				System.out.println("No Solutions");
				return;
			} else {
				int ct = 0;
				while (ct < 8) {
					int row = (r + ct) % 8;
					if (isLegal(row, c)) {
						updatePanel(row, c);
						recurQueens(row, c + 1);
					} else {
						ct++;
						recurQueens(row + 1, c);
					}
				}
			}
		}

	}

	public boolean isLegal(int x1, int y1) {
		if (onBoard.isEmpty())
			return false;
		else {
			int y2 = 0;
			int x2 = 0;
			for (Queen q : onBoard) {
				y2 = q.getC();
				x2 = q.getR();
				if (x2 == x1)
					return false;
				else {
					if (Math.abs((y2 - y1) / (x2 - x1)) == 1)
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * Displays a win scene if the board is solved
	 * 
	 * @throws InterruptedException
	 */
	public void displaySolve() throws InterruptedException {
		for (int r = 0; r < spaces.length; r++) {
			for (int c = 0; c < spaces[0].length; c++) {
				if (spaces[r][c].getBack().equals(PANEL_COLOR1))
					spaces[r][c].setBack(Color.GREEN);
			}
		}

		Thread.sleep(5000);

		for (int r = 0; r < spaces.length; r++) {
			for (int c = 0; c < spaces[0].length; c++) {
				if (spaces[r][c].getBack().equals(Color.GREEN))
					spaces[r][c].setBack(PANEL_COLOR1);
			}
		}
	}

	public static void main(String[] args) {

		EightQueensDisplay pg = new EightQueensDisplay();
		/*
		 * while (true) { System.out.println("Which Cell (r c): ");
		 * java.util.Scanner kb = new java.util.Scanner(System.in); int row =
		 * kb.nextInt(); int col = kb.nextInt(); pg.updatePanel(row, col); }
		 */

	}

}
