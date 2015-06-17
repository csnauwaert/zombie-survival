package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.repositories.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class HumanServiceImpl implements HumanService {

	@Autowired
	private HumanRepository humanRepository;
	
	@Override
	public Human create(Crew crew, Location loc) {
		String name = "John Doe";
		Human human = new Human(name,crew,loc);
		return humanRepository.save(human);
	}

	@Override
	public void delete(Human human) {
		// TODO Auto-generated method stub

	}
	
	@Override
	@Transactional
	public boolean change_crew(Human human, Crew crew) {
		human.setCrew(crew);
		return true;
	}

}
