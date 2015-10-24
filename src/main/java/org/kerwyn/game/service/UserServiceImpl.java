package org.kerwyn.game.service;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.kerwyn.game.config.GameConfig;
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
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private CrewService crewService;
	
	@Autowired
	private GameConfig config;
	
	private Logger log = Logger.getLogger(UserService.class);

	@Override
	public User create(User user) {
		log.info(String.format("Creating new user: %s", user.getUsername()));
		
		User existing_user = userRepository.findOneByUsername(user.getUsername());

		if (existing_user != null || StringUtils.isEmpty(user.getUsername())
				|| StringUtils.isEmpty(user.getPassword())) {
			log.info(String.format("User %s already exists", user.getUsername()));
			throw new UserAlreadyExistsException(String.format(
					"There already exists a user with username=%s",
					user.getUsername()));
		}
		userRepository.save(user);
		authorityRepository.save(new Authority(user, "ROLE_USER"));
		//Upon creation of new user, should create a new Crew for him
		crewService.create(user);
		return user;

	}
	
	@Override
	public User createAdmin() {
		log.info("Creating admin user");
		if (userRepository.count() > 0){
			log.info("Admin user already exists!");
			return null;
		}
		User admin = this.create(new User(config.getAdminLogin(), config.getAdminPassword(), true, "Admin"));
		authorityRepository.findOneByUser(admin).setAuthority("ROLE_ADMIN");
		log.info("Admin user has been created");
		return admin;
	}

	@Override
	public void delete(User user) {
		log.info(String.format("Deleting user: %s", user.getUsername()));
		Authority auth = authorityRepository.findOneByUser(user);
		authorityRepository.delete(auth);
		userRepository.delete(user.getId());
	}

	@Override
	public void enable(User user, boolean enable) {
		log.info(String.format("Change status of player '%s' to %s" +
				" (true=active, false=inactive)", user.getUsername(), enable));
		user.setEnabled(enable);
	}

	@Override
	public Boolean changeAuthLevel(User executor, User user, String auth_level) {
		Authority player_auth = authorityRepository.findOneByUser(user);
		Authority executor_auth = authorityRepository.findOneByUsername(executor.getUsername());
		if (!executor_auth.canChangeAuthLevel()){
			log.info(String.format("User '%s' has attempted to change player '%s' auth_level " +
					"from '%s' to '%s', but is not allowed to!", executor.getUsername(),
					user.getUsername(), player_auth.getAuthority(), auth_level));
			throw new AuthorityLevelException("You cannot change your " +
					"authentication level, not enough access rights!");
		}
		log.info(String.format("Admin '%s' has changed auth level of player '%s'" +
				" from '%s' to '%s'", executor.getUsername(), user.getUsername(),
				player_auth.getAuthority(),
				auth_level));
		player_auth.setAuthority(auth_level);
		return true;
	}

	@Override
	public User changePassword(User user, String old_password,
			String new_password) {
		if (user.getPassword().equals(old_password)){
			log.info(String.format("Changing password of player '%s'", user.getUsername()));
			user.setPassword(new_password);
		}
		else {
			log.info(String.format("Attempt to change password of player '%s' (wrong password)", user.getUsername()));
			throw new AuthorityLevelException("Wrong password!");
		}
		return user;
	}

	@Override
	public User changePseudo(User user, String new_pseudo) {
		log.info(String.format("Changing player '%s' username from '%s' to '%s'", 
				user.getUsername(), user.getPseudo(), new_pseudo));
		user.setPseudo(new_pseudo);
		return user;
	}

	/**
	 * Method used to write login date in db
	 */
	@Override
	public User loginHook(User user) {
		log.info(String.format("Updating user %s last connection", user.getUsername()));
		Long cur_time = System.currentTimeMillis();
		user.setLastConnection(new Timestamp(cur_time));
		return user;
	}

}