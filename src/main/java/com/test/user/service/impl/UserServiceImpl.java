package com.test.user.service.impl;

import static com.test.user.common.Constants.ERROR;
import static com.test.user.common.Constants.INVALID_REQUEST;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.user.dao.UserDAO;
import com.test.user.model.User;
import com.test.user.service.UserService;
import com.test.user.transformer.UserDTO;
import com.test.user.transformer.UserRegisterRequest;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserDAO userDao;
	@Override
	public User saveUser(UserRegisterRequest request) {
		User user=User.builder().userName(request.getUserName()).userPassword(request.getUserPassword()).
				userEmail(request.getUserEmail()).lastLoginDate(new Date()).build();
		return userDao.save(user);
	}
	@Override
	public List<UserDTO> fetchAllUser() {
		return userDao.fethAllUser();
	}
	@Override
	public UserDTO checkLogin(UserRegisterRequest request) {
		UserDTO dto=null;
		User user=userDao.findUserByLogin(request.getUserEmail(),request.getUserPassword());
		if(null !=user) {
			dto=new UserDTO(user.getUserName(), user.getUserEmail(), user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userDao.save(user);
		}else {
			dto=new UserDTO();
			dto.setMessageCode(ERROR);
			dto.setMessage(INVALID_REQUEST);
		}
		return dto;
	}

}
