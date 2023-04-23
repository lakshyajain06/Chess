
public class GameRunner {
	public static ChessGame game = new ChessGame();
	
	public static void main(String[] args) {
		game.printBoard();
		for(int i = 0; !game.getGameOver(); i++) {byub
			if(i % 2 == 0) {
				game.playTurn(game.getPlayer('w'));
			}
			else game.playTurn(game.getPlayer('b'));
		}
		System.out.println("game over");
	}
}
