package org.kerwyn.game.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kerwyn.game.Greeting;
import org.kerwyn.game.controllers.JSONController;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JSONControllerTest {

	@Test
	public void testReturnValue() {

		JSONController jsonController = new JSONController();
		Greeting greeting = jsonController.testrpc("name");
		assertEquals("name", greeting.getName());
	}

}
