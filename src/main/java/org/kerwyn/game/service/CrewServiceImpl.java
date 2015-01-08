package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CrewServiceImpl implements CrewService {

	@Autowired
	private CrewRepository crewRepository;
	
	@Override
	public Crew create(User user) {
		return crewRepository.save(new Crew(user));
		//Should create humans, basic loot, etc
	}

}
