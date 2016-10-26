package se.torgammelgard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.torgammelgard.persistence.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
