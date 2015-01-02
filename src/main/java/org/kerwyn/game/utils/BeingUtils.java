package org.kerwyn.game.utils;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.repositories.UserTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeingUtils {

	@Autowired
	private UserTempRepository userTempRepository;

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
