package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.User;

public interface UserService {

	User create(User user);
	void delete(User user);
	void enable(User user, boolean enable);
	void change_auth_level(User user, String auth_level);
	String change_password(User user, String old_password, String new_password);
	String change_pseudo(User user, String new_pseudo);
	Crew add_crew(User user);
	void remove_crew(User user, Crew crew);
	
}
