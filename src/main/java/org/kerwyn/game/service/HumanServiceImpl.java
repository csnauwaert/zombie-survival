package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.repositories.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HumanServiceImpl implements HumanService {

	@Autowired
	private HumanRepository humanRepository;
	
	@Override
	public Human create(Crew crew, Location loc) {
		// TODO Auto-generated method stub
		String name = "John Doe";
		
		return null;
	}

	@Override
	public void delete(Human human) {
		// TODO Auto-generated method stub

	}

}
