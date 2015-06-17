package org.kerwyn.game.controllers;


import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.CrewRepository;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.UserRepository;
import org.kerwyn.game.service.HumanService;
import org.kerwyn.game.service.UserService;
import org.kerwyn.game.service.exception.AuthorityLevelException;
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

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class RegisterUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HumanService humanService;
	
	@Autowired
	private HumanRepository humanRepository;
	
	@Autowired
	private CrewRepository crewRepository;

	@Autowired
	private UserRepository userRepository;
	
	@JsonView(View.UserBasicView.class)
	@RequestMapping(value = "/register_user", method=RequestMethod.POST)
	public User createNewUser(
			@RequestParam(value = "username", required = true) String username, 
			@RequestParam(value= "password", required = true) String password) {
		return userService.create(new User(username, password, true, username));
	}
	
	@RequestMapping(value = "/game/delete_user", method=RequestMethod.GET)
	public boolean deleteUser(){
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userRepository.findOneByUsername(authentication.getName());
		if (user != null) {
			userService.delete(user);
			//perform a logout
			SecurityContextHolder.getContext().setAuthentication(null);
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value = "/change", method=RequestMethod.GET)
	public boolean changecrew(
			@RequestParam(value = "human_id", required = true) Long human_id, 
			@RequestParam(value= "crew_id", required = true) Long crew_id) {
		Human human = humanRepository.findOne(human_id);
		Crew crew = crewRepository.findOne(crew_id);
		return humanService.change_crew(human, crew);
	}
	
//	@RequestMapping(value = "/game/change_password", method=RequestMethod.POST)
//	public User changePassword(){
		
//		return userService.change_password(user, old_password, new_password)
//	}
	
	
	//TODO: change exception handling system, just create generic exception with
	//a message inside, maybe one exception type per responseStatus?
	//NotFoundException, ConflictException, AccessRightException, ...
	// + Create a class genericController that just handle Exception and all other
	//controller should extend from that class
	@ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		return e.getMessage();
    }
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleAuthorityLevelException(AuthorityLevelException e) {
		return e.getMessage();
	}
}
