package org.kerwyn.game.service;

import org.apache.log4j.Logger;
import org.kerwyn.game.config.Config;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.CrewRepository;
import org.kerwyn.game.service.exception.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CrewServiceImpl implements CrewService {

	@Autowired
	private CrewRepository crewRepository;
	
	@Autowired
	private HumanService humanService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private Config config;
	
	private Logger log = Logger.getLogger(CrewService.class);
	
	@Override
	@Transactional
	public Crew create(User user) throws NumberFormatException, ConfigurationException {
		//Create crew
		log.info(String.format("Creating new crew for user %s", user.getUsername()));
		Crew new_crew = crewRepository.save(new Crew(user));
		//Should create humans, basic loot, etc
		//Create basic human composing the crew
		int start_human = Integer.parseInt(config.get("crew.start_human"));
		Location starting_point = locationService.findEmptyLocation();
		log.debug(String.format("Starting point location at: %s", starting_point.getCoordinate()));
		for (int i=0; i<start_human; i++){
			//Create human
			humanService.create(new_crew, starting_point);
		}
		return new_crew;
	}

}
