import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLogic implements ActionListener {
	private JButton[][] tiles;
	private int[] knightPos = new int[2];
	private int x = 0; //Knight's x-coordinate/row
	private int y = 0;	//Knight's y-coordinate/column
	
	//Get variables from Chessboard
	public GameLogic(JButton[][] tiles, int[] knightPos) {
		this.tiles = tiles;
		this.knightPos = knightPos;
		
		addActionListeners();
	}
	
	//Check Moves
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource(); //Get button clicked
		
		/*
			Capture Logic
			- Knight has a max of 8 L-shaped moves
		*/
		
		x = knightPos[0];
		y = knightPos[1];
		
		//Valid move (Tile landed on before)
		if(clickedButton.getText().equals("") && validateMove(clickedButton)){
			//record same tile here
		}
		//Valid move (Captured pawn)
		else if(clickedButton.getText().equals("P") && validateMove(clickedButton)){
			//increment capture count here
		}
		//Invalid move (Error message)
		else {
			JOptionPane.showMessageDialog(null, "Invalid Move", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		knightPos[0] = x;
		knightPos[1] = y;
		
		//Determine capture count (Increment when capture + count when not increment)
		
		
		//All pawns have been captured (Check condition per move + action if fulfilled)


		//For Summary: Record moves + time taken per move
		
		
	}
	
	private boolean validateMove(JButton clickedButton){
		//Iterate through valid moves
			if(x + 2 <= 3 && y + 1 <= 7){
				if (clickedButton == tiles[x+2][y+1]){
					tiles[x][y].setText("");
					x += 2;
					y += 1;
					tiles[x][y].setText("K");
					return true;
				}
			}
			if(x + 2 <= 3 && y - 1 >= 0){
				if (clickedButton == tiles[x+2][y-1]){
					tiles[x][y].setText("");
					x += 2;
					y -= 1;
					tiles[x][y].setText("K");
					return true;
				}
			}
			if(x - 2 >= 0 && y + 1 <= 7){
				if (clickedButton == tiles[x-2][y+1]){
					tiles[x][y].setText("");
					x -= 2;
					y += 1;
					tiles[x][y].setText("K");
					return true;
				}
			}
			if(x - 2 >= 0 && y - 1 >= 0){
				if (clickedButton == tiles[x-2][y-1]){
					tiles[x][y].setText("");
					x -= 2;
					y -= 1;
					tiles[x][y].setText("K");
					return true;
				}
			}
			if(x + 1 <= 3 && y + 2 <= 7){
				if (clickedButton == tiles[x+1][y+2]){
					tiles[x][y].setText("");
					x += 1;
					y += 2;
					tiles[x][y].setText("K");
					return true;
				} 
			} 
			if(x - 1 >= 0 && y + 2 <= 7){
				if (clickedButton == tiles[x-1][y+2]){
					tiles[x][y].setText("");
					x -= 1;
					y += 2;
					tiles[x][y].setText("K");
					return true;
				}
			}
			if(x + 1 <= 3 && y - 2 >= 0){
				if (clickedButton == tiles[x+1][y-2]){
					tiles[x][y].setText("");
					x += 1;
					y -= 2;
					tiles[x][y].setText("K");
					return true;
				}
			}
			if(x - 1 >= 0 && y - 2 >= 0){
				if (clickedButton == tiles[x-1][y-2]){
					tiles[x][y].setText("");
					x -= 1;
					y -= 2;
					tiles[x][y].setText("K");
					return true;
				}
			}
			
		//If move is invalid	
		return false;
	}
	
	private boolean checkForWin(){
		
		return false;
	}
	
	//Enable/Disable Game Logic
	private void addActionListeners() {
		for (JButton[] row : tiles) {
			for (JButton tile : row) {
				tile.addActionListener(this);
			}
		}
	}
	
	private void removeActionListeners(){
		for (JButton[] row : tiles) {
			for (JButton tile : row) {
				tile.removeActionListener(this);
			}
		}
	}
	
	//Getters for Summary
	/*
		Summary
		- Outcome (W/L)
		- Completion time (add all times recorded)
		- Captured pieces
		- Times Knight passes through same tile (use counter for not increment)
		Movement
		- 2D array [position][time taken] (use the record)
	*/
}
