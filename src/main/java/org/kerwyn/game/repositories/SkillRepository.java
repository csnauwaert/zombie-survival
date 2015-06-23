package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

	public Skill findOneByName(String name);

}
