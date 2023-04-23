public abstract class piece {
	private String name;
	private boolean pieceColorWhite;
	private boolean alive = true;
	private int rowPos;
	private int colPos;

	public piece(String name, int row, int col) {
		rowPos = row;
		colPos = col;
		this.name = name;
	}
	
	public piece(String name, char x, int row, int col) {
		this.name = name;
		rowPos = row;
		colPos = col;
		if(x == 'w') {
			this.pieceColorWhite = true;
		}
		else{
			this.pieceColorWhite = false;
		}
	}
	
	public abstract boolean canMove(int row, int col, piece[][] board);
	
	public void move(int row, int col, piece[][] board) {
		if(canMove(row, col, board)) {
			setPos(row, col);
		}
	}
	public boolean canCapture(int row, int col, piece[][] board) {
		if(alive) {
			if(this.isWhite()) {
				if(canMove(row, col, board) && !(board[row][col] instanceof empty) && !board[row][col].isWhite()) {
					return true;
				}	
			}
			else if(!this.isWhite()) {
				if(canMove(row, col, board) && !(board[row][col] instanceof empty) && board[row][col].isWhite()) {
					return true;
				}	
			}
		}
		return false;
	}
	public void setDead() {
		alive = false;
	}
	public boolean isAlive() {
		return alive;
	}
	public int getRowPos() {
		return rowPos;
	}
	public int getColPos() {
		return colPos;
	}
	public void setPos(int row, int col) {
		rowPos = row;
		colPos = col;
	}
	public String getName() {
		return name;
	}
	public boolean isWhite() {
		return pieceColorWhite;
	}

}
