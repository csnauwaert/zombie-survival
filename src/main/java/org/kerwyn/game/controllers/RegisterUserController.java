package org.kerwyn.game.controllers;


import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.UserRepository;
import org.kerwyn.game.service.UserService;
import org.kerwyn.game.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/register_user", method=RequestMethod.POST)
	public User createNewUser(
			@RequestParam(value = "username", required = true) String username, 
			@RequestParam(value= "password", required = true) String password) {
		return userService.create(new User(username, password, true, username));
	}
	
	@RequestMapping(value = "/game/delete_user", method=RequestMethod.POST)
	public boolean deleteUser(){
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userRepository.findOneByUsername(authentication.getName());
		if (user != null) {
			userService.delete(user);
			//TODO Should perform a logout
			return true;
		}
		else {
			return false;
		}
	}
	
	@ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }
}
