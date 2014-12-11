package org.kerwyn.game.controllers;

import org.kerwyn.game.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONController {

	@RequestMapping("/testrpc")
	public Greeting testrpc(
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		return new Greeting(name);
	}

}
