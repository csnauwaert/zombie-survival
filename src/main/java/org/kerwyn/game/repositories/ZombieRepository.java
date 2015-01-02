package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Zombie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZombieRepository extends CrudRepository<Zombie, Long> {

}
