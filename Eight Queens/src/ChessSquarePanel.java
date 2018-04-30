import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author rileyp
 *
 */
public class ChessSquarePanel extends JPanel {
	
	private Color back;
	private boolean isQueen;
	private static final int FONTSIZE = 20;
	private static final Font f = new Font("Comic Sans MS", Font.PLAIN, FONTSIZE);
	
	
	public ChessSquarePanel(Color b, boolean is) {
		back = b;
		isQueen = is;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(f);
		this.setBackground(back);
		g.setColor(Color.BLACK);
		
		int x = (this.getWidth() / 2) - FONTSIZE/4; 
        int y = (this.getHeight() / 2) + FONTSIZE/4;
        if(isQueen);
        	g.drawString("Q", x, y);
			
	}
	
	public void setBack(Color c) {
		back = c;
	}
	
	public void setIsQueen(boolean q) {
		isQueen = q;
	}
	
}
