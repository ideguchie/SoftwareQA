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
	
	public Bidder() {
		items = new ArrayList<>();
		id = 0;
		name = "name";
	}
	
	public void placeBid(Item item) {
		//TODO: this method places a bid on an item
	}
}
