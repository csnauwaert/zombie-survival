package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface LocationRepository extends CrudRepository<Location, Long> {

	Location findOneByCoordinate(Integer coordinate);

}
