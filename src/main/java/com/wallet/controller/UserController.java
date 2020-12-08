package com.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import com.wallet.util.Response;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result) {

		Response<UserDTO> response = new Response<UserDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		User user = service.save(convertUserToEntity(dto));

		response.setData(this.convertEntityToUser(user));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	private User convertUserToEntity(UserDTO dto) {
		User u = new User();
		u.setId(dto.getId());
		u.setName(dto.getName());
		u.setPassword(dto.getPassword());
		u.setEmail(dto.getEmail());

		return u;
	}

	private UserDTO convertEntityToUser(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getId());
		dto.setName(u.getName());
		dto.setPassword(u.getPassword());
		dto.setEmail(u.getEmail());

		return dto;
	}

}
