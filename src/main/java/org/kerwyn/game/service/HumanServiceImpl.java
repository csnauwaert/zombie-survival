package org.kerwyn.game.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.kerwyn.game.config.GameConfig;
import org.kerwyn.game.config.Profession;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Item;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HumanServiceImpl implements HumanService {

	@Autowired
	private HumanRepository humanRepository;

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private ItemService itemService;

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
		
		Random rand = new Random();
		for (Profession profession : Profession.values()) {
			this.addExperience(human, (long)rand.nextInt(20), profession);
		}
		if (rand.nextInt(config.getHumanStartChanceInfected()) == 0){
			human.setInfected(true);
			Long addMillis = (long)(rand.nextInt(config.getHumanMaxTimeBeforeTurning() - config.getHumanMinTimeBeforeTurning()) + config.getHumanMinTimeBeforeTurning())*1000;
			Timestamp date_of_turning = new Timestamp(System.currentTimeMillis() + addMillis);
			human.setTimeOfTurning(date_of_turning);
		}
		humanRepository.save(human);
		itemService.create("Food", config.getHumanStartFood(), loc, crew, human, false);
		return human;
	}

	@Override
	public Human addExperience(Human human, Long exp, Profession profession) {
		Long new_xp = human.getExp(profession) + exp;
		if (new_xp < 0) { new_xp = 0L; }
		human.setExp(new_xp, profession);
		//TODO call method to check if we need to add/remove skills
		return human;
	}
	
	@Override
	public Human setExperience(Human human, Long exp, Profession profession) {
		if (exp < 0) { exp = 0L; }
		human.setExp(exp, profession);
		//TODO call method to check if we need to add/remove skills
		return human;
	}

	@Override
	public void delete(Human human) {
		humanRepository.delete(human.getId());
	}

	@Override
	public boolean changeCrew(Human human, Crew crew) {
		human.setCrew(crew);
		return true;
	}

	@Override
	public void checkTurning(Timestamp last_sync) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		List<Human> humans_to_transform = humanRepository.findByTimeOfTurningBetween(last_sync, currentTime);
		if (humans_to_transform.size() > 0) {
			for (Human human : humans_to_transform) {
				die(human);
				
				//TODO Alert user (if human is not on a job) and check if transformed human attacks friendly unit
			}
		}
	}

	@Override
	public void checkJobs(Timestamp last_sync) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		List<Human> humans_to_return = humanRepository.findByTimeOfReturnBetween(last_sync, currentTime);
		if (humans_to_return.size() > 0) {
			for (Human human : humans_to_return) {
				//TODO Alert user that human has returned from a job
				human.setAwayJob(null);
			}
		}
	}

	@Override
	public Double getEfficiency(Human human) {
		switch (human.getLastFoodConsume()) {
		case 3:
			return 1.0;
		case 2:
			return 0.75;
		case 1:
			return 0.35;
		case 0:
			return 0.1;
		}
		return 1.0;
	}
	
	@Override
	public void foodCron() {
		//Consume food + Write currentFoodConsume in LastFoodConsume + check if user should die of hunger
		//TODO optimize update since as it is, it will perform 2 updates per human (one for item qty and 
		//	the other for humans lastfoodconsume,currentfoodconsume,nbrdayswithoutfood
		List<Human> humans = humanRepository.findAllByDead(false);
		for (Human human : humans) {
			Item currentFood = itemService.findFoodInInventory(human);
			if (currentFood != null){
				if (human.getCurrentFoodConsume() > currentFood.getQuantity()) {
					human.setCurrentFoodConsume(currentFood.getQuantity());
				}
				currentFood.setQuantity(currentFood.getQuantity() - human.getCurrentFoodConsume());
				if (currentFood.getQuantity() == 0) {
					itemService.delete(currentFood);
				}
			}
			else {
				human.setCurrentFoodConsume(0);
			}
			human.setLastFoodConsume(human.getCurrentFoodConsume());
			if (human.getCurrentFoodConsume() == 0) {
				human.setNbrDaysWithoutFood(human.getNbrDaysWithoutFood() +1);
				if (human.getNbrDaysWithoutFood() == 3) {
					die(human);
				}
			}
			else {
				human.setNbrDaysWithoutFood(0);
			}
			
		}
		 
	}
	
	@Override
	public void die(Human human) {
		//TODO
		log.info(String.format("Human died: %s", human.getId()));
		human.setDead(true);
	}
	
	

}
