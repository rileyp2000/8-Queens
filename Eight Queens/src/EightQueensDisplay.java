import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
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
	private static final Color FOOTER_COLOR = Color.MAGENTA;
	private static final Color HEADER_COLOR = Color.GRAY;
	private static final int FONTSIZE = 20;
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final Font f = new Font("Comic Sans MS", Font.PLAIN, FONTSIZE);

	private JFrame window;
	private JPanel panelOne, panelTwo, panelThree;
	ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS]; // In order
																	// to update
																	// the
																	// panels
																	// later
	// you must keep a reference to them

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

	private void buildFrame() {
		window = new JFrame("Practicing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		// could set min, max, and preferred dimensions, I think
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

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

	private JPanel buildGridPanels(int[][] sol) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(ROWS, COLS));
		Color bg;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				bg = setPanelColor(r, c);
				ChessSquarePanel m = null;
				if(sol[r][c] == 1)
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

	private JPanel buildFooterPanel() {
		JPanel p = new JPanel();
		p.setMinimumSize(new Dimension(WIDTH, 10));
		p.setMaximumSize(new Dimension(WIDTH, 50));
		p.setPreferredSize(new Dimension(WIDTH, 40));
		p.setBackground(FOOTER_COLOR);

		// TODO- add Buttons
		return p;
	}

	public void updatePanel(int r, int c) {
		// Demonstrating one way to update the panels in the grid
		// grab the reference to the ChessSquarePanel - change the fields
		ChessSquarePanel p = spaces[r][c];
		// window.repaint(); // because the set fields update the graphics, this
		// isn't needed
	}

	public static void main(String[] args) {

		int[][] aSolution = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 } };

		EightQueensDisplay pg = new EightQueensDisplay(aSolution);
		System.out.println("Which Cell (r c): ");
		java.util.Scanner kb = new java.util.Scanner(System.in);
		int row = kb.nextInt();
		int col = kb.nextInt();
		pg.updatePanel(row, col);

	}

}
