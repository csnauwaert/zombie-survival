package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Crew;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends CrudRepository<Crew, Long> {
	
}
