package org.kerwyn.game.service;

import org.kerwyn.game.entities.User;

public interface UserService {

	User create(User user);
	void delete(User user);
	void enable(User user, boolean enable);
	void change_auth_level(User executor, User user, String auth_level);
	User change_password(User user, String old_password, String new_password);
	User change_pseudo(User user, String new_pseudo);
	
}
