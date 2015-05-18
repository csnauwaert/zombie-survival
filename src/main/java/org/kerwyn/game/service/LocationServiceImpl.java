package org.kerwyn.game.service;

import java.util.Set;

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
		return locationRepository.findOne(1L);
	}

	@Override
	@Transactional
	public boolean createMap(int width, int height) {
		//if we already has a record in location, that means that map exists
		//so do nothing.
		if (locationRepository.exists(1L)){
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
