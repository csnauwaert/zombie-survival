package org.kerwyn.game.travel;

import org.kerwyn.game.repositories.LocationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class HumanTravelValidator.
 */
public class HumanTravelValidator implements TravelValidator {

	private final static Long NOT_EXIST = 0L;

	/** The location repository. */
	private final LocationRepository locationRepository;

	/**
	 * Instantiates a new human travel validator.
	 *
	 * @param newLocationRepository
	 *            the new location repository
	 */
	public HumanTravelValidator(final LocationRepository newLocationRepository) {
		this.locationRepository = newLocationRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kerwyn.game.travel.TravelValidator#validate(java.lang.Long,
	 * java.lang.Integer)
	 */
	@Override
	public void validate(Long beingId, Integer destination)
			throws TravelException {

		final Long countByCoordinate = locationRepository
				.countByCoordinate(destination);
		if (countByCoordinate.equals(NOT_EXIST)) {
			throw new TravelException("The destination does not exists.");
		}

	}

}
