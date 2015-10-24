package org.kerwyn.game.service;

import java.util.Set;

import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.Item;

public interface LocationService {
	
	public Set<Item> getLocationLoot(Location tile);
	public Set<Human> getLocationHuman(Location tile);
	public Location findEmptyLocation();
	public boolean createMap(int width, int height);
	public boolean loadMap(String file);

}
