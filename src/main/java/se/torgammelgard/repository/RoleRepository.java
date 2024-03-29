package se.torgammelgard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Role;

/**
 * A Role repo.
 * 
 * @author torgammelgard
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
	
}
