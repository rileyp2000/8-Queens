import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

 

/**
 * @author rileyp
 *
 */
public class ChessSquarePanel extends JPanel {
	
	private Color back;
	private boolean isQueen;
	private static final int FONTSIZE = 20;
	private static final Font f = new Font("Comic Sans MS", Font.PLAIN, FONTSIZE);
	
	/**
	 * Creates a square for the chess board
	 * @param b The background color of the panel
	 * @param is If the square should display a queen or not
	 */
	public ChessSquarePanel(Color b, boolean is) {
		back = b;
		isQueen = is;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(f);
		this.setBackground(back);
		if(back.equals(Color.BLACK))
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.BLACK);
		
		int x = (this.getWidth() / 2) - FONTSIZE/4; 
        int y = (this.getHeight() / 2) + FONTSIZE/4;
        if(isQueen)
        	g.drawString("Q", x, y);
			
	}
	
	/**
	 * Sets the background Color
	 * @param c The Color of the background
	 */
	public void setBack(Color c) {
		back = c;
		repaint();
	}
	
	/**
	 * Sets if the panel should display a queen or not
	 * @param q Whether the panel is a queen or not
	 */
	public void setIsQueen(boolean q) {
		isQueen = q;
		repaint();
	}
	
	/**
	 * Gets whether the panel will display a queen or not
	 * @return Whether the panel displays a queen or not
	 */
	public boolean getIsQueen() {
		return isQueen;
	}
	
	/**
	 * Gets the background color of the Panel
	 * @return the background color of the panel
	 */
	public Color getBack(){
		return back;
	}
}
