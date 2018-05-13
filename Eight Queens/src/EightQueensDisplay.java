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
	private static final Color PANEL_COLOR1 = Color.BLACK;
	private static final Color PANEL_COLOR2 = Color.WHITE;
	private static final int FONTSIZE = 20;
	private static final Font f = new Font("Comic Sans MS", Font.PLAIN, FONTSIZE);
	private static final ArrayList<Queen> PRESET_SOL = Queen
			.toQueenList(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
					{ 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 } });

	private JFrame window;
	private JPanel panelOne, panelTwo, panelThree;
	private ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS];

	private ArrayList<Queen> onBoard;
	private ArrayList<ArrayList<Queen>> allSolutions;

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

		window.setVisible(true);

		allSolutions = new ArrayList<ArrayList<Queen>>();

	}

	/**
	 * Creates and sets up the JFrame
	 */
	private void buildFrame() {
		window = new JFrame("8 Queens");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

	/**
	 * Creates the Header Jpanel, adds text to it, and returns the new JPanel
	 * 
	 * @param s
	 *            The String to put as the Header
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
	 * Determines if a number is even
	 * 
	 * @param x
	 *            the number to find if it is even
	 * @return if the number is even
	 */
	private boolean isEven(int x) {
		return x % 2 == 0;
	}

	/**
	 * updates the current board
	 */
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
	 * Builds the footer panel and adds buttons to it for finding and displaying
	 * solutions
	 * 
	 * @return The created footer panel
	 */
	private JPanel buildFooterPanel() {
		JPanel p = new JPanel();
		p.setMinimumSize(new Dimension(WIDTH, 10));
		p.setMaximumSize(new Dimension(WIDTH, 50));
		p.setPreferredSize(new Dimension(WIDTH, 40));
		p.setBackground(FOOTER_COLOR);

		JButton recur = new JButton("\nRecursively find solutions\n");
		recur.addActionListener(e -> {
			try {
				recurFind();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		p.add(recur);

		JButton dispRecur = new JButton("Display recursively found solutions");
		dispRecur.addActionListener(e -> {
			try {
				if (allSolutions.size() == 0)
					recurFind();
				displaySolve(allSolutions.get((int) (Math.random() * 91)));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		p.add(dispRecur);

		JButton reset = new JButton("\nReset board\n");
		reset.addActionListener(e -> reset());
		p.add(reset);

		JButton disp = new JButton("\nDisplay one solution");
		disp.addActionListener(e -> {
			try {
				displaySolve(PRESET_SOL);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
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
	 * @throws InterruptedException
	 *             for Thread.sleep
	 */
	public void updatePanel(int r, int c) throws InterruptedException {
		spaces[r][c].setIsQueen(!spaces[r][c].getIsQueen());
	}

	/**
	 * Resets the board to a blank board with no queens
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
	 * Determines if a Queen can legally be placed
	 * 
	 * @param x1
	 *            row of placement
	 * @param y1
	 *            column of placement
	 * @return if the placement is legal
	 */
	public boolean isLegal(int x1, int y1) {
		if (x1 > 7 || y1 > 7)
			return false;
		if (onBoard.isEmpty())
			return true;
		else {
			int y2 = 0;
			int x2 = 0;
			for (Queen q : onBoard) {
				y2 = q.getC();
				x2 = q.getR();
				if (x2 == x1 || y1 == y2)
					return false;
				else {
					if (x2 - x1 != 0) {

						if (Math.abs(y2 - y1) == Math.abs(x2 - x1))
							return false;
					} else
						return y2 - y1 == 0;
				}
			}
		}
		return true;
	}

	/**
	 * Displays the given solution on the board
	 * 
	 * @param solution
	 *            a Solution to the board
	 * @throws InterruptedException
	 *             for Thread.sleep
	 */
	public void displaySolve(ArrayList<Queen> solution) throws InterruptedException {
		reset();
		for (Queen q : solution)
			updatePanel(q.getR(), q.getC());
		currentBoard();

	}

	/**
	 * Method to call to start finding recursive solutions
	 * 
	 * @throws InterruptedException
	 *             for Thread.sleep
	 */
	public void recurFind() throws InterruptedException {
		reset();
		// updatePanel(0,0);
		// newRecur(0, 0);
		recursiveFind(0, 0);

	}

	/**
	 * Recursively finds all solutions to the 8 queens problem by going from
	 * left to right with all legal combinations
	 * 
	 * @param r
	 *            The row of the panel
	 * @param c
	 *            The column of the panel
	 * @throws InterruptedException
	 *             for Thread.sleep
	 */
	public void recursiveFind(int r, int c) throws InterruptedException {
		boolean placed = false;
		if (c < 8) {
			for (int ct = 0; ct <= 8; ct++) {
				// if there is already some queen on the current column, remove
				// it
				if (placed) {
					/* Queen q = */ onBoard.remove(onBoard.size() - 1);
					// System.out.println("Removed at: " + q);
					placed = false;

				}
				// if you can place it, place it and move to the next row
				if (isLegal(ct, c)) {
					onBoard.add(new Queen(ct, c));
					// System.out.println("Placed at: " + ct + ", " + c);
					placed = true;
					recursiveFind(ct, c + 1);
				}
			}
		} else {
			// if you get to the end and you dont have 8 queens, remove one and
			// go back
			if (onBoard.size() != 8) {
				Queen q = onBoard.remove(onBoard.size() - 1);
				// System.out.println("Removed at: " + q);
			} else {
				// add the solution to the solution set and keep going on
				ArrayList<Queen> q = new ArrayList<Queen>(onBoard);
				allSolutions.add(q);
				System.out.println("Found the " + allSolutions.size() + "th Solution!!!");

			}
		}
	}

	public static void main(String[] args) {

		EightQueensDisplay pg = new EightQueensDisplay();

	}

}
