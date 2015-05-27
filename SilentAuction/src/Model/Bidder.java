/*
 * @author Elliot Ideguchi
 * @version 5/26/2015
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class Bidder {
	List<Item> items;
	int id;
	String name;
	
	public Bidder(String name) {
		items = new ArrayList<>();
		id = 0;
		this.name = name;
	}
	
	public void placeBid(Item item) {
		//TODO: this method places a bid on an item.
	}
	
	/*
	 * @Override
	 * Returns the Bidder information.
	 * @return Bidder information.
	 */
	public String toString() {
		return "Name: " + name + "\n";
	}
}
