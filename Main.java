import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        StartScreen start = new StartScreen();

        // Wait for the username to be submitted
        String username = start.getUsername();

        if (username != null) {
            ChessBoard board = new ChessBoard(username);
            board.load();

            // Enable game logic
            GameLogic logic = new GameLogic(board.getTiles(), board.getKnightPosition(), board.getGiveUpButton());
        }
    }
}