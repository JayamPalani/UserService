package com.test.user.service;

import java.util.List;

import com.test.user.model.User;
import com.test.user.transformer.UserDTO;
import com.test.user.transformer.UserRegisterRequest;

public interface UserService {

	User saveUser(UserRegisterRequest request);

	List<UserDTO> fetchAllUser();

	UserDTO checkLogin(UserRegisterRequest request);

}
