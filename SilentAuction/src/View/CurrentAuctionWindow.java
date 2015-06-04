/*
 * @author Brandon Scholer
 * @version 6/3/2015
 */

package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Model.Auction;
import Model.Bidder;
import Model.Item;

/**
 * Creates all the views for an auction.
 * @author Brandon
 *
 */
public class CurrentAuctionWindow {
	
	/**
	 * Frame that Auction data will be displayed on
	 */
	JFrame myFrame;
	
	/**
	 * Current Auction whose data is being displayed.
	 */
	Auction myAuction;
	
	/**
	 * Initializes instance fields for selected Auction.
	 * @param auction
	 */
	public CurrentAuctionWindow(Auction auction) {
		myFrame = new JFrame("Auction List");
		myAuction = auction;
		setup();
		
	}
	
	/**
	 * Sets up the window with which various current Auction
	 * information will be displayed(Bidders, Items, Winners).
	 */
	public void setup() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		myFrame.setVisible(true);
		myFrame.setSize(screenSize.width/2, screenSize.height/2);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel bidders = createBidderPane();
		JPanel items = createItemPane();
		JPanel winners = createWinnerPane();
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("Bidders", bidders);
		pane.addTab("Items", items);
		pane.addTab("Winners", winners);
		myFrame.add(pane);
		myFrame.pack();
	}
	
	/**
	 * Sets up the Bidder Pane for the Auction window.
	 * @return the bidder panel.
	 */
	private JPanel createBidderPane() {
		final JTabbedPane pane = new JTabbedPane();
		final JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		final ArrayList<Bidder> biddersList = (ArrayList<Bidder>) myAuction.getBidders();
		final DefaultListModel<Bidder> model = new DefaultListModel<Bidder>();
		for (Bidder bidder : biddersList)
			model.addElement(bidder);
		final JList<Bidder> bidders = new JList(model);
		JButton select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!bidders.isSelectionEmpty()) {
					bidderInfoWindow(biddersList.get(bidders.getSelectedIndex()));
				}
				else
					JOptionPane.showMessageDialog(pane, "A Bidder must be selected.");
			}
		});
		
		JButton add = new JButton("Add Bidder");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBidderWindow(model);
			}
		});
		
		JButton remove = new JButton("Remove Bidder");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeBidderWindow(model, bidders);
			}
		});
		JButton back = createBackButton();
		panel.setBackground(Color.WHITE);
		panel.add(bidders, BorderLayout.NORTH);
		buttonPanel.add(select, BorderLayout.PAGE_END);
		buttonPanel.add(add, BorderLayout.PAGE_END);
		buttonPanel.add(remove, BorderLayout.PAGE_END);
		buttonPanel.add(back, BorderLayout.PAGE_END);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		return panel;
		
	}
	
	/**
	 * Sets up the Item Pane for the Auction window.
	 * @return the item panel.
	 */
	private JPanel createItemPane() {
		final JTabbedPane pane = new JTabbedPane();
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		final ArrayList<Item> itemList = (ArrayList<Item>) myAuction.getItems();
		final DefaultListModel<Item> model = new DefaultListModel<Item>();
		for (Item items : itemList)
			model.addElement(items);
		final JList<Item> items = new JList(model);
		JButton select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!items.isSelectionEmpty())
					itemInfoWindow(itemList.get(items.getSelectedIndex()));
				else
					JOptionPane.showMessageDialog(pane, "An Item must be selected.");
			}
		});
		JButton add = new JButton("Add Item");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItemWindow(model);
			}
		});
		
		JButton remove = new JButton("Remove Item");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeItemWindow(model, items);
			}
		});
		
		JButton back = createBackButton();
		panel.add(items, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		buttonPanel.add(select, BorderLayout.PAGE_END);
		buttonPanel.add(add, BorderLayout.PAGE_END);
		buttonPanel.add(remove, BorderLayout.PAGE_END);
		buttonPanel.add(back, BorderLayout.PAGE_END);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		return panel;
		
	}
	
	/**
	 * Sets up the Winners Pane for the Auction window.
	 * @return the winner panel.
	 */
	private JPanel createWinnerPane() {
		final JTabbedPane pane = new JTabbedPane();
		JPanel panel = new JPanel();
		final ArrayList<Bidder> winnerList = (ArrayList<Bidder>) myAuction.getWinners();
		final JList<String> winners = new JList(winnerList.toArray());
		JButton select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!winners.isSelectionEmpty())
				bidderInfoWindow(winnerList.get(winners.getSelectedIndex()));
				else
					JOptionPane.showMessageDialog(pane, "A Winner must be selected.");
			}
		});
		JButton back = createBackButton();
		panel.add(winners);
		panel.add(select);
		panel.add(back);
		return panel;
		
	}
	
	/**
	 * Sets up and displays window with specific Bidder information.
	 * @param bidder The bidder whose information is to be displayed.
	 */
	private void bidderInfoWindow(Bidder bidder) {
		final JFrame bidderInfo = new JFrame("Bidder Information");
		JLabel name = new JLabel(bidder.toString());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bidderInfo.dispose();
			}
		});
		
		bidderInfo.add(name, BorderLayout.WEST);
		bidderInfo.add(back, BorderLayout.PAGE_END);
		bidderInfo.setLocationRelativeTo(null);
		bidderInfo.setVisible(true);
		bidderInfo.pack();
	}
	
	/**
	 * Sets up and displays window with specific Item information.
	 * @param item The item whose information is to be displayed.
	 */
	private void itemInfoWindow(Item item) {
		final JFrame itemInfo = new JFrame("Item Information");
		JPanel itemDetails = new JPanel();
		itemDetails.setLayout(new BoxLayout(itemDetails, BoxLayout.Y_AXIS));
		//itemInfo.setLayout(new BoxLayout(itemInfo, BoxLayout.PAGE_AXIS));
		JLabel name = new JLabel("Item Name: " + item.getName());
		JLabel startBid = new JLabel("Item Value: " + item.getStartBid());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemInfo.dispose();
			}
		});
		itemDetails.add(name);
		itemDetails.add(startBid);
		itemInfo.add(itemDetails, BorderLayout.NORTH);
		itemInfo.add(back, BorderLayout.PAGE_END);
		itemInfo.setVisible(true);
		itemInfo.pack();
		itemInfo.setLocationRelativeTo(null);
	}
	
	/**
	 * Creates a button to take the user back to the
	 * previous window.
	 * @return the back button
	 */
	private JButton createBackButton() {
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myFrame.dispose();
			}
		});
		
		return back;
	}
	
	/**
	 * Sets up and displays the window to add an item to the
	 * current Auction.
	 * @param model the list of current Items
	 */
	private void addItemWindow(final DefaultListModel<Item> model) {
		final JFrame addItem = new JFrame("Add Item");
		JPanel namePanel = new JPanel();
		JPanel pricePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel nameLabel = new JLabel("Enter Item Name: ");
		final JTextField itemName = new JTextField(20);
		JLabel priceLabel = new JLabel("Enter Starting Bid: ");
		final JTextField itemPrice = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(itemName);
		pricePanel.add(priceLabel);
		pricePanel.add(itemPrice);
		JButton add = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		buttonPanel.add(add);
		buttonPanel.add(cancel);
		addItem.add(namePanel, BorderLayout.NORTH);
		addItem.add(pricePanel, BorderLayout.CENTER);
		addItem.add(buttonPanel, BorderLayout.SOUTH);
		addItem.setVisible(true);
		addItem.pack();
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (itemName.getText() != null && itemPrice.getText() != null)
				myAuction.addItem(itemName.getText(), Float.parseFloat(itemPrice.getText()));
				ArrayList<Item> itemList = (ArrayList<Item>) myAuction.getItems();
				model.addElement(itemList.get(itemList.size()-1));
				myFrame.pack();
				addItem.dispose();
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItem.dispose();
			}
		});
	}
	
	/**
	 * Activates dialog to remove the selected Item.
	 * @param model The model of Items.
	 * @param list The list of Items.
	 */
	private void removeItemWindow(DefaultListModel<Item> model, JList<Item> list) {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected Item?");
		if (dialogResult == JOptionPane.YES_OPTION) {
			model.remove(list.getSelectedIndex());
			myFrame.pack();
		}
	}
	
	/**
	 * Sets up and displays the window to add a Bidder to the
	 * current Auction.
	 * @param model the list of current Bidders
	 */
	private void addBidderWindow(final DefaultListModel<Bidder> model) {
		final JFrame addBidder = new JFrame("Add Bidder");
		JPanel namePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(namePanel);
		JLabel nameLabel = new JLabel("Enter Bidder Name: ");
		final JTextField bidderName = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(bidderName);
		JButton add = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		buttonPanel.add(add);
		buttonPanel.add(cancel);
		addBidder.add(scrollPane, BorderLayout.NORTH);
		addBidder.add(buttonPanel, BorderLayout.SOUTH);
		addBidder.setVisible(true);
		addBidder.pack();
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bidderName.getText() != null)
				myAuction.addBidder(bidderName.getText());
				ArrayList<Bidder> bidderList = (ArrayList<Bidder>) myAuction.getBidders();
				model.addElement(bidderList.get(bidderList.size()-1));
				myFrame.pack();
				addBidder.dispose();
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBidder.dispose();
			}
		});
	}
	
	/**
	 * Activates dialog to remove the selected Bidder.
	 * @param model The model of Bidders.
	 * @param list The list of Bidders.
	 */
	private void removeBidderWindow(DefaultListModel<Bidder> model, JList<Bidder> list) {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected Item?");
		if (dialogResult == JOptionPane.YES_OPTION) {
			model.remove(list.getSelectedIndex());
			myFrame.pack();
		}
	}

}
