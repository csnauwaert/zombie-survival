package org.kerwyn.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerGreeting {
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String index(Model model) {
        // add a variable named "name" to the view-model:
//        model.addAttribute("name", "World");
        return "home";
    } 

}