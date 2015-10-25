package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Item;
import org.kerwyn.game.entities.Location;

public interface ItemService {

	public Item create(String name, int quantity, Location loc, Crew ownership, Human human, boolean equiped);
	public Item findFoodInInventory(Human human);
	public void delete(Item item);
}
