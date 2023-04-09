//package com.example.todo.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class UserDTO {
//private String token;
//private String email;
//private String username;
//private String password;
//private String id;
//}
package com.example.todo.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.todo.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String token;
	private String email;
	private String username;
	private String password;
	private String id;
	private static PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	public UserDTO(final UserEntity entity) {
		this.id=entity.getId();
		this.password=entity.getPassword();
		this.username=entity.getUsername();
		this.email=entity.getEmail();	}
	public static UserEntity toEntity(final UserDTO dto) {
		 return UserEntity.builder()
				.id(dto.getId())
				.password(passwordEncoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.username(dto.getUsername()).build();
		
	}	

}