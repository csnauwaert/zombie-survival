package org.kerwyn.game.service;

import java.util.Set;

import org.apache.log4j.Logger;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.Loot;
import org.kerwyn.game.map.Map;
import org.kerwyn.game.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	private Logger log = Logger.getLogger(LocationService.class);
	
	@Override
	public Set<Loot> getLocationLoot(Location tile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Human> getLocationHuman(Location tile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findEmptyLocation() {
		// TODO Auto-generated method stub
		// Should be fixed to find a correct empty location, for now return tile at 1-1
		return locationRepository.findOneByCoordinate(Map.convertCoord(1, 1));
	}

	@Override
	@Transactional
	public boolean createMap(int width, int height) {
		//if we already has a record in location, that means that map exists
		//so do nothing.
		log.info("Creating map");
		if (locationRepository.exists(1L)){
			log.info("Map already exists");
			return false;
		}
		for (int i=0; i<width;i++){
			for (int j=0; j<height; j++){
				String coord = Map.convertCoord(i, j);
				//save tile in database
				Location loc = new Location(coord, 1);
				locationRepository.save(loc);
			}
		}
		return true;
		
	}

}
