package org.kerwyn.game.repositories;


import org.kerwyn.game.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
		
	public User findOneByUsername(String username);

}
