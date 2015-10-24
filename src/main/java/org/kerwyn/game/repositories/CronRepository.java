package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Cron;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronRepository extends CrudRepository<Cron, Long>{

	public Cron findOneByName(String name);
}
