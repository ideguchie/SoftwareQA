package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
	
	JFrame myFrame;
	
	public static void main(String args[]) {
	Login currentLogin = new Login();
		
	}
	
	public Login() {
		myFrame = new JFrame("Host Login");
		setup();
		myFrame.setVisible(true);
	}
	
	private void setup() {
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		
		JPanel name = new JPanel();
		JLabel nameText = new JLabel("User Name");
		JTextField nameField = new JTextField("default name");
		name.add(nameText);
		name.add(nameField);
		nameField.setEnabled(false);
		
		JPanel password = new JPanel();
		JLabel passwordText = new JLabel("Password");
		JTextField passwordField = new JTextField("**************");
		password.add(passwordText);
		password.add(passwordField);
		passwordField.setEnabled(false);
		
		JPanel buttons = new JPanel();
		JButton enter = new JButton("Login");
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AuctionSelectWindow window = new AuctionSelectWindow();
				myFrame.dispose();
			}
		});
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
				);
		buttons.add(enter);
		buttons.add(exit);
		
		myFrame.add(name, BorderLayout.NORTH);
		myFrame.add(password, BorderLayout.CENTER);
		myFrame.add(buttons, BorderLayout.PAGE_END);
		myFrame.pack();
		
	}

}
