/*
 * @author Brandon Scholer
 * @Version 6/3/2015
 */

package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Auction;

/**
 * Class that allows acess to auctions.
 * @author Brandon
 *
 */
public class AuctionSelectWindow {
	
	/**
	 * The window that the auctions will be displayed on.
	 */
	JFrame myWindow;
	
	/**
	 * The List that contains the Auctions.
	 */
	JList<String> myAuctionList;
	
	/**
	 * The Pane that contains the list.
	 */
	JScrollPane myScrollPane;
	
	/**
	 * The dimension of the screen the program is being run on.
	 */
	Dimension screenSize;
	
	/**
	 * Populates instance fields and starts window setup.
	 */
	public AuctionSelectWindow() {
		myWindow = new JFrame("Auction List");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setup();
	}
	
	/**
	 * Sets up the window that displays the various auctions
	 * that are accessible.
	 */
	private void setup() {
		
		myWindow.setSize(screenSize.width / 2, screenSize.height / 2);
		myWindow.setLocationRelativeTo(null);
		myWindow.setVisible(true);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel auctionLabel = new JLabel("Auctions");
		
		myAuctionList = setupAuctionList();
		
		myScrollPane = new JScrollPane(myAuctionList);
		myWindow.add(auctionLabel, BorderLayout.NORTH);
		myWindow.add(myScrollPane, BorderLayout.CENTER);
		buttonSetup();
	}
	
	/**
	 * Sets up the buttons that will control auction selection.
	 */
	private void buttonSetup() {
		
		JPanel buttons = new JPanel();
		JButton select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton newAuction = createNewAuction();
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		} );
		
		buttons.add(select);
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!myAuctionList.isSelectionEmpty()) {
					Auction current = new Auction(myAuctionList.getSelectedValue());
					CurrentAuctionWindow auctionWindow = new CurrentAuctionWindow(current);
				} else 
					JOptionPane.showMessageDialog(myWindow, "An Auction must be selected.");
			}
		});
		
		buttons.add(newAuction);
		buttons.add(exit);
		myWindow.add(buttons, BorderLayout.PAGE_END);
		
	}

	/**
	 * Populates the list of Auctions.
	 * @return list of auctions.
	 */
	private JList<String> setupAuctionList() {
		
		ArrayList<String> auctionNames = new ArrayList<String>();
		
		String dir = System.getProperty("user.dir") + "/My_Auctions";
		File file = new File(dir);
		String[] names = file.list();
		
		for(String name : names) {
			if (new File(dir + "/" + name).isDirectory())
				auctionNames.add(name);
		}
		
		return new JList(auctionNames.toArray());
	}
	
	/**
	 * Sets up the button that allows for the creation of
	 * new Auctions.
	 * @return the createAuction button.
	 */
	private JButton createNewAuction() {
		JButton createAuction = new JButton("Create Auction");
		createAuction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame getAuctionName = new JFrame("New Auction");
				JPanel textPanel = new JPanel();
				JPanel buttonPanel = new JPanel();
				JLabel nameLabel = new JLabel("Enter Name: ");
				final JTextField auctionName = new JTextField(20);
				JButton okay = new JButton("Okay");
				okay.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = auctionName.getText();
						String folder = System.getProperty("user.dir") + "/My_Auctions/";
						new File(folder + name).mkdirs();
						getAuctionName.dispose();
						myWindow.dispose();
						AuctionSelectWindow another = new AuctionSelectWindow();
					}
				});
				
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						getAuctionName.dispose();
					}
				});
				
				textPanel.add(nameLabel);
				textPanel.add(auctionName);
				buttonPanel.add(okay);
				buttonPanel.add(cancel);
				getAuctionName.add(textPanel, BorderLayout.NORTH);
				getAuctionName.add(buttonPanel, BorderLayout.SOUTH);
				getAuctionName.setVisible(true);
				getAuctionName.pack();
				getAuctionName.setLocationRelativeTo(null);
			}
		});
		
		return createAuction;
	}

}
