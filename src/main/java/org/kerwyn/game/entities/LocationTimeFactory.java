package org.kerwyn.game.entities;

import java.util.Calendar;

/**
 * A factory for creating LocationTime objects.
 */
public final class LocationTimeFactory {

	/**
	 * Gets the single instance of LocationTimeFactory.
	 *
	 * @param location the location
	 * @param crew the crew
	 * @return single instance of LocationTimeFactory
	 */
	public static LocationTime getInstance(final Location location,
			final Crew crew) {

		return new LocationTime(crew, location.getCoordinate(), Calendar
				.getInstance().getTime(), location.getLoots(),
				location.getBuildings(), location.getHumans(),
				location.getZombies());

	}

}
