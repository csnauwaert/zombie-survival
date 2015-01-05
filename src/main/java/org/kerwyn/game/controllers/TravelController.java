package org.kerwyn.game.controllers;

import org.kerwyn.game.entities.BeingType;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.travel.TravelResolver;
import org.kerwyn.game.travel.TravelResolverFactory;
import org.kerwyn.game.travel.TravelValidator;
import org.kerwyn.game.travel.TravelValidatorException;
import org.kerwyn.game.travel.TravelValidatorFactory;
import org.kerwyn.game.utils.BeingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * 
 * Controller which will calculate travel time from a point A to a point B
 * according the following specification.<br/>
 * <ul>
 * <li>Travel time could be modified by the weight a character wears.</li>
 * </ul>
 * 
 * @author sseron
 *
 */
@RestController
public class TravelController {

	/** The travel validator factory. */
	@Autowired
	private TravelValidatorFactory travelValidatorFactory;

	/** The travel resolver factory. */
	@Autowired
	private TravelResolverFactory travelResolverFactory;

	/** The being utils. */
	@Autowired
	private BeingUtils beingUtils;

	/**
	 * Shift the character.
	 *
	 * @param beingId
	 *            the being id
	 * @param location
	 *            the location
	 * @return the object
	 * @throws TravelControllerException
	 *             the travel controller exception
	 */
	@RequestMapping(value = "/game/travel", method = RequestMethod.POST)
	public Float travel(
			@RequestParam(value = "being", required = true) final Long beingId,
			@RequestParam(value = "location", required = true) final Integer location)
			throws TravelControllerException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		final BeingType beingType = this.beingUtils
				.getBeingType((User) authentication.getPrincipal());
		final TravelValidator validator = this.travelValidatorFactory
				.getInstance(beingType);
		final TravelResolver resolver = this.travelResolverFactory
				.getInstance(beingType);

		try {
			validator.validate(beingId, location);
		} catch (TravelValidatorException validatorException) {
			throw new TravelControllerException(validatorException);
		}

		return resolver.resolve(beingId, location);
	}
}
