package org.kerwyn.game.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.kerwyn.game.entities.Human;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends CrudRepository<Human, Long> {

	public Human findOneByName(String name);
	
	public List<Human> findByTimeOfReturnBetween(Timestamp start_date, Timestamp end_date);
	
	public List<Human> findByTimeOfTurningBetween(Timestamp start_date, Timestamp end_date);

}
