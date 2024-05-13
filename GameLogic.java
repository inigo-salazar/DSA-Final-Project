import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

public class GameLogic implements ActionListener {
	private JButton[][] tiles;
	private int[] knightPos = new int[2];
	private JButton giveUp;
	private int x = 0; //Knight's x-coordinate/row
	private int y = 0;	//Knight's y-coordinate/column
	private int captured = 0; //no. of pieces captured
	private int repeated = 0; //no. of times landed on same tile
	private boolean win;
	private String dateTimeLog, completion;
	private LocalTime start, end, previous;
	private ArrayList<Movement> movements = new ArrayList<Movement>();
	private int turns = 0;
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	//Get variables from Chessboard
	public GameLogic(JButton[][] tiles, int[] knightPos, JButton giveUp) {
		this.tiles = tiles;
		this.knightPos = knightPos;
		this.giveUp = giveUp;
		
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
		
		if (captured < 1){
			start = LocalTime.now();
			previous = start;
		}
		
		//If player gives up, lets down, runs around, and deserts...
		//If player makes cry, says goodbye, tells a lie, and hurts...
		if(clickedButton.getText().equals("Give Up")){
			win = false;
			end = LocalTime.now();
			
			completion = calculateTotalTime(start,end);
			dateTimeLog = gameDateTime();
			formatStartTime(start);
			formatEndTime(end);
			/* Test: Mini-summary
			System.out.println("Start time : " + start);
			System.out.println("End time : " + end);
			System.out.println("Completion time : " + completion);
			System.out.println("\nMovements:");
			System.out.println("------------------");
			for(int i = 0; i < movements.size(); i++){
				System.out.println("Movement " + (i+1));
				System.out.println("Position: (" + movements.get(i).getX() + "," + movements.get(i).getY() + ")");
				System.out.println("Time Taken: " + movements.get(i).getTimeTaken() + "\n");
			}
			*/
			//Transition from here
			
			JOptionPane.showMessageDialog(null, "You lose!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//Valid move (Tile landed on before)
		else if(clickedButton.getText().equals("") && validateMove(clickedButton, turns)){
			repeated++;
		}
		//Valid move (Captured pawn)
		else if(clickedButton.getText().equals("P") && validateMove(clickedButton,turns)){
			captured++;
			if (captured == 31){
				win = true;
				end = LocalTime.now();
				completion = calculateTotalTime(start,end);
				dateTimeLog = gameDateTime();
				formatStartTime(start);
				formatEndTime(end);
				
				// Transition from here
				
				System.out.println("You Win!");
			}
		}
		//Invalid move (Error message)
		else {
			JOptionPane.showMessageDialog(null, "Invalid Move", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		knightPos[0] = x;
		knightPos[1] = y;
		turns++;
	}
	
	private boolean validateMove(JButton clickedButton, int turns){
		//Iterate through valid moves
			if(x + 2 <= 3 && y + 1 <= 7){
				if (clickedButton == tiles[x+2][y+1]){
					tiles[x][y].setText("");
					x += 2;
					y += 1;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			if(x + 2 <= 3 && y - 1 >= 0){
				if (clickedButton == tiles[x+2][y-1]){
					tiles[x][y].setText("");
					x += 2;
					y -= 1;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			if(x - 2 >= 0 && y + 1 <= 7){
				if (clickedButton == tiles[x-2][y+1]){
					tiles[x][y].setText("");
					x -= 2;
					y += 1;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			if(x - 2 >= 0 && y - 1 >= 0){
				if (clickedButton == tiles[x-2][y-1]){
					tiles[x][y].setText("");
					x -= 2;
					y -= 1;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			if(x + 1 <= 3 && y + 2 <= 7){
				if (clickedButton == tiles[x+1][y+2]){
					tiles[x][y].setText("");
					x += 1;
					y += 2;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				} 
			} 
			if(x - 1 >= 0 && y + 2 <= 7){
				if (clickedButton == tiles[x-1][y+2]){
					tiles[x][y].setText("");
					x -= 1;
					y += 2;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			if(x + 1 <= 3 && y - 2 >= 0){
				if (clickedButton == tiles[x+1][y-2]){
					tiles[x][y].setText("");
					x += 1;
					y -= 2;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			if(x - 1 >= 0 && y - 2 >= 0){
				if (clickedButton == tiles[x-1][y-2]){
					tiles[x][y].setText("");
					x -= 1;
					y -= 2;
					tiles[x][y].setText("K");
					movements.add(new Movement(x, y, Duration.between(previous, LocalTime.now())));
					return true;
				}
			}
			
		//If move is invalid	
		return false;
	}
	
	private String calculateTotalTime(LocalTime start, LocalTime end){
		Duration total = Duration.between(start, end);
		
		return String.format("%d:%02d:%02d", total.toHours(), total.toMinutesPart(), total.toSecondsPart());
	}
	
	private String gameDateTime(){
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
		
		return dateTime.format(formattedDateTime);
	}
	
	private String formatStartTime(LocalTime start){
		return format.format(start);
	}
	
	private String formatEndTime(LocalTime end){
		return format.format(end);
	}
	
	//Enable/Disable Game Logic
	private void addActionListeners() {
		for (JButton[] row : tiles) {
			for (JButton tile : row) {
				tile.addActionListener(this);
			}
		}
		
		giveUp.addActionListener(this);
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
	
	//Summary
	public boolean getOutcome(){
		return win;
	}
	public String getCompletionTime(){
		return completion;
	}
	public int getCapturedPieces(){
		return captured;
	}
	public int getPassedSameTile(){
		return repeated;
	}
	//Movements
	public ArrayList<Movement> getMovements(){
		return movements;
	}
	//All games/log
	public String getDateTimeLog(){
		return dateTimeLog;
	}
}

class Movement{
	private int x;
	private int y;
	private Duration timeTaken;
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public Movement(int x, int y, Duration timeTaken){
		this.x = x;
		this.y = y;
		this.timeTaken = timeTaken;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public String getTimeTaken(){
		return String.format("%d:%02d:%02d", timeTaken.toHours(), timeTaken.toMinutesPart(), timeTaken.toSecondsPart());
	}
}