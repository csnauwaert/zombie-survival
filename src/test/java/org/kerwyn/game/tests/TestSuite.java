package org.kerwyn.game.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.kerwyn.game.Launcher;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EntityTest.class,
	UserTest.class
})
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Launcher.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TestSuite {
}
