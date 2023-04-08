//package com.example.todo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.todo.model.UserEntity;
//import com.example.todo.persistence.UserRepository;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//
//public class UserService {
//@Autowired
//private UserRepository userRepository;
//
//public UserEntity create(final UserEntity userEntity) {
//	if(userEntity==null || userEntity.getEmail()==null) {
//		throw new RuntimeException("Invalid arguments");
//	}
//	final String email=userEntity.getEmail();
//	if(userRepository.existsByEmail(email)) {
//		log.warn("Email already exists {}",email);
//		throw new RuntimeException("Email already exists");
//	}
//	return userRepository.save(userEntity);
//	
//}
//public UserEntity getByCredentials(final String email, final String password) {
//	return userRepository.findByEmailAndPassword(email,password);
//}
//}
package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.model.TodoEntity;
import com.example.todo.model.UserEntity;
import com.example.todo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String email = userEntity.getEmail();
		if(userRepository.existsByEmail(email)) {
			log.warn("Email already exists {}",email);
			throw new RuntimeException("Email already exists");
		}
		
		return userRepository.save(userEntity);
	}
	public UserEntity retrieve(final String email) {
		return userRepository.findByEmail(email);
	}
	public UserEntity update(final UserEntity userEntity) {
		
		
//		validate(userEntity);
		if(userRepository.existsByEmail(userEntity.getEmail())) {
			userRepository.save(userEntity);
		}
		else throw new RuntimeException("Unknown email");
		
		return userRepository.findByEmail(userEntity.getEmail());
	}
	public UserEntity getByCredentials(final String email, final String password,final PasswordEncoder encoder) {
		final UserEntity originalUser=userRepository.findByEmail(email);
		if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
//		return userRepository.findByEmailAndPassword(email, password);
		return null;
	}
}