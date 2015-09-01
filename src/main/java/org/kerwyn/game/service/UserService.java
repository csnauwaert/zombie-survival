package org.kerwyn.game.service;

import org.kerwyn.game.entities.User;

public interface UserService {

	User create(User user);
	User createAdmin();
	User loginHook(User user);
	void delete(User user);
	void enable(User user, boolean enable);
	Boolean changeAuthLevel(User executor, User user, String auth_level);
	User changePassword(User user, String old_password, String new_password);
	User changePseudo(User user, String new_pseudo);
	
}
