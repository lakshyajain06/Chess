public class ChessGame {
	private piece[][] board = new piece[8][8];
	private Player White = new Player('w');
	private Player Black = new Player('b');
	private empty[][] emptySpots = new empty[8][8];
	private boolean gameOver = false;
	
	public ChessGame() {
		for(int i = 0; i < 8; i++) {
			for(int c  = 0; c < 8; c++) {
				emptySpots[i][c] = new empty(i,c);
			}
		}
		for(int i = 0; i < 8; i++) {
			for(int c  = 0; c < 8; c++) {
				board[i][c] = emptySpots[i][c];
			}
		}
		for(int i = 0; i < 16; i++) {
			board[White.getPiece(i).getRowPos()][White.getPiece(i).getColPos()] = White.getPiece(i);
			board[Black.getPiece(i).getRowPos()][Black.getPiece(i).getColPos()] = Black.getPiece(i);
		}
	}
	public void playTurn(Player x) {
		piece pieceSelected;
		String input;
		int rowInput;
		int colInput;
		int previousRowPos;
		int previousColPos;
		boolean turnOver = false;
		
		pieceSelected = userPieceInput(x);
		previousRowPos = pieceSelected.getRowPos();
		previousColPos = pieceSelected.getColPos();
		
		while(!turnOver) {
			System.out.println("The piece you have chosen is " + pieceSelected.getName() + ". If you want to choose a different piece, type 'r'. Type the position you want to move the piece to.");
			input = TextIO.getlnString();
			
			
			if(input.equals("r")) {
				printBoard();
				pieceSelected = userPieceInput(x);
				continue;
			}
			if(!input.isEmpty()) {
				switch(input.substring(0,1)) {
					case "a":
						colInput = 0;
						break;
					case "b":
						colInput = 1;
						break;					
					case "c":
						colInput = 2;
						break;
						
					case "d":
						colInput = 3;
						break;
						
					case "e":
						colInput = 4;
						break;
						
					case "f":
						colInput = 5;
						break;
						
					case "g":
						colInput = 6;
						break;
						
					case "h":
						colInput = 7;
						break;
						
					default:
						System.out.println("Enter a real spot");
						colInput = 8;
				}
			}
			else {
				System.out.println("Enter a spot");
				continue;
			}
			if(colInput == 8) {
				continue;
			}
			try {	
				rowInput = Integer.parseInt(input.substring(1)) - 1;
				if(!(rowInput >= 0 && rowInput <= 7)) {
					System.out.println("Enter a real spot");
					continue;
				}
			}
			catch(Exception e) {
				System.out.println("Enter a real spot");
				continue;
			}
			
			
			if(!(pieceSelected instanceof King)) {
				if(!pieceSelected.canMove(rowInput, colInput, board)) {
					System.out.println("You can't move there");
					continue;
				}
				else {
					actualMovePiece(pieceSelected, rowInput, colInput);
				}
			}
			else if(canCastle(pieceSelected, rowInput, colInput)) {
				if(x.getColor() == 'w') {
					if(((King)pieceSelected).isNotCheck(board, Black)) {
						castle(pieceSelected, rowInput, colInput);
					}
					else {
						System.out.println("You can't castle under check");
						continue;
					}
				}
				else {
					if(((King)pieceSelected).isNotCheck(board, White)) {
						castle(pieceSelected, rowInput, colInput);
					}
					else {
						System.out.println("You can't castle under check");
						continue;
					}
				}
			}
			else if(pieceSelected.isWhite()){
				if(!((King)pieceSelected).canMove(rowInput, colInput, board, Black)) {
					System.out.println("You can't move there");
					continue;
				}
				else {
					actualMovePiece(pieceSelected, rowInput, colInput);
				}
			}else if(!pieceSelected.isWhite()){
				if(!((King)pieceSelected).canMove(rowInput, colInput, board, White)) {
					System.out.println("You can't move there");
					continue;
				}
				else {
					actualMovePiece(pieceSelected, rowInput, colInput);
				}
			}
			if(x.getColor() == 'w') {
				if(!((King) White.getPiece(15)).isNotCheck(board, Black)) {
					System.out.println("You can't move there. King would be under check.");
					board[pieceSelected.getRowPos()][pieceSelected.getColPos()] = emptySpots[pieceSelected.getRowPos()][pieceSelected.getColPos()];
					pieceSelected.setPos(previousRowPos, previousColPos);
					board[previousRowPos][previousColPos] = pieceSelected;
					continue;

				}
			}
			else {
				if(!((King) Black.getPiece(15)).isNotCheck(board, White)) {
					System.out.println("You can't move there. King would be under check.");
					board[pieceSelected.getRowPos()][pieceSelected.getColPos()] = emptySpots[pieceSelected.getRowPos()][pieceSelected.getColPos()];
					pieceSelected.setPos(previousRowPos, previousColPos);
					board[previousRowPos][previousColPos] = pieceSelected;
					continue;
				}
			}
			turnOver = true;
		}
		if(x.getColor() == 'w') {
			if(!((King)Black.getPiece(15)).isNotCheck(board, White)) {
				System.out.println("Check");
				Black.setCheck(true);
			}
		}
		else{
			if(!((King)White.getPiece(15)).isNotCheck(board, Black)) {
				System.out.println("Check");
				White.setCheck(true);
			}
		}
		if(White.isCheck()){
			isgameOver(White);
		}
		else if(Black.isCheck()){
			isgameOver(Black);
		}
		printBoard();
	}
	public piece userPieceInput(Player x) {
		String input;
		int rowInput;
		int colInput;
		boolean validInput = false;
		piece pieceSelected = null;
		
		while(!validInput) {
			if(x.getColor() == 'w') {
				System.out.println("White's turn");
			}
			else {
				System.out.println("Black's turn");
			}
			System.out.println("Type position of piece you want to move. ex: a2");
			input = TextIO.getlnString();
			if(!input.isEmpty()) {
				switch(input.substring(0,1)) {
					case "a":
						colInput = 0;
						break;
					case "b":
						colInput = 1;
						break;
						
					case "c":
						colInput = 2;
						break;
						
					case "d":
						colInput = 3;
						break;
						
					case "e":
						colInput = 4;
						break;
						
					case "f":
						colInput = 5;
						break;
						
					case "g":
						colInput = 6;
						break;
						
					case "h":
						colInput = 7;
						break;
						
					default:
						System.out.println("Enter a real spot");
						colInput = 8;
				}
			}
			else {
				System.out.println("Enter a spot");
				continue;
			}
			if(colInput == 8) {
				continue;
			}
			try {	
				rowInput = Integer.parseInt(input.substring(1)) - 1;
				if(!(rowInput >= 0 && rowInput <= 7)) {
					System.out.println("Enter a real spot");
					continue;
				}
			}
			catch(Exception e) {
				System.out.println("Enter a real spot");
				continue;
			}
			if(!(board[rowInput][colInput] instanceof empty)) {
				if((x.getColor() == 'w' && board[rowInput][colInput].isWhite()) || (x.getColor() == 'b' && !board[rowInput][colInput].isWhite())) {
					pieceSelected = board[rowInput][colInput];
					
				}
				else {
					System.out.println("Select your own piece");
					continue;
				}
			}
			else {
				System.out.println("There is no piece there");
				continue;
			}
			validInput = true;
		}
		return pieceSelected;
	}
	public void actualMovePiece(piece y, int row, int col) {
		if(y.canCapture(row, col, board)) {
			board[row][col].setDead();
		}
		board[y.getRowPos()][y.getColPos()] = emptySpots[y.getRowPos()][y.getColPos()];
		y.move(row, col, board);
		board[y.getRowPos()][y.getColPos()] = y;
	}
	public boolean canCastle(piece x, int row, int col) {
		if(!((King)x).getHasMoved()){
			if(x.isWhite()) {
				if(row == 0) {
					if(col == 6) {
						if(!((Rook)White.getPiece(9)).getHasMoved() && board[0][5] instanceof empty && board[0][6] instanceof empty && ((King)x).isNotCheck(row, col, board, Black)) {
							return true;
						}
					}
					if(col == 2) {
						if(!((Rook)White.getPiece(8)).getHasMoved() && board[0][1] instanceof empty && board[0][2] instanceof empty && board[0][3] instanceof empty && ((King)x).isNotCheck(row, col, board, Black)) {
							return true;
						}
					}
				}
			}
			else if(!x.isWhite()) {
				if(((King)x).isNotCheck(board, White)) {
					if(row == 7) {
						if(col == 6) {
							if(!((Rook)Black.getPiece(9)).getHasMoved() && board[7][5] instanceof empty && board[7][6] instanceof empty && ((King)x).isNotCheck(row, col, board, White)) {
								return true;
							}
						}
						if(col == 2) {
							if(!((Rook)Black.getPiece(8)).getHasMoved() && board[7][1] instanceof empty && board[7][2] instanceof empty && board[7][3] instanceof empty && ((King)x).isNotCheck(row, col, board, White)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	public void castle(piece x, int row, int col) {
		if(!((King)x).getHasMoved()){
			if(x.isWhite()) {
				if(row == 0) {
					if(col == 6) {
						if(!((Rook)White.getPiece(9)).getHasMoved() && board[0][5] instanceof empty && board[0][6] instanceof empty && ((King)x).isNotCheck(row, col, board, Black)) {
							board[x.getRowPos()][x.getColPos()] = emptySpots[x.getRowPos()][x.getColPos()];
							board[White.getPiece(9).getRowPos()][White.getPiece(9).getColPos()] = emptySpots[White.getPiece(9).getRowPos()][White.getPiece(9).getColPos()];
							x.setPos(row, col);
							White.getPiece(9).setPos(0, 5);
							board[x.getRowPos()][x.getColPos()] = x;
							board[White.getPiece(9).getRowPos()][White.getPiece(9).getColPos()] = White.getPiece(9);
						}
					}
					if(col == 2) {
						if(!((Rook)White.getPiece(8)).getHasMoved() && board[0][1] instanceof empty && board[0][2] instanceof empty && board[0][3] instanceof empty && ((King)x).isNotCheck(row, col, board, Black)) {
							board[x.getRowPos()][x.getColPos()] = emptySpots[x.getRowPos()][x.getColPos()];
							board[White.getPiece(8).getRowPos()][White.getPiece(8).getColPos()] = emptySpots[White.getPiece(8).getRowPos()][White.getPiece(8).getColPos()];
							x.setPos(row, col);
							White.getPiece(8).setPos(0, 3);
							board[x.getRowPos()][x.getColPos()] = x;
							board[White.getPiece(8).getRowPos()][White.getPiece(8).getColPos()] = White.getPiece(8);
						}
					}
				}
			}
			else if(!x.isWhite()) {
				if(((King)x).isNotCheck(board, White)) {
					if(row == 7) {
						if(col == 6) {
							if(!((Rook)Black.getPiece(9)).getHasMoved() && board[7][5] instanceof empty && board[7][6] instanceof empty && ((King)x).isNotCheck(row, col, board, White)) {
								board[x.getRowPos()][x.getColPos()] = emptySpots[x.getRowPos()][x.getColPos()];
								board[Black.getPiece(9).getRowPos()][Black.getPiece(9).getColPos()] = emptySpots[Black.getPiece(9).getRowPos()][Black.getPiece(9).getColPos()];
								x.setPos(row, col);
								Black.getPiece(9).setPos(7, 5);
								board[x.getRowPos()][x.getColPos()] = x;
								board[Black.getPiece(9).getRowPos()][Black.getPiece(9).getColPos()] = Black.getPiece(9);
							}
						}
						if(col == 2) {
							if(!((Rook)Black.getPiece(8)).getHasMoved() && board[7][1] instanceof empty && board[7][2] instanceof empty && board[7][3] instanceof empty && ((King)x).isNotCheck(row, col, board, White)) {
								board[x.getRowPos()][x.getColPos()] = emptySpots[x.getRowPos()][x.getColPos()];
								board[Black.getPiece(8).getRowPos()][Black.getPiece(8).getColPos()] = emptySpots[Black.getPiece(8).getRowPos()][Black.getPiece(8).getColPos()];
								x.setPos(row, col);
								Black.getPiece(8).setPos(7, 3);
								board[x.getRowPos()][x.getColPos()] = x;
								board[Black.getPiece(8).getRowPos()][Black.getPiece(8).getColPos()] = Black.getPiece(8);
							}
						}
					}
				}
			}
		}
	}
	public piece whichPieceChecks(King king, Player enemy) {
		piece pieceThatChecks = emptySpots[1][1];
		for(int i = 0; i < 16; i++) {
			if(enemy.getPiece(i).canCapture(king.getRowPos(), king.getColPos(), board) ) {
				pieceThatChecks = enemy.getPiece(i);
				break;
			}
		}
		return pieceThatChecks;
	}
	public boolean anyPieceCanTake(Player x, King king) {
		if(x.getColor() == 'w') {
			for(int i = 0; i < 15; i++){
				if(x.getPiece(i).canCapture(whichPieceChecks(king, Black).getRowPos(), whichPieceChecks(king, Black).getColPos(), board)) {
					return true;
				}
			}
		}
		else {
			for(int i = 0; i < 15; i++){
				if(x.getPiece(i).canCapture(whichPieceChecks(king, White).getRowPos(), whichPieceChecks(king, White).getColPos(), board)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean anyPieceCanMove(Player x, int row, int col) {
		if(x.getColor() == 'w') {
			for(int i = 0; i < 15; i++){
				if(x.getPiece(i).canMove(row, col, board)) {
					return true;
				}
			}
		}
		else {
			for(int i = 0; i < 15; i++){
				if(x.getPiece(i).canMove(row, col, board)) {
					return true;
				}
			}
		}
		return false;
	}
	public void isgameOver(Player x) {
		boolean notCheckMate = false;
		King king = (King)x.getPiece(15);
		piece checkPiece;
		
		if(king.isWhite()) {
			if(king.howManyPiecesCheck(board, Black) > 1) {
				for(int i = -1; i < 2; i++) {
					for(int c = -1; c < 2; c++) {
						if(king.canMove(king.getRowPos() + i, king.getColPos() + c, board, Black)){
							notCheckMate = true;
						}
					}
				}
				if(!notCheckMate) {
					gameOver = true;
					return;
				}
			}
			else {
				checkPiece = whichPieceChecks(king, Black);
				for(int i = -1; i < 2; i++) {
					for(int c = -1; c < 2; c++) {
						if(king.canMove(king.getRowPos() + i, king.getColPos() + c, board, Black)){
							notCheckMate = true;
						}
					}
				}
				if(anyPieceCanTake(x, king)) {
					notCheckMate = true;
				}
				if(checkPiece instanceof Rook) {
					if(king.getRowPos()-checkPiece.getRowPos() == 0) {
						for(int i = 1; i < Math.abs(king.getColPos()-checkPiece.getColPos()); i++) {
							if(king.getColPos() > checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()+i)) {
									notCheckMate = true;
								}
							}
							if(king.getColPos() < checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()-i)) {
									notCheckMate = true;
								}
							}
						}
					}
					else if(king.getColPos()-checkPiece.getColPos() == 0) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()+i, king.getColPos())) {
									notCheckMate = true;
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()-i, king.getColPos())) {
									notCheckMate = true;
								}
							}
						}
					}
				}
				else if(checkPiece instanceof Bishop) {
					if(Math.abs(king.getColPos()- checkPiece.getColPos()) == Math.abs(king.getRowPos()-checkPiece.getRowPos())) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
						}
					}
				}
				else if(checkPiece instanceof Queen) {
					if(king.getRowPos()-checkPiece.getRowPos() == 0) {
						for(int i = 1; i < Math.abs(king.getColPos()-checkPiece.getColPos()); i++) {
							if(king.getColPos() > checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()+i)) {
									notCheckMate = true;
								}
							}
							if(king.getColPos() < checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()-i)) {
									notCheckMate = true;
								}
							}
						}
					}
					else if(king.getColPos()-checkPiece.getColPos() == 0) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()+i, king.getColPos())) {
									notCheckMate = true;
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()-i, king.getColPos())) {
									notCheckMate = true;
								}
							}
						}
					}
					else if(Math.abs(king.getColPos()- checkPiece.getColPos()) == Math.abs(king.getRowPos()-checkPiece.getRowPos())) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
						}
					}
				}
			}
		}
		else {
			if(king.howManyPiecesCheck(board, White) > 1) {
				for(int i = -1; i < 2; i++) {
					for(int c = -1; c < 2; c++) {
						if(king.canMove(king.getRowPos() + i, king.getColPos() + c, board, White)){
							notCheckMate = true;
						}
					}
				}
				if(!notCheckMate) {
					gameOver = true;
					return;
				}
			}
			else {
				checkPiece = whichPieceChecks(king, White);
				for(int i = -1; i < 2; i++) {
					for(int c = -1; c < 2; c++) {
						if(king.canMove(king.getRowPos() + i, king.getColPos() + c, board, White)){
							notCheckMate = true;
						}
					}
				}
				if(anyPieceCanTake(x, king)) {
					notCheckMate = true;
				}
				if(checkPiece instanceof Rook) {
					if(king.getRowPos()-checkPiece.getRowPos() == 0) {
						for(int i = 1; i < Math.abs(king.getColPos()-checkPiece.getColPos()); i++) {
							if(king.getColPos() > checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()+i)) {
									notCheckMate = true;
								}
							}
							if(king.getColPos() < checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()-i)) {
									notCheckMate = true;
								}
							}
						}
					}
					else if(king.getColPos()-checkPiece.getColPos() == 0) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()+i, king.getColPos())) {
									notCheckMate = true;
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()-i, king.getColPos())) {
									notCheckMate = true;
								}
							}
						}
					}
				}
				else if(checkPiece instanceof Bishop) {
					if(Math.abs(king.getColPos()- checkPiece.getColPos()) == Math.abs(king.getRowPos()-checkPiece.getRowPos())) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
						}
					}
				}
				else if(checkPiece instanceof Queen) {
					if(king.getRowPos()-checkPiece.getRowPos() == 0) {
						for(int i = 1; i < Math.abs(king.getColPos()-checkPiece.getColPos()); i++) {
							if(king.getColPos() > checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()+i)) {
									notCheckMate = true;
								}
							}
							if(king.getColPos() < checkPiece.getColPos()) {
								if(anyPieceCanMove(x, king.getRowPos(), checkPiece.getColPos()-i)) {
									notCheckMate = true;
								}
							}
						}
					}
					else if(king.getColPos()-checkPiece.getColPos() == 0) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()+i, king.getColPos())) {
									notCheckMate = true;
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(anyPieceCanMove(x, checkPiece.getRowPos()-i, king.getColPos())) {
									notCheckMate = true;
								}
							}
						}
					}
					else if(Math.abs(king.getColPos()- checkPiece.getColPos()) == Math.abs(king.getRowPos()-checkPiece.getRowPos())) {
						for(int i = 1; i < Math.abs(king.getRowPos()-checkPiece.getRowPos()); i++) {
							if(king.getRowPos() > checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() + i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
							if(king.getRowPos() < checkPiece.getRowPos()) {
								if(king.getColPos() > checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() + i)) {
										notCheckMate = true;
									}
								}
								if(king.getColPos() < checkPiece.getColPos()) {
									if(anyPieceCanMove(x, checkPiece.getRowPos() - i, checkPiece.getColPos() - i)) {
										notCheckMate = true;
									}
								}
							}
						}
					}
				}
			}
		}
		if(notCheckMate) {
			gameOver = false;
		}
		else {
			gameOver = true;
		}
	}
	
	public void printBoard() {
		for(int i = 0; i < 7; i++) {
			System.out.printf("%3d%10s%10s%10s%10s%10s%10s%10s%10s", 8-i, board[7-i][0].getName(), board[7-i][1].getName(), board[7-i][2].getName(), board[7-i][3].getName(), board[7-i][4].getName(), board[7-i][5].getName(), board[7-i][6].getName(), board[7-i][7].getName());
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
		System.out.printf("%3d%10s%10s%10s%10s%10s%10s%10s%10s", 1, board[0][0].getName(), board[0][1].getName(), board[0][2].getName(), board[0][3].getName(), board[0][4].getName(), board[0][5].getName(), board[0][6].getName(), board[0][7].getName());
		System.out.println();
		System.out.println();
		System.out.printf("%10s%10s%10s%10s%10s%10s%10s%10s", "a", "b", "c", "d", "e", "f", "g", "h");
		System.out.println();
		System.out.println();
	}
	public Player getPlayer(char x) {
		if(x == 'w') {
			return White;
		}
		else {
			return Black;
		}
	}
	public boolean getGameOver() {
		return gameOver;
	}
}
