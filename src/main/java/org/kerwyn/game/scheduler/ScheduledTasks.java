package org.kerwyn.game.scheduler;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.kerwyn.game.entities.Cron;
import org.kerwyn.game.repositories.CronRepository;
import org.kerwyn.game.service.HumanService;
import org.kerwyn.game.service.LocationService;
import org.kerwyn.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@EnableScheduling
@Component
@Transactional
public class ScheduledTasks {

	private static final Logger log = Logger.getLogger(ScheduledTasks.class);

	private static boolean needToRunStartupMethod = true;

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private HumanService humanService;
	
	@Autowired
	private CronRepository cronRepository;
	
	private Timestamp logCronDate(String cronname) {
		log.debug(cronname +" cron running");
		Cron cron = cronRepository.findOneByName(cronname);
		Timestamp cur_timestamp = new Timestamp(System.currentTimeMillis());
		if (cron == null) {
			cron = new Cron(cronname, cur_timestamp);
			cronRepository.save(cron);
			return new Timestamp(0);
		}
		else {
			Timestamp last_execution = cron.getLast_execution();
			cron.setLast_execution(cur_timestamp);
			return last_execution;
		}
	}

	@Scheduled(fixedRate = 3600000)
	public void keepAlive() {
		//log "alive" every hour for sanity checks
		logCronDate("alive");
		if (needToRunStartupMethod) {
			runOnceOnlyOnStartup();
			needToRunStartupMethod = false;
		}
	}
	
	@Scheduled(initialDelay=10000, fixedRate = 60000)
	public void humansCron() {
		Timestamp last_sync = logCronDate("human");
		humanService.checkTurning(last_sync);
		humanService.checkJobs(last_sync);
	}
	
	@Scheduled(cron="0 0 12 * * ?")
	public void DailyCron() {
		logCronDate("daily");
		humanService.foodCron();
	}

	public void runOnceOnlyOnStartup() {
		logCronDate("startjob");
		//Important, location should be loaded first since creating user requires a location
		locationService.loadMap("map.xml");
		userService.createAdmin();
	}

}
