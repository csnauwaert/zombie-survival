package org.kerwyn.game.travel;

import org.kerwyn.game.entities.Zombie;
import org.springframework.data.repository.CrudRepository;

/**
 * The Class ZombieTravelResolver.
 */
public class ZombieTravelResolver implements TravelResolver {
	
	private CrudRepository<Zombie, Long> zombieRepo;

	public ZombieTravelResolver(CrudRepository<Zombie, Long> newZombieRepo) {

		this.zombieRepo=newZombieRepo;
	
	}

	/* (non-Javadoc)
	 * @see org.kerwyn.game.travel.TravelResolver#resolve(java.lang.Integer)
	 */
	@Override
	public Zombie resolve(final Long beingId, final String destination) {

		return null;
	}

}
