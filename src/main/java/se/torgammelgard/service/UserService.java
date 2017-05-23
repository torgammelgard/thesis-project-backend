package se.torgammelgard.service;

import org.springframework.stereotype.Service;

import se.torgammelgard.dto.UserDto;
import se.torgammelgard.exception.EmailExistsException;
import se.torgammelgard.exception.PasswordMismatchException;
import se.torgammelgard.form.UserForm;
import se.torgammelgard.persistence.entities.User;

/**
 * A service for handling users.
 * 
 * @author torgammelgard
 *
 */
@Service
public interface UserService {

	public User findByUsername(String username);
	
	public User registerNewUser(UserDto userDto) throws EmailExistsException, PasswordMismatchException;
	
	public User findById(Long id);
	
	public User update(UserForm userForm);
}
