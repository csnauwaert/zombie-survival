package org.kerwyn.game.travel;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating TravelResolver objects.
 */
@Component
public class TravelResolverFactory {

	@Autowired
	private HumanRepository humanRepo;
	@Autowired
	private ZombieRepository zombieRepo;

	/**
	 * Gets the single instance of TravelResolverFactory.
	 *
	 * @param beingType
	 *            the being type
	 * @return single instance of TravelResolverFactory
	 */
	public TravelResolver getInstance(final BeingType beingType) {

		return beingType.equals(BeingType.HUMAN) ? new HumanTravelResolver(
				humanRepo) : new ZombieTravelResolver(zombieRepo);

	}

}
