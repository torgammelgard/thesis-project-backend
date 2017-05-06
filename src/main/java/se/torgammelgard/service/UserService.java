package se.torgammelgard.service;

import org.springframework.stereotype.Service;

import se.torgammelgard.persistence.entities.User;

@Service
public interface UserService {

	public User findByUsername(String username);
}
