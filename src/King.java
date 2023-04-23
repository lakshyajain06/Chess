
public class King extends piece {
	private boolean hasMoved = false;

	public King(String name, char x, int row, int col) {
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
		if((Math.abs(row-getRowPos()) == 1 || row-getRowPos() == 0) && (Math.abs(col-getColPos()) == 1 || col-getColPos() == 0)){
			return true;
		}
		return false;
	
	}
	public boolean canMove(int row, int col, piece[][] board, Player enemy) {
		if(row >= 0 && row < 8 && col >= 0 && col < 8) {
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
			if((Math.abs(row-getRowPos()) == 1 || row-getRowPos() == 0) && (Math.abs(col-getColPos()) == 1 || col-getColPos() == 0)){
				return isNotCheck(row, col, board, enemy);
			}
		}
		return false;
		
	}
	public boolean isNotCheck(piece[][] board, Player enemy) {
		if(howManyPiecesCheck(board, enemy) > 0) {
			return false;
		}
		return true;
	}
	public boolean isNotCheck(int row, int col, piece[][] board, Player enemy) {
		if(howManyPiecesCheck(row, col, board, enemy) > 0) {
			return false;
		}
		return true;
	}
	public int howManyPiecesCheck(piece[][] board, Player enemy) {
		int counter = 0;
		for(int i = 0; i < 16; i++) {
			if(enemy.getPiece(i).canCapture(this.getRowPos(), this.getColPos(), board) ) {
				counter++;
			}
		}
		return counter;
	}
	public int howManyPiecesCheck(int row, int col, piece[][] board, Player enemy) {
		int counter = 0;
		piece previousPiece = board[row][col];
		board[row][col] = this;
		for(int i = 0; i < 16; i++) {
			if(enemy.getPiece(i).canCapture(row, col, board) ) {
				counter++;
			}
		}
		board[row][col] = previousPiece;
		return counter;
	}
	public void move(int row, int col, piece[][] board) {
		if(canMove(row, col, board)) {
			setPos(row, col);
			hasMoved = true;
		}
	}
	public void move(int row, int col, piece[][] board, Player enemy) {
		if(canMove(row, col, board, enemy)) {
			setPos(row, col);
			hasMoved = true;		
		}
	}
	public boolean getHasMoved() {
		return hasMoved;
	}
}
