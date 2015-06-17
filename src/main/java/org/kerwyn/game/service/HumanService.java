package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;

public interface HumanService {
	
	Human create(Crew crew, Location loc);
	void delete(Human human);
	boolean change_crew(Human human, Crew crew);
}
