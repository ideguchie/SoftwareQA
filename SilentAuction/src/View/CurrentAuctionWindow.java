package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Model.Auction;
import Model.Bidder;
import Model.Item;

public class CurrentAuctionWindow {
	
	JFrame myFrame;
	Auction myAuction;
	
	public CurrentAuctionWindow(Auction auction) {
		myFrame = new JFrame("Auction List");
		myAuction = auction;
		setup();
		
	}
	
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
		
	}
	
	private JPanel createBidderPane() {
		final JTabbedPane pane = new JTabbedPane();
		JPanel panel = new JPanel();
		final ArrayList<Bidder> biddersList = (ArrayList<Bidder>) myAuction.getBidders();
		final JList<String> bidders = new JList(biddersList.toArray());
		JButton select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!bidders.isSelectionEmpty())
					bidderInfoWindow(biddersList.get(bidders.getSelectedIndex()));
				else
					JOptionPane.showMessageDialog(pane, "A Bidder must be selected.");
			}
		});
		panel.add(select);
		panel.add(bidders);
		return panel;
		
	}
	
	private JPanel createItemPane() {
		final JTabbedPane pane = new JTabbedPane();
		JPanel panel = new JPanel();
		final ArrayList<Item> itemList = (ArrayList<Item>) myAuction.getItems();
		final JList<String> items = new JList(itemList.toArray());
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
		JButton back = createBackButton();
		panel.add(items, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		panel.add(select, BorderLayout.PAGE_END);
		panel.add(back, BorderLayout.PAGE_END);
		return panel;
		
	}
	
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
		panel.add(winners);
		panel.add(select);
		return panel;
		
	}
	
	private void bidderInfoWindow(Bidder bidder) {
		final JFrame bidderInfo = new JFrame("Bidder Information");
		bidderInfo.setLayout(new BoxLayout(bidderInfo, BoxLayout.PAGE_AXIS));
		JLabel name = new JLabel(bidder.toString());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bidderInfo.dispose();
			}
		});
		bidderInfo.add(name);
		bidderInfo.add(back);
		bidderInfo.setVisible(true);
		bidderInfo.pack();
	}
	
	private void itemInfoWindow(Item item) {
		final JFrame itemInfo = new JFrame("Item Information");
		itemInfo.setLayout(new BoxLayout(itemInfo, BoxLayout.PAGE_AXIS));
		JLabel name = new JLabel(item.toString());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemInfo.dispose();
			}
		});
		itemInfo.add(name);
		itemInfo.add(back);
		itemInfo.setVisible(true);
		itemInfo.pack();
	}
	
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

}
