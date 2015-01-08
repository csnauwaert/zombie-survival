package org.kerwyn.game.service;

import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.AuthorityRepository;
import org.kerwyn.game.repositories.UserRepository;
import org.kerwyn.game.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private CrewService crewService;

	@Override
	public User create(User user) {
		User existing_user = userRepository.findOneByUsername(user
				.getUsername());

		if (existing_user != null || StringUtils.isEmpty(user.getUsername())
				|| StringUtils.isEmpty(user.getPassword())) {
			throw new UserAlreadyExistsException(String.format(
					"There already exists a user with username=%s",
					user.getUsername()));
		}
		authorityRepository
				.save(new Authority(user.getUsername(), "ROLE_USER"));
		userRepository.save(user);
		//Upon creation of new user, should create a new Crew for him
		this.add_crew(user);
		return user;

	}

	@Override
	public void delete(User user) {
		userRepository.delete(user.getId());
	}

	@Override
	public boolean enable(boolean enable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean change_auth_level(User user, String auth_level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String change_password(User user, String old_password,
			String new_password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String change_pseudo(User user, String new_pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Crew add_crew(User user) {
		return crewService.create(user);
	}

	@Override
	public void remove_crew(User user, Crew crew) {
		// TODO Auto-generated method stub
	}

}