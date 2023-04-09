//package com.example.todo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.todo.dto.ResponseDTO;
//import com.example.todo.dto.UserDTO;
//import com.example.todo.model.UserEntity;
//import com.example.todo.security.TokenProvider;
//import com.example.todo.service.UserService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/auth")
//public class UserController {
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private TokenProvider tokenProvider;
//	
//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
//		try {
//			UserEntity user=UserEntity.builder()
//					.email(userDTO.getEmail())
//					.username(userDTO.getUsername())
//					.password(userDTO.getPassword())
//					.build();
//			
//			UserEntity registeredUser =userService.create(user);
//			UserDTO responseUserDTO=userDTO.builder()
//					.email(registeredUser.getEmail())
//					.id(registeredUser.getId())
//					.username(registeredUser.getUsername())
//					.build();
//			return ResponseEntity.ok().body(responseUserDTO);
//		}catch(Exception e) {
//			ResponseDTO responseDTO=ResponseDTO.builder().error(e.getMessage()).build();
//			return ResponseEntity.badRequest().body(responseDTO);
//		}
//	}
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
//		UserEntity user=userService.getByCredentials(userDTO.getEmail(),userDTO.getPassword());
//		if(user != null) {
//			final String token =tokenProvider.create(user);
//			final UserDTO responseUserDTO =UserDTO.builder()
//					.email(user.getEmail())
//					.id(user.getId())
//					.token(token)
//					.build();
//			return ResponseEntity.ok().body(responseUserDTO);
//		}else {
//			ResponseDTO responseDTO=ResponseDTO.builder() 
//					.error("Login failed")
//					.build();
//			return ResponseEntity.badRequest().body(responseDTO);
//		}
//	}
//
//}
package com.example.todo.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.ResponseDTO;
import com.example.todo.dto.TodoDTO;
import com.example.todo.dto.UserDTO;
import com.example.todo.model.TodoEntity;
import com.example.todo.model.UserEntity;
import com.example.todo.security.TokenProvider;
import com.example.todo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	  ModelMapper modelMapper;
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//	@PostMapping("/profile")
//	public ResponseEntity<?>userInfo(@RequestBody UserDTO userDTO){
//		try {
//			UserEntity user = UserEntity.builder()
//					.email(userDTO.getEmail())
//					.username(userDTO.getUsername())
//					.password(passwordEncoder.encode(userDTO.getPassword()))
//					.build();
//			
//			UserEntity registeredUser = userService.create(user);
//			UserDTO responseUserDTO = userDTO.builder()
//					.email(registeredUser.getEmail())
//					.id(registeredUser.getId())
//					.username(registeredUser.getUsername())
//					.build();
//			return ResponseEntity.ok().body(responseUserDTO);
//		}catch(Exception e){
//			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
//			return ResponseEntity.badRequest().body(responseDTO);
//		}
//	}
	@PostMapping("/signup")
	public ResponseEntity<?>registerUser(@RequestBody UserDTO userDTO){
		try {
			UserEntity user = UserEntity.builder()
					.email(userDTO.getEmail())
					.username(userDTO.getUsername())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.build();
			
			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = userDTO.builder()
					.email(registeredUser.getEmail())
					.id(registeredUser.getId())
					.username(registeredUser.getUsername())
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		}catch(Exception e){
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	@PutMapping("/update")
	public ResponseEntity<?> updateProfile(@AuthenticationPrincipal String userId,@RequestBody UserDTO userDTO) {
//		try {
//			UserEntity user = UserEntity.builder()
//					.email(userDTO.getEmail())
////					.id(userDTO.getId())
//					.username(userDTO.getUsername())
//					.password(passwordEncoder.encode(userDTO.getPassword()))
//					.build();
//			
//			UserEntity registeredUser = userService.update(user);
//			UserDTO responseUserDTO = userDTO.builder()
//					.email(registeredUser.getEmail())
//					.id(registeredUser.getId())
//					.username(registeredUser.getUsername())
//					.build();
//			return ResponseEntity.ok().body(responseUserDTO);
//		}catch(Exception e){
//			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
//			return ResponseEntity.badRequest().body(responseDTO);
//		}
//		try {
//			UserEntity user = UserEntity.builder()
//					.email(userDTO.getEmail())
////					.id(userDTO.getId())
//					.username(userDTO.getUsername())
//					.password(passwordEncoder.encode(userDTO.getPassword()))
//					.build();
//			UserEntity entity=userService.update(user);
//		System.out.println(entity);
//		UserDTO dto=modelMapper.map(entity, UserDTO.class);
//		System.out.println(dto);
////		ResponseDTO<UserDTO> response=ResponseDTO.<UserDTO>builder().build();
//		UserDTO response = dto.builder()
//				.email(dto.getEmail())
//				.id(dto.getId())
//				.username(dto.getUsername())
//				.password(dto.getPassword())
//				.build();
//		System.out.println(response);
//
//				return ResponseEntity.ok().body(response);}catch(Exception e){
//					ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
//					return ResponseEntity.badRequest().body(responseDTO);
//				}
		try {
			UserEntity entity=UserDTO.toEntity(userDTO);
//			entity.setUserId("temporary-user");
			entity.setId(userId);
			UserEntity entitiy=userService.update(entity);
			System.out.println(entity);
			UserDTO dto=modelMapper.map(entity, UserDTO.class);
			System.out.println(dto);
//			ResponseDTO<UserDTO> response=ResponseDTO.<UserDTO>builder().build();
			UserDTO response = dto.builder()
					.email(dto.getEmail())
					.id(dto.getId())
					.username(dto.getUsername())
					.password(dto.getPassword())
					.build();
			System.out.println(response);

					return ResponseEntity.ok().body(response);}catch(Exception e){
						ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
						return ResponseEntity.badRequest().body(responseDTO);
					}
	}
	@GetMapping("/profile")
	public ResponseEntity<?>retrieveUser(@RequestParam String email){
		try {UserEntity entity=userService.retrieve(email);
		System.out.println(entity);
		UserDTO dto=modelMapper.map(entity, UserDTO.class);
		System.out.println(dto);
//		ResponseDTO<UserDTO> response=ResponseDTO.<UserDTO>builder().build();
		UserDTO response = dto.builder()
				.email(dto.getEmail())
				.id(dto.getId())
				.username(dto.getUsername())
				.password(dto.getPassword())
				.build();
		System.out.println(response);

				return ResponseEntity.ok().body(response);}catch(Exception e){
					ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
					return ResponseEntity.badRequest().body(responseDTO);
				}
	}
	@PostMapping("/signin")
	public ResponseEntity<?>authenticate(@RequestBody UserDTO userDTO){
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(),passwordEncoder);
		
		if(user !=null){
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder()
					.email(user.getEmail())
					.id(user.getId())
					.token(token)
					.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("Login failed")
					.build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

}
