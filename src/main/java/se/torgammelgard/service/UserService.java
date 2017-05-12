package se.torgammelgard.service;

import org.springframework.stereotype.Service;

import se.torgammelgard.dto.UserDto;
import se.torgammelgard.exception.EmailExistsException;
import se.torgammelgard.persistence.entities.User;

@Service
public interface UserService {

	public User findByUsername(String username);
	
	public User registerNewUser(UserDto userDto) throws EmailExistsException;
}
