/*
 * @author Elliot Ideguchi
 * @version 5/26/2015
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Auction {
	List<Bidder> bidders;
	List<Item> items;
	Timer timer;
	
	public Auction() {
		bidders = new ArrayList<>();
		items = new ArrayList<>();
		timer = new Timer();
		//populateAuction();
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
	}
	
	private void populateAuction() {
		//TODO: this method retrieves data from database when auction loads
	}
	
}
