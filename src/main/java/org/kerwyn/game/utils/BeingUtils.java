package org.kerwyn.game.utils;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.entities.UserTemp;
import org.springframework.data.repository.CrudRepository;

public class BeingUtils {

	private CrudRepository<UserTemp, Long> userTempRepository;

	/**
	 * Checks if is human.
	 *
	 * @param userId
	 *            the user id
	 * @return true, if is human
	 */
	public BeingType getBeingType(final Long userId) {

		return userTempRepository.findOne(userId).getCrew().getHumans()
				.isEmpty() ? BeingType.ZOMBIE : BeingType.HUMAN;

	}

}
