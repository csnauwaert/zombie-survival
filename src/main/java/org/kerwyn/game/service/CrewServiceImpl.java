package org.kerwyn.game.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.kerwyn.game.config.GameConfig;
import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.User;
import org.kerwyn.game.repositories.CrewRepository;
import org.kerwyn.game.repositories.HumanRepository;
import org.kerwyn.game.repositories.SkillRepository;
import org.kerwyn.game.service.exception.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CrewServiceImpl implements CrewService {

	@Autowired
	private CrewRepository crewRepository;

	@Autowired
	private HumanService humanService;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private HumanRepository humanRepository;
	
	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private Environment env;

	@Autowired
	private GameConfig config;

	private Logger log = Logger.getLogger(CrewService.class);

//	@PersistenceContext
//	EntityManager entityManager;
//
//	protected Session getCurrentSession()  {
//		return entityManager.unwrap(Session.class);
//	}

	@Override
	public Crew create(User user) throws NumberFormatException, ConfigurationException {
		//Create crew
//		Session session = getCurrentSession();
//		SQLQuery query = session.createSQLQuery("select * from crews;");
//		query.addEntity(Crew.class);
//		List results = query.list();
//		log.error(String.format("count crew = %s", results.size()));
		log.info(String.format("Creating new crew for user %s", user.getUsername()));
		Crew new_crew = crewRepository.save(new Crew(user));
		//Should create humans, basic loot, etc
		//Create basic human composing the crew
		int start_human = config.getCrewStartingHuman();
		Location starting_point = locationService.findEmptyLocation();
		log.debug(String.format("Starting point location at: %s", starting_point.getCoordinate()));
		for (int i=0; i<start_human; i++){
			//Create human
			humanService.create(new_crew, starting_point);
		}
		return new_crew;
	}

	@Override
	public void delete(Crew crew) {
		log.info(String.format("Delete crew: %s", crew.getId()));
		crewRepository.delete(crew.getId());
	}

}
