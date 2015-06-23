package org.kerwyn.game.tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kerwyn.game.Launcher;
import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.Skill;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.map.Map;
import org.kerwyn.game.repositories.AuthorityRepository;
import org.kerwyn.game.repositories.CrewRepository;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.LocationRepository;
import org.kerwyn.game.repositories.SkillRepository;
import org.kerwyn.game.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Launcher.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class EntityTest {
	/***
	 * This test case is used to check that upon creating-deleting certain entity,
	 * the state in jpa is correct afterwards
	 * ex: deleting a user should also delete the crew associated with it
	 * adding a human to a crew should mean that crew.getHumans and human.getCrew both work
	 */
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	User user;
	Authority auth;
	Crew crew;
	Crew crew2;
	Location loc;
	Human human;
	Human human2;
	Skill skill;
	Skill skill2;
	
	@Before
	public void setUp() {
		//The creation and persistence of object must follow a given order
		//User must be create and save to db before we can create authority
		//object, otherwise we will get an error saying that we are trying to
		//reference a transient model
		
		//Create User
		user = new User("test", "password", true, "testPlayer");
		userRepository.save(user);
		//Authority for user
		auth = new Authority(user, "ROLE_TEST");
		authorityRepository.save(auth);
		//Create 2 crews for user
		crew = new Crew(user);
		crew2 = new Crew(user);
		crewRepository.save(crew);
		crewRepository.save(crew2);
		//Create a location
		loc = new Location(Map.convertCoord(1, 1),1);
		locationRepository.save(loc);
		//Create a human and a skill, save the skill then assign it to human
		//And save the human afterwards
		human = new Human("TestHuman", crew, loc);
		skill = new Skill("TestSkill");
		skillRepository.save(skill);
		human.addSkill(skill);
		humanRepository.save(human);
		//Create a secondary skill that we assign on the same human
		skill2 = new Skill("TestSkill2");
		skillRepository.save(skill2);
		skill2.addHuman(human);
		//Create a secondary human
		human2 = new Human("TestHuman2", crew, loc);
		humanRepository.save(human2);
		human2.addSkill(skill);
		human2.addSkill(skill2);
	}
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
		authorityRepository.deleteAll();
		crewRepository.deleteAll();
		humanRepository.deleteAll();
		skillRepository.deleteAll();
		locationRepository.deleteAll();
	}
	
	@Test
	public void testBiderectionalLink() {
		//Test the one2one link between user and auth
		assertNotNull("Link between Authority and User not correctly set", auth.getUser());
		assertNotNull("Link between User and Authority not correctly set", user.getAuthority());
		//Test the one2many link and many2one link between user and crew
		assertTrue("Link between User and Crew not correctly set", user.getCrew().contains(crew));
		assertTrue("Link between User and Crew not correctly set", user.getCrew().contains(crew2));
		assertNotNull("Link between Crew and User not correctly set", crew.getUser());
		//Test the o2m and m2o link between crew and human
		assertTrue("Link between Crew and Human not correctly set", crew.getHumans().contains(human));
		assertTrue("Link between Crew and Human not correctly set", crew.getHumans().contains(human2));
		assertNotNull("Link between Human and Crew not correctly set", human.getCrew());
		//Test the many2many link between human and skills
		assertTrue("Link between Human and Skills not correctly set", human.getSkills().contains(skill));
		assertTrue("Link between Human and Skills not correctly set", human.getSkills().contains(skill2));
		assertTrue("Link between Skills and Humans not correctly set", skill.getHumans().contains(human));
		assertTrue("Link between Skills and Humans not correctly set", skill2.getHumans().contains(human));
	}
	
	@Test
	@Transactional
	public void testDeleteLink() {
		//Test the many2many delete (if a skill is deleted, it should be remove from human skill list
		//and only first skill should remains)
		skillRepository.delete(skill2);
		assertTrue("Human should not has a link to a deleted Skill entity", human.getSkills().contains(skill));
		assertTrue("Human should not has a link to a deleted Skill entity", human2.getSkills().contains(skill));
		assertEquals("Human should not has a link to a deleted Skill entity", 1, human.getSkills().size());
		assertEquals("Human should not has a link to a deleted Skill entity", 1, human2.getSkills().size());
		//Add new skill to human2 and delete human2 and see if link has been removed correctly from skill
		Skill newSkill = new Skill("SkillTest3");
		skillRepository.save(newSkill);
		newSkill.addHuman(human2);
		assertTrue("New skill should have been added to the human", human2.getSkills().contains(newSkill));
		assertEquals("New skill should have been added to the human", 2, human2.getSkills().size());
		humanRepository.delete(human2);
		assertEquals("Skill should not have link to a deleted Human entity", 0, newSkill.getHumans().size());
		assertTrue("Skill should not have link to a deleted Human entity", skill.getHumans().contains(human));
		assertEquals("Skill should not have link to a deleted Human entity", 1, skill.getHumans().size());
		//Check that crew was not deleted with human2 and that crew.getHuman is correct
		assertTrue("Crew should not have a link to a deleted Human entity", crew.getHumans().contains(human));
		assertEquals("Crew should not have a link to a deleted Human entity", 1, crew.getHumans().size());
		//Now delete the crew and check that human has been deleted with it
		crewRepository.delete(crew);
		
	}

}
