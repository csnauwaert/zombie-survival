package org.kerwyn.game.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kerwyn.game.Launcher;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.LocationRepository;
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
	
	User admin;
	User player;
	
	@Before
    public void setUp() {
		//Create map
		locationService.createMap(10, 10);
        //create admin user
        admin = new User("admin","pwd",true,"administrator");
        admin = userService.create(admin);
        //give admin authority role
        admin.getAuthority().setAuthority("ROLE_ADMIN");
        //create player user
        player = new User("player", "pwd", true, "TestPlayer");
        player = userService.create(player);
    }
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
		locationRepository.deleteAll();
	}
	
	@Test
	public void TestChangeAuthLevel() {
		//Verify that admin and user starts with the correct authority
		assertEquals(admin.getAuthority().getAuthority(), "ROLE_ADMIN");
		assertEquals(player.getAuthority().getAuthority(), "ROLE_USER");
		//User that is not admin should not be able to change its role or someone else role
		try {
			userService.change_auth_level(player, player, "TEST_ROLE");
		} catch (AuthorityLevelException e) {}
		try {
			userService.change_auth_level(player, admin, "TEST_ROLE");
		} catch (AuthorityLevelException e) {}
		assertEquals(admin.getAuthority().getAuthority(), "ROLE_ADMIN");
		assertEquals(player.getAuthority().getAuthority(), "ROLE_USER");
		//User with correct authority can change someone else role
		userService.change_auth_level(admin, player, "ROLE_ADMIN");
		assertEquals(player.getAuthority().getAuthority(), "ROLE_ADMIN");
		//Newly user that has been granted authority can change it's own role
		userService.change_auth_level(player, player, "ROLE_USER");
		assertEquals(player.getAuthority().getAuthority(), "ROLE_USER");
	}
	
	@Test
	public void TestCreateExistingUser() {
		long count_users = userRepository.count();
		//Try creating a user with a name that already exists
		User existing_user = new User("admin", "somepwd", true, "TrueAdmin");
		try {
			userService.create(existing_user);
		} catch (UserAlreadyExistsException e) {}
		//User should have not been created
		assertEquals(userRepository.count(), count_users);
	}

}
