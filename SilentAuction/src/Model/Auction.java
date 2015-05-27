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
	
	public void addBidder() {
		//TODO: this method adds bidders to the auction
	}
	
	public void addItem() {
		//TODO: this method adds items to the auction
	}
	
	public List<Bidder> getWinners() {
		//TODO: this method returns the list of winners
		return null;
	}
	
	private void populateAuction() {
		//TODO: this method retrieves data from database when auction loads
	}
	
}
