package org.kerwyn.game.service;

import org.apache.log4j.Logger;
import org.kerwyn.game.config.GameConfig;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class HumanServiceImpl implements HumanService {

	@Autowired
	private HumanRepository humanRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private GameConfig config;
	
	private static int counter = 0;
	
	private Logger log = Logger.getLogger(HumanService.class);
	
	@Override
	public Human create(Crew crew, Location loc) {
		String name = "Human "+Integer.toString(counter);
		log.info(String.format("Creating human with name: %s (crew: %d)", name, crew.getId()));
		counter += 1;
		Human human = new Human(name, crew, loc, false,
				config.getHumanStartCarryCapacity(),
				config.getHumanStartConsumeFoodLevel(),
				config.getHumanStartMaxNumberInjury());
		
//		Skill skill = new Skill("Fireball");
		
//		human.addSkill(skill);
//		humanRepository.save(human);
//		skill.addHuman(human);
		humanRepository.save(human);
//		skillRepository.save(skill);
		return human;
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
