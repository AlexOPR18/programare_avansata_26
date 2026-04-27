package lab8;

import java.io.Serializable;

public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int row, col;
	boolean[] walls = {true, true, true, true};
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
