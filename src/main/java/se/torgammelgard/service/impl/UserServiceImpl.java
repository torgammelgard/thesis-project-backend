package se.torgammelgard.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mysql.cj.core.exceptions.PasswordExpiredException;

import se.torgammelgard.dto.UserDto;
import se.torgammelgard.exception.EmailExistsException;
import se.torgammelgard.exception.PasswordMismatchException;
import se.torgammelgard.form.UserForm;
import se.torgammelgard.persistence.entities.Role;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.RoleRepository;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User registerNewUser(UserDto userDto) throws EmailExistsException, PasswordMismatchException {

		if (userRepository.findByUsername(userDto.getUsername()) != null) {
			throw new EmailExistsException();
		}
		
		if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
			throw new PasswordMismatchException();
		}
		
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRoles(Arrays.asList(adminRole));
		user.setEnabled(true);
		
		return userRepository.save(user);
	}
	
	@Override
	public User update(UserForm userForm) {
		User user = new User();
		user.setId(userForm.getId());
		user.setUsername(userForm.getUsername());
		user.setFirstName(userForm.getFirstName() == null ? "" : userForm.getFirstName());
		user.setLastName(userForm.getLastName() == null ? "" : userForm.getLastName());
		user.setPassword(userForm.getPassword());
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
}
