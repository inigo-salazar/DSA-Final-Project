/*
	Start screen
		- contains title, "load game" button
			- Title: borderlayout NORTH
			- Button: borderlayout CENTER
		- "load game" button puts a popup requesting username; contains input text field, submit button
			- Text field: borderlayout CENTER
			- Submit button: borderlayout CENTER
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JFrame implements ActionListener {

	private JTextField usernameField;
	private JButton submitButton;

	public StartScreen() {
		// Create a JFrame with a title and a default size
		super("Start Screen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 200);

		// Create a JPanel to hold the UI components
		JPanel panel = new JPanel();
		getContentPane().add(panel);

		// Create a JLabel for the username field
		JLabel label = new JLabel("Username:");
		panel.add(label);

		// Create a JTextField for the username
		usernameField = new JTextField(20);
		panel.add(usernameField);

		// Create a JButton for the submit button
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		panel.add(submitButton);

		// Set the JFrame to be visible
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle the submit button action
		String username = usernameField.getText();
		if (isValidUsername(username)) {
			// Proceed with loading the new game
			System.out.println("Username submitted: " + username);
			// Load new game logic here
		} else {
			// Display an error message
			JOptionPane.showMessageDialog(this,
					"Invalid username. Please enter a username with at least 5 letters and 1 number, with no spaces nor special characters.");
		}
	}

	private boolean isValidUsername(String username) {
		// Check if the username meets the specified requirements
		return username.matches("^[a-zA-Z0-9]{5,}$");
	}
	
	public static void main(String[] args) {
		// Create a new instance of the StartScreen
		new StartScreen();
	}
}