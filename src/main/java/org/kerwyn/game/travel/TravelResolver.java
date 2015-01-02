package org.kerwyn.game.travel;

/**
 * The Interface TravelResolver.
 */
public interface TravelResolver {

	/**
	 * Resolve the location, and update fields in database if needed...
	 *
	 * @param destination
	 *            the destination
	 * @return the float
	 */
	public Float resolve(final Long beingId, final Integer destination);

}
