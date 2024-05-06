import javax.swing.*;
import java.awt.*;

public class Main{
	
	public static void main(String[] args){
		//New board instance
		//StartScreen start = new StartScreen();
		ChessBoard board = new ChessBoard();
		
		//Show board
		board.load();
		
		//Enable game logic
		GameLogic logic = new GameLogic(board.getTiles(), board.getKnightPosition());
	}
}

