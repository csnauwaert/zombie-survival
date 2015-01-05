package org.kerwyn.game.utils;

import javax.validation.constraints.NotNull;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeingUtils {

	@Autowired
	private User user;

	/**
	 * Checks if is human.
	 *
	 * @param userId
	 *            the user id
	 * @return true, if is human
	 */
	public BeingType getBeingType(@NotNull final User user) {

		return BeingType.HUMAN;

	}

}
