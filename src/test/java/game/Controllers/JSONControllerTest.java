package game.Controllers;

import static org.junit.Assert.*;
import game.Greeting;

import org.junit.Test;

public class JSONControllerTest {

	@Test
	public void testReturnValue() {

		JSONController jsonController = new JSONController();
		Greeting greeting = jsonController.testrpc("name");

		assertEquals(1, greeting.getId());
		assertEquals("name", greeting.getName());

	}

}
