package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Human;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends CrudRepository<Human, Long> {

}
