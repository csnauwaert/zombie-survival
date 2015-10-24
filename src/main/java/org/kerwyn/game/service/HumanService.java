package org.kerwyn.game.service;

import java.sql.Timestamp;

import org.kerwyn.game.config.Profession;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;

public interface HumanService {
	
	public Human create(Crew crew, Location loc);
	public void delete(Human human);
	public boolean changeCrew(Human human, Crew crew);
	/*
	 * moveToLocation(Human human, Location loc);
	 * moveToLocation(Human human, int x, int y);
	 * gainExp(Human human, String profession, int value);
	 * killForFood(Human human)
	 * equipItem(Human human, Item[] items);
	 * unequipItem(Human human, Item[] items);
	 * changeName(Human human, String name);
	 * setInjury(Human human, int injury);
	 * 
	 */
	// Add experience to the human current experience in the profession
	public Human addExperience(Human human, Long exp, Profession profession);
	// Enforce experience to the value given in parameter
	public Human setExperience(Human human, Long exp, Profession profession);
	
	//CRON jobs
	public void checkTurning(Timestamp last_sync);
	public void checkJobs(Timestamp last_sync);
}
