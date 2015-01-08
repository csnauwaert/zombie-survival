package org.kerwyn.game.service;

import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.AuthorityRepository;
import org.kerwyn.game.repositories.CrewRepository;
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
	private CrewRepository crewRepository;

	@Override
	public User save(User user) {
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
		crewRepository.save(new Crew(user));
		return user;

	}

}