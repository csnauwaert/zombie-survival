package org.kerwyn.game.service;

import org.kerwyn.game.entities.Authority;
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
		return userRepository.save(user);
	}

}
