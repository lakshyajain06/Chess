
public class Rook extends piece {
	private boolean hasMoved = false;
	
	public Rook(String name, char x, int row, int col) {
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
		
		if(row-getRowPos() == 0) {
			for(int i = 1; i < Math.abs(col-getColPos()); i++) {
				if(col > getColPos()) {
					if(!(board[row][getColPos()+i] instanceof empty)) {
						return false;
					}
				}
				if(col < getColPos()) {
					if(!(board[row][getColPos()-i] instanceof empty)) {
						return false;
					}
				}
			}
		}
		else if(col-getColPos() == 0) {
			for(int i = 1; i < Math.abs(row-getRowPos()); i++) {
				if(row > getRowPos()) {
					if(!(board[getRowPos()+i][col] instanceof empty)) {
						return false;
					}
				}
				if(row < getRowPos()) {
					if(!(board[getRowPos()-i][col] instanceof empty)) {
						return false;
					}
				}
			}
		}
		else {
			return  false;
		}
		return true;
	}
	public void move(int row, int col, piece[][] board) {
		if(canMove(row, col, board)) {
			setPos(row, col);
			hasMoved = true;
		}
	}
	public boolean getHasMoved() {
		return hasMoved;
	}
}
