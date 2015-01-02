package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.UserTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTempRepository extends CrudRepository<UserTemp, Long> {

}
