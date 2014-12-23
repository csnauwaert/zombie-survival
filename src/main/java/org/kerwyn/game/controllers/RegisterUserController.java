package org.kerwyn.game.controllers;

import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.AuthorityRepository;
import org.kerwyn.game.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected AuthorityRepository authorityRepository;

	@RequestMapping(value = "/register_user", method = RequestMethod.GET)
	public User createNewUser(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		authorityRepository.save(new Authority(username, "ROLE_USER"));
		return userRepository.save(new User(username, password, true));
	}
}
