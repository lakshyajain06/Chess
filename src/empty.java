
public class empty extends piece{

	public empty(int row, int col) {
		super("----", row, col);
	}

	public void move(int row, int col, piece[][] board) {
		
	}

	public boolean canMove(int row, int col, piece[][] board) {
		return false;
	}

	@Override
	public boolean canCapture(int row, int col, piece[][] board) {
		return false;
	}

}
