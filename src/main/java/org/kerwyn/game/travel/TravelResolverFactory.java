package org.kerwyn.game.travel;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Zombie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating TravelResolver objects.
 */
public class TravelResolverFactory {

	@Autowired
	private CrudRepository<Human, Long> humanRepo;
	@Autowired
	private CrudRepository<Zombie, Long> zombieRepo;

	/**
	 * Gets the single instance of TravelResolverFactory.
	 *
	 * @param beingType
	 *            the being type
	 * @return single instance of TravelResolverFactory
	 */
	public TravelResolver getInstance(final BeingType beingType) {

		return beingType.equals(BeingType.HUMAN) ? new HumanTravelResolver(humanRepo)
				: new ZombieTravelResolver(zombieRepo);

	}

}
