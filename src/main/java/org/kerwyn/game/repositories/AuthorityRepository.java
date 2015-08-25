package org.kerwyn.game.repositories;

import org.kerwyn.game.entities.Authority;
import org.kerwyn.game.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

	public Authority findOneByUsername(String username);
	public Authority findOneByUser(User user);
}
