/*
 * @author Elliot Ideguchi
 * @version 5/26/2015
 */
package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

public class Item {
	String name;
	Icon itemImage;
	String description;
	float currentBid;
	float startBid;
	float retail;
	List<Bidder> bids;
	
	public Item() {
		name = "item";
		itemImage = null;
		description = "description";
		currentBid = 1.00f;
		startBid = 1.00f;
		retail = 1.00f;
		bids = new ArrayList<>();
	}
	
	public void updateBid() {
		//TODO: this method updates the current bid
	}
	
	public Bidder getWinner() {
		//TODO: this method returns the winner of the item
		return null;
	}
}
