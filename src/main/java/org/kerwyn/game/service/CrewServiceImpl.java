package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CrewServiceImpl implements CrewService {

	@Autowired
	private CrewRepository crewRepository;
	
	@Autowired
	private Environment env;
	
	@Override
	public Crew create(User user) {
		//Create crew
		Crew new_crew = crewRepository.save(new Crew(user));
		//Should create humans, basic loot, etc
		//Create basic human composing the crew
		int start_human = Integer.parseInt(env.getProperty("crew.start_human"));
		for (int i=0; i<start_human; i++){
			//Create human
			
		}
		return new_crew;
	}

}
