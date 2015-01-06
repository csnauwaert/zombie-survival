package org.kerwyn.game.travel;

import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.LocationTime;
import org.kerwyn.game.entities.LocationTimeFactory;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.LocationRepository;
import org.kerwyn.game.repositories.LocationTimeRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class HumanTravelResolver.
 */
public class HumanTravelResolver implements TravelResolver {

	/** The human repo. */
	private final HumanRepository humanRepo;

	/** The location repository. */
	private final LocationRepository locationRepository;

	private final LocationTimeRepository locationTimeRepository;

	/**
	 * Instantiates a new human travel resolver.
	 *
	 * @param newHumanRepo
	 *            the new human repo
	 * @param newLocationRepository
	 *            the new location repository
	 */
	public HumanTravelResolver(final HumanRepository newHumanRepo,
			final LocationRepository newLocationRepository,
			final LocationTimeRepository newLocationTimeRepository) {

		this.humanRepo = newHumanRepo;
		this.locationRepository = newLocationRepository;
		this.locationTimeRepository = newLocationTimeRepository;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kerwyn.game.travel.TravelResolver#resolve(java.lang.Long,
	 * java.lang.Integer)
	 */
	@Override
	public Human resolve(final Long beingId, final Integer destination) {
		final Human current = this.humanRepo.findOne(beingId);
		final Location destinationLocation = locationRepository
				.findOneByCoordinate(destination);
		current.setCurrentLocation(destinationLocation);
		final LocationTime locationTime = LocationTimeFactory.getInstance(
				destinationLocation, current.getCrew());

		locationTimeRepository.save(locationTime);

		return current;
	}

}
