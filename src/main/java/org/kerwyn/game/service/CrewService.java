package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.service.exception.ConfigurationException;

public interface CrewService {
	
	Crew create(User user) throws NumberFormatException, ConfigurationException;

}
