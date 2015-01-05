package org.kerwyn.game.travel;

import org.kerwyn.game.entities.BeingType;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TravelValidator objects.
 */
@Component
public class TravelValidatorFactory {

	/**
	 * Gets the single instance of TravelValidatorFactory.
	 *
	 * @param beingType the being type
	 * @return single instance of TravelValidatorFactory
	 */
	public TravelValidator getInstance(final BeingType beingType) {

		return beingType.equals(BeingType.HUMAN) ? new HumanTravelValidator()
				: new ZombieTravelValidator();

	}

}
