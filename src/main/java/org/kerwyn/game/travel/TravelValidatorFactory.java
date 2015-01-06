package org.kerwyn.game.travel;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TravelValidator objects.
 */
@Component
public class TravelValidatorFactory {
	
	@Autowired
	private LocationRepository locationRepository;

	/**
	 * Gets the single instance of TravelValidatorFactory.
	 *
	 * @param beingType the being type
	 * @return single instance of TravelValidatorFactory
	 */
	public TravelValidator getInstance(final BeingType beingType) {

		return beingType.equals(BeingType.HUMAN) ? new HumanTravelValidator(locationRepository)
				: new ZombieTravelValidator();

	}

}
