package org.kerwyn.game.service;

import org.apache.log4j.Logger;
import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.AuthorityRepository;
import org.kerwyn.game.repositories.UserRepository;
import org.kerwyn.game.service.exception.AuthorityLevelException;
import org.kerwyn.game.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private CrewService crewService;
	
	private Logger log = Logger.getLogger(UserService.class);

	@Override
	@Transactional
	public User create(User user) {
		log.info(String.format("Creating new user: %s", user.getUsername()));
		
		User existing_user = userRepository.findOneByUsername(user
				.getUsername());

		if (existing_user != null || StringUtils.isEmpty(user.getUsername())
				|| StringUtils.isEmpty(user.getPassword())) {
			log.debug(String.format("User %s already exists", user.getUsername()));
			throw new UserAlreadyExistsException(String.format(
					"There already exists a user with username=%s",
					user.getUsername()));
		}
		authorityRepository
				.save(new Authority(user.getUsername(), "ROLE_USER"));
		userRepository.save(user);
		//Upon creation of new user, should create a new Crew for him
		crewService.create(user);
		return user;

	}

	@Override
	@Transactional
	public void delete(User user) {
		log.info(String.format("Deleting user: %s", user.getUsername()));
		userRepository.delete(user.getId());
	}

	@Override
	@Transactional
	public void enable(User user, boolean enable) {
		log.info(String.format("Change status of player '%s' to %s" +
				" (true=active, false=inactive)", user.getUsername(), enable));
		user.setEnabled(enable);
	}

	@Override
	@Transactional
	public void change_auth_level(User executor, User user, String auth_level) {
		Authority player_auth = authorityRepository.findOneByUsername(user.getUsername());
		Authority executor_auth = authorityRepository.findOneByUsername(executor.getUsername());
		if (!executor_auth.canChangeAuthLevel()){
			log.info(String.format("Player '%s' has attempted to change is auth_level " +
					"from '%s' to '%s', but is not allowed to!", user.getUsername(),
					player_auth.getAuthority(), auth_level));
			throw new AuthorityLevelException("You cannot change your " +
					"authentication level, not enough access rights!");
		}
		log.info(String.format("Changing auth level of player '%s'" +
				" from '%s' to '%s'", user.getUsername(),
				player_auth.getAuthority(),
				auth_level));
		player_auth.setAuthority(auth_level);
	}

	@Override
	@Transactional
	public User change_password(User user, String old_password,
			String new_password) {
		if (user.getPassword().equals(old_password)){
			log.info(String.format("Changing password of player '%s'", user.getUsername()));
			user.setPassword(new_password);
		}
		else {
			log.debug(String.format("Attempt to change password of player '%s' (wrong password)", user.getUsername()));
			throw new AuthorityLevelException("Wrong password!");
		}
		return user;
	}

	@Override
	@Transactional
	public User change_pseudo(User user, String new_pseudo) {
		log.info(String.format("Changing player '%s' username from '%s' to '%s'", 
				user.getUsername(), user.getPseudo(), new_pseudo));
		user.setPseudo(new_pseudo);
		return user;
	}

}