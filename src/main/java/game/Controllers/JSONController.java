package game.Controllers;

import game.Greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONController {
	
	private int counter = 0;
	
	@RequestMapping("/testrpc")
    public Greeting testrpc(@RequestParam(value="name", required=false, defaultValue="") String name) {
        counter += 1;
        return new Greeting(counter,  ""+name);
    }

}
