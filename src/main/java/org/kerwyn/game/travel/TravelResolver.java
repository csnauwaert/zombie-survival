package org.kerwyn.game.travel;

/**
 * The Interface TravelResolver.
 */
public interface TravelResolver {

	/**
	 * Resolve.
	 *
	 * @param destination
	 *            the destination
	 * @return the float
	 */
	public Float resolve(final Long beingId, final Integer destination);

}
