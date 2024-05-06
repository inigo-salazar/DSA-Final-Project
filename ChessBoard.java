import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ChessBoard extends JFrame{
	private JButton[][] tiles;
	private JPanel gameBoard = new JPanel(new GridLayout(4, 8));
	private JLabel gameLabel = new JLabel("Knight's Chess", JLabel.CENTER);
	
	private int[] knightPos = new int[2]; //Coordinate position of knight: Key = row num, Value = col num
	
	/* TEMPORARILY DISABLED FOR TESTING
	private JLabel gameLetterCoordinate = new JLabel("A", JLabel.CENTER);
	private JLabel gameNumberCoordinate = new JLabel("1", JLabel.CENTER);
	private JLabel gameWest = new JLabel("", JLabel.CENTER);
	*/
	
	ChessBoard(){
		setTitle("Knight's Chess"); //Window title
		setSize(500, 500); //Window size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); 
		
		//4x8 grid (Center)
		tiles = new JButton[4][8];
		
		//Game label (North)
		gameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		
		/* TEMPORARILY DISABLED FOR TESTING
		//Game Letter Coordinate (South)
		gameLetterCoordinate = new JLabel("A\nB\nC\nD\nE\nF\nG\nH", JLabel.CENTER);
		
		//Game Number Coordinate (EAST)
		gameNumberCoordinate = new JLabel("1\t2\t3\t4", JLabel.CENTER);
		*/
		
		//Borderlayout 
		add(gameBoard, BorderLayout.CENTER); //Game board (Tiles)
		add(gameLabel, BorderLayout.NORTH); //Game label
		
		/* TEMPORARILY DISABLED FOR TESTING
		add(gameLetterCoordinate, BorderLayout.SOUTH); //Letter coordinate
		add(gameNumberCoordinate, BorderLayout.EAST); //Number coordinate
		add(gameWest, BorderLayout.WEST); // Empty space?
		*/
		
		boolean knightExists = false;
		Random rand = new Random();
		int knightSeeder = rand.nextInt(31);
		//Seed 4x8 board
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new JButton();
				tiles[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
				
				//Seed Knight and Pawn
				if(((j + (i * tiles[i].length))  == knightSeeder) && !isKnightPresent(knightExists)){
					tiles[i][j].setText("K");
					knightExists = true;
					knightPos[0] = i;
					knightPos[1] = j;
				}
				else{
					tiles[i][j].setText("P");
				}
				
				gameBoard.add(tiles[i][j]);
			}
		}
	}
	
	//Show board
	public void load(){
		setVisible(true);
	}
	
	private boolean isKnightPresent(boolean knightExists){
		if (!knightExists)
			return false;
		return true;
	}
	
	//Getters for Game Logic
	public int[] getKnightPosition(){
		return knightPos;
	}
	public JButton[][] getTiles() {
		return tiles;
	}
}