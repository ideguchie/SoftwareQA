/*
 * @author Elliot Ideguchi
 * @version 5/26/2015
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;

public class Auction {
	private List<Bidder> bidders;
	private List<Item> items;
	private Timer timer;
	private String name;
	
	public Auction(String name) {
		bidders = new ArrayList<>();
		items = new ArrayList<>();
		timer = new Timer();
		this.name = name;
		try {
			populateAuction();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				Path biddersFile = Paths.get(System.getProperty("user.dir") + "/My_Auction/" + name + "/Bidders.txt");
				Files.createFile(biddersFile);
				Path itemsFile = Paths.get(System.getProperty("user.dir") + "/My_Auction/" + name + "/Items.txt");
				Files.createFile(itemsFile);
			} catch (IOException ex) {
				e.printStackTrace();
			}	
		}
	}
	
	/*
	 * Adds a Bidder to the Auction.
	 */
	public void addBidder(Bidder bidder) {
		bidders.add(bidder);
	}
	
	/*
	 * Creates a Bidder and adds it to the Auction.
	 */
	public void addBidder(String name) {
		Bidder bidder = new Bidder(name);
		bidders.add(bidder);
	}
	
	/*
	 * Adds an Item to the Auction.
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/*
	 * Creates an Item and adds it to the Auction.
	 */
	public void addItem(String name, float startBid) {
		Item item = new Item(name, startBid);
		items.add(item);
	}
	
	public void removeBidder(int index) {
		bidders.remove(index);
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	/*
	 * Returns the list of Bidders in the Auction.
	 * @return List of Bidders.
	 */
	public List<Bidder> getBidders() {
		return bidders;
	}
	
	/*
	 * Returns the list of Items in the Auction.
	 * @return List of Items.
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/*
	 * Returns the winners of the Auction.
	 * @return Winning Bidders of the Auction.
	 */
	public List<Bidder> getWinners() {
		List<Bidder> winners = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			winners.add(items.get(i).getWinner());
		}
		return winners;
	}
	
	/*
	 * Prints the Auction information to the console. (For testing purposes)
	 */
	public void print() {
		for(int i = 0; i < items.size(); i++) {
			System.out.print(items.get(i).toString());
		}
		for(int i = 0; i < bidders.size(); i++) {
			System.out.print(bidders.get(i).toString());
		}
	}
	
	public void closeAuction() {
		//TODO: this method saves the Auction and stores the data in a database
		
//		for(int i = 0; i < bidders.size(); i++) {
//			List<Item> items = bidders.get(i).getItems();
//			write: bidders.get(i).getName(), bidders.get(i).getID(), items;
//		}
	}
	
	/*
	 * Retrieves data from database when auction loads
	 */
	private void populateAuction() throws IOException{
		//TODO: this method retrieves data from database when auction loads
		String dir = System.getProperty("user.dir") + "/My_Auctions/";
		BufferedReader bidders = new BufferedReader(new FileReader(dir + name + "/Bidders.txt"));
		try {
			String line = bidders.readLine();
			while(line != null) {
				addBidder(line);
				line = bidders.readLine();
			}
		} finally {
			bidders.close();
		}
		BufferedReader items = new BufferedReader(new FileReader(dir + name + "/Items.txt"));
		try {
			String line = items.readLine();
			while(line != null) {
				StringTokenizer tok = new StringTokenizer(line,",");
				String name = tok.nextToken();
				String start = tok.nextToken();
				addItem(name, Float.valueOf(start));
				line = items.readLine();
			}
		} finally {
			items.close();
		}
	}
}
