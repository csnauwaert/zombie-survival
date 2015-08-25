package org.kerwyn.game.scheduler;

import org.apache.log4j.Logger;
import org.kerwyn.game.service.LocationService;
import org.kerwyn.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTasks {

	private static final Logger log = Logger.getLogger(ScheduledTasks.class);

	private static boolean needToRunStartupMethod = true;

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;

	@Scheduled(fixedRate = 3600000)
	public void keepAlive() {
		//log "alive" every hour for sanity checks
		log.debug("alive");
		if (needToRunStartupMethod) {
			runOnceOnlyOnStartup();
			needToRunStartupMethod = false;
		}
	}

	public void runOnceOnlyOnStartup() {
		log.info("Running startup job");
		//Important, location should be loaded first since creating user requires a location
		locationService.loadMap("map.xml");
		userService.createAdmin();
	}

}
