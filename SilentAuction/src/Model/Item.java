/*
 * @author Elliot Ideguchi
 * @version 5/26/2015
 */
package Model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

public class Item {
	private String name;
	private String description;
	private Icon itemImage;
	private int id;
	private float currentBid;
	private float startBid;
	private float retail;
	private Map<Float, Bidder> bids;
	
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
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public float getCurrentBid() {
		return currentBid;
	}
	
	public float getStartBid() {
		return startBid;
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
		return "Item: " + name; //+ "\nCurrent Bid: " + currentBid + "\n";
	}
}
