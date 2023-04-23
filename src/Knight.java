
public class Knight extends piece {

	public Knight(String name, char x, int row, int col) {
		super(name, x, row, col);
	}
	public boolean canMove(int row, int col, piece[][] board) {
		if(this.isWhite()) {
			if(!(board[row][col] instanceof empty) && board[row][col].isWhite()) {
				return false;
			}	
		}
		else if(!this.isWhite()) {
			if(!(board[row][col] instanceof empty) && !board[row][col].isWhite()) {
				return false;
			}	
		}
		if(Math.abs(col-getColPos()) == 1 && Math.abs(row-getRowPos()) == 2) {
			return true;
		}
		else if(Math.abs(col-getColPos()) == 2 && Math.abs(row-getRowPos()) == 1) {
			return true;
		}
		return false;
	}

}
