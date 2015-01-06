package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.LocationTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface LocationTimeRepository extends
		CrudRepository<LocationTime, Long> {

}
