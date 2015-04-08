package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Interface LocationRepository.
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

	/**
	 * Find one by coordinate.
	 *
	 * @param coordinate the coordinate
	 * @return the location
	 */
	Location findOneByCoordinate(String coordinate);
	
	/**
	 * Count by coordinate.
	 *
	 * @param coordinate the coordinate
	 * @return the long
	 */
	Long countByCoordinate(String coordinate);

}
