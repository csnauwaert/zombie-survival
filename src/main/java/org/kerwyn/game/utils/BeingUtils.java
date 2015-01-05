package org.kerwyn.game.utils;

import javax.validation.constraints.NotNull;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class BeingUtils.
 */
@Component
public class BeingUtils {

	/**
	 * Checks if is human.
	 *
	 * @param user the user
	 * @return true, if is human
	 */
	public BeingType getBeingType(@NotNull final User user) {

		return BeingType.HUMAN;

	}

}
