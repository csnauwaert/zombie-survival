package org.kerwyn.game.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kerwyn.game.Launcher;
import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.AuthorityRepository;
import org.kerwyn.game.repositories.CrewRepository;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.LocationRepository;
import org.kerwyn.game.repositories.SkillRepository;
import org.kerwyn.game.repositories.UserRepository;
import org.kerwyn.game.service.LocationService;
import org.kerwyn.game.service.UserService;
import org.kerwyn.game.service.exception.AuthorityLevelException;
import org.kerwyn.game.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Launcher.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private HumanRepository humanRepository;
	
	@Autowired
	private CrewRepository crewRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	User admin;
	Authority admin_auth;
	User player;
	Authority player_auth;
	
	@Before
    public void setUp() {
		//Create map
		locationService.loadMap("map.xml");
        //create admin user
        admin = new User("administrator","pwd",true,"administrator");
        admin = userService.create(admin);
        //give admin authority role
        admin_auth = authorityRepository.findOneByUser(admin);
        admin_auth.setAuthority("ROLE_ADMIN");
        authorityRepository.save(admin_auth);
        //create player user
        player = new User("player", "pwd", true, "TestPlayer");
        player = userService.create(player);
        player_auth = authorityRepository.findOneByUser(player);
    }
	
	@After
	public void tearDown() {
		authorityRepository.deleteAll();
		userRepository.deleteAll();
		crewRepository.deleteAll();
		humanRepository.deleteAll();
		skillRepository.deleteAll();
		locationRepository.deleteAll();
	}
	
	@Test
	public void TestChangeAuthLevel() {
		//Verify that admin and user starts with the correct authority
		assertEquals("Incorrect role for user admin", "ROLE_ADMIN", authorityRepository.findOneByUser(admin).getAuthority());
		assertEquals("Incorrect role for user player", "ROLE_USER", authorityRepository.findOneByUser(player).getAuthority());
		//User that is not admin should not be able to change its role or someone else role
		try {
			userService.changeAuthLevel(player, player, "TEST_ROLE");
		} catch (AuthorityLevelException e) {}
		try {
			userService.changeAuthLevel(player, admin, "TEST_ROLE");
		} catch (AuthorityLevelException e) {}
		assertEquals("Admin role should not have been changed", "ROLE_ADMIN", admin_auth.getAuthority());
		assertEquals("Player role should not have been changed", "ROLE_USER", player_auth.getAuthority());
		//User with correct authority can change someone else role
		userService.changeAuthLevel(admin, player, "ROLE_ADMIN");
		assertEquals("Player role should have changed", "ROLE_ADMIN", authorityRepository.findOneByUser(player).getAuthority());
		//Newly user that has been granted authority can change it's own role
		userService.changeAuthLevel(player, player, "ROLE_USER");
		assertEquals("Player role should have been changed", "ROLE_USER", authorityRepository.findOneByUser(player).getAuthority());
	}
	
	@Test
	public void TestCreateExistingUser() {
		long count_users = userRepository.count();
		//Try creating a user with a name that already exists
		User existing_user = new User("administrator", "somepwd", true, "TrueAdmin");
		try {
			userService.create(existing_user);
		} catch (UserAlreadyExistsException e) {}
		//User should have not been created
		assertEquals("User should not have been created", userRepository.count(), count_users);
	}

}
