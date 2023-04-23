
public class Player {
	private piece[] pieces = new piece[16];
	private char color;
	private boolean check = false;
	
	public Player(char x) {
		color = x;
		if(x == 'w') {
			for(int i = 0; i < 8; i++) {
				pieces[i] = new Pawn("wPawn", 'w', 1, i);
			}
			pieces[8] = new Rook("wRook", 'w', 0, 0);
			pieces[9] = new Rook("wRook", 'w', 0, 7);
			pieces[10] = new Knight("wKnight", 'w', 0, 1);
			pieces[11] = new Knight("wKnight", 'w', 0, 6);
			pieces[12] = new Bishop("wBishop", 'w', 0, 2);
			pieces[13] = new Bishop("wBishop", 'w', 0, 5);
			pieces[14] = new Queen("wQueen", 'w', 0, 3);
			pieces[15] = new King("wKing", 'w', 0, 4);
		}
		else {
			for(int i = 0; i < 8; i++) {
				pieces[i] = new Pawn("bPawn", 'b', 6, i);
			}
			pieces[8] = new Rook("bRook", 'b', 7, 0);
			pieces[9] = new Rook("bRook", 'b', 7, 7);
			pieces[10] = new Knight("bKnight", 'b', 7, 1);
			pieces[11] = new Knight("bKnight", 'b', 7, 6);
			pieces[12] = new Bishop("bBishop", 'b', 7, 2);
			pieces[13] = new Bishop("bBishop", 'b', 7, 5);
			pieces[14] = new Queen("bQueen", 'b', 7, 3);
			pieces[15] = new King("bKing", 'b', 7, 4);
		}
	}
	public piece getPiece(int x) {
		return pieces[x];
	}
	public char getColor() {
		return color;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
