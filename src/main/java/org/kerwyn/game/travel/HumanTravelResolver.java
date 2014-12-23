package org.kerwyn.game.travel;

import org.kerwyn.game.entities.Human;
import org.springframework.data.repository.CrudRepository;

public class HumanTravelResolver implements TravelResolver {
	
	private CrudRepository<Human, Long> humanRepo;
	

	public HumanTravelResolver(CrudRepository<Human, Long> newHumanRepo) {

		this.humanRepo=newHumanRepo;
	
	}

	@Override
	public Float resolve(final Long beingId, final Integer destination) {
		// TODO Auto-generated method stub
		return null;
	}

}
