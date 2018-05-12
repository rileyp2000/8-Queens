import java.util.ArrayList;

/**
 * This represents a placed Queen
 * 
 * @author rileyp
 *
 */
public class Queen {
	int r, c;

	public Queen(int row, int col) {
		r = row;
		c = col;
	}

	/**
	 * @return the r
	 */
	public int getR() {
		return r;
	}

	/**
	 * @param r
	 *            the r to set
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * @return the c
	 */
	public int getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(int c) {
		this.c = c;
	}

	/**
	 * Converts a list of Queens into a 2d array version of the chess board	
	 * @param placed the queens placed on the board
	 * @return A 2d array representation of the board
	 *
	 *
	 */
	public static int[][] toFullBoard(ArrayList<Queen> placed) {
		int[][] ret = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

		for (Queen q : placed) {
			ret[q.getR()][q.getC()] = 1;
		}

		return ret;
	}
	
	/**
	 * Converts a 2d array into a list of queen objects
	 * @param fullBoard the 2d array representation of the board
	 * @return An ArrayList of Queen objects representing the queens
	 *
	 *
	 */
	public static ArrayList<Queen> toQueenList(int[][] fullBoard) {
		ArrayList<Queen> ret = new ArrayList<Queen>();

		for (int r = 0; r < fullBoard.length; r++) {
			for (int c = 0; c < fullBoard[0].length; c++) {
				if (fullBoard[r][c] == 1) {
					Queen q = new Queen(r, c);
					ret.add(q);
				}
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Queen at: " + r + ", " + c;
	}




}
