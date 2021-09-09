/**
 * 
 */
package com.test.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import com.test.user.model.User;
import com.test.user.service.UserService;
import com.test.user.transformer.Message;
import com.test.user.transformer.UserDTO;
import com.test.user.transformer.UserRegisterRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.test.user.common.Constants.ERROR;
import static com.test.user.common.Constants.INVALID_REQUEST;
import static com.test.user.common.Constants.INVALID_USER_NAME;
import static com.test.user.common.Constants.INVALID_USER_EMAIL;
import static com.test.user.common.Constants.INVALID_USER_PASSWORD;
import static com.test.user.common.Constants.INVALID_PASSWORD_LENGTH;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
	private final UserService service;

	@PostMapping(path = "/user", produces = APPLICATION_JSON_VALUE)
	public Message saveUser(final UserRegisterRequest request) {
		Message message = isNullorEmpty(request);
		if (null == message) {
			User user = service.saveUser(request);
			if (null != user) {
				message = Message.builder().messageCode("S")
						.message("User Saved successfully. User Id " + user.getUserId()).build();
			}
		}
		return message;

	}

	@GetMapping(path = "/user", produces = APPLICATION_JSON_VALUE)
	public List<UserDTO> fetchUserList(final UserRegisterRequest request) {

		return service.fetchAllUser();

	}
	
	@GetMapping(path = "/login", produces = APPLICATION_JSON_VALUE)
	public UserDTO loginValidate(final UserRegisterRequest request) {
		UserDTO userDto = null;
		if (null == request) {
			userDto=new UserDTO();
			userDto.setMessageCode(ERROR);
			userDto.setMessage(INVALID_REQUEST);
		} else if (null == request.getUserEmail() || "".equals(request.getUserEmail())) {
			userDto=new UserDTO();
			userDto.setMessageCode(ERROR);
			userDto.setMessage(INVALID_USER_EMAIL);
		}else if (null == request.getUserPassword() || "".equals(request.getUserPassword())) {
			userDto=new UserDTO();
			userDto.setMessageCode(ERROR);
			userDto.setMessage(INVALID_USER_PASSWORD);
		} 
		if(null == userDto) {
			userDto=service.checkLogin(request);
		}
		return userDto;

	}


	private Message isNullorEmpty(UserRegisterRequest request) {
		Message message = null;
		if (null == request) {
			message = Message.builder().messageCode(ERROR).message(INVALID_REQUEST).build();
		} else if (null == request.getUserName() || "".equals(request.getUserName())) {
			message = Message.builder().messageCode(ERROR).message(INVALID_USER_NAME).build();
		} else if (null == request.getUserEmail() || "".equals(request.getUserEmail())) {
			message = Message.builder().messageCode(ERROR).message(INVALID_USER_EMAIL).build();
		}else if (null == request.getUserPassword() || "".equals(request.getUserPassword())) {
			message = Message.builder().messageCode(ERROR).message(INVALID_USER_PASSWORD).build();
		} else if (request.getUserPassword().length() < 8) {
			message = Message.builder().messageCode(ERROR).message(INVALID_PASSWORD_LENGTH).build();
		}
		return message;
	}

}
