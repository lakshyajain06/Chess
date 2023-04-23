
public class Pawn extends piece{

	public Pawn(String name, char x, int row, int col) {
		super(name, x, row, col);
	}
	public boolean canMove(int row, int col, piece[][] board) {
		if(isWhite()) {
			if(board[row][col] instanceof empty) {
				if(row-getRowPos() == 1 && col == getColPos()) {
					return true; 
				}
				else if(row-getRowPos() == 2 && getRowPos() == 1 && col == getColPos()) {
					return true; 
				}
			}
			else if(!(board[row][col] instanceof empty) && !board[row][col].isWhite()) {
				if(row-getRowPos() == 1 && Math.abs(col-getColPos()) == 1) {
					return true;
				}
			}
			else {
				return false;
			}
		}
		else if(!isWhite()) {
			if(board[row][col] instanceof empty) {
				if(getRowPos()-row == 1 && col == getColPos()) {
					return true; 
				}
				else if(getRowPos()-row == 2 && getRowPos() == 6 && col == getColPos()) {
					return true; 
				}
			}
			else if(!(board[row][col] instanceof empty) && board[row][col].isWhite()) {
				if(getRowPos()-row == 1 && Math.abs(col-getColPos()) == 1) {
					return true;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

}
