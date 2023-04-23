
public class Bishop extends piece {

	public Bishop(String name, char x, int row, int col) {
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
		if(col == getColPos() && row == getRowPos()) {
			return false;
		}
		if(Math.abs(col- getColPos()) == Math.abs(row-getRowPos())) {
			for(int i = 1; i < Math.abs(row-getRowPos()); i++) {
				if(row > getRowPos()) {
					if(col > getColPos()) {
						if(!(board[getRowPos() + i][getColPos() + i] instanceof empty)) {
							return false;
						}
					}
					if(col < getColPos()) {
						if(!(board[getRowPos() + i][getColPos() - i] instanceof empty)) {
							return false;
						}
					}
				}
				if(row < getRowPos()) {
					if(col > getColPos()) {
						if(!(board[getRowPos() - i][getColPos() + i] instanceof empty)) {
							return false;
						}
					}
					if(col < getColPos()) {
						if(!(board[getRowPos() - i][getColPos() - i] instanceof empty)) {
							return false;
						}
					}
				}
			}
		}
		else {
			return false;
		}
		return true;
	}

}
