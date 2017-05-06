package se.torgammelgard.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
