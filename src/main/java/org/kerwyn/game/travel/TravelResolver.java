package org.kerwyn.game.travel;

import org.kerwyn.game.entities.Being;

/**
 * The Interface TravelResolver.
 */
public interface TravelResolver {

	/**
	 * Resolve the location, and update fields in database if needed...
	 * 
	 * Update the current location of a being.
	 * Update the location time of a being.
	 * Update the location time of the crew.
	 *
	 * @param destination
	 *            the destination
	 * @return the float
	 */
	public Being resolve(final Long beingId, final String destination);

}
