package org.kerwyn.game.travel;

/**
 * The Interface TravelValidator.
 */
public interface TravelValidator {

	/**
	 * Validate.
	 *
	 * @param destination
	 *            the destination
	 * @throws TravelException
	 *             the travel exception
	 */
	public void validate(final Long beingId, final Integer destination)
			throws TravelException;

}
