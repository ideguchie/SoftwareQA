/*
 * @author Elliot Ideguchi
 * @version 5/26/2015
 */
package Model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

public class Item {
	String name;
	String description;
	Icon itemImage;
	int id;
	float currentBid;
	float startBid;
	float retail;
	Map<Float, Bidder> bids;
	
	public Item(String name, float startBid) {
		this.name = name;
		this.itemImage = null;
		this.id = 0;
		this.description = "description";
		this.currentBid = startBid;
		this.startBid = startBid;
		this.retail = 1.00f;
		this.bids = new HashMap<>();
	}
	
	/*
	 * Updates the current bid and notifies bidders of the current bid.
	 */
	public void updateBid(Bidder bidder, float bid) {
		currentBid = bid;
		bids.put(bid, bidder);
	}
	
	/*
	 * This method returns highest bidder on the item.
	 * @return Winning Bidder.
	 */
	public Bidder getWinner() {
		return bids.get(currentBid);
	}
	
	/*
	 * @Override
	 * Returns the Item information.
	 * @return Item information.
	 */
	public String toString() {
		return "Item: " + name + "\nCurrent Bid: " + currentBid + "\n";
	}
}
