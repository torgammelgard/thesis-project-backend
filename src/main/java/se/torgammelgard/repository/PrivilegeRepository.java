package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Privilege;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
	
	Privilege findByName(String name);
}
