package com.test.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.user.model.User;
import com.test.user.transformer.UserDTO;
@Repository
public interface UserDAO extends JpaRepository<User, Long>{
	@Query("select new com.test.user.transformer.UserDTO(u.userName,u.userEmail,u.lastLoginDate) from User u")
	List<UserDTO> fethAllUser();
	@Query("select u from User u where u.userEmail=:userEmail and u.userPassword=:userPassword")
	User findUserByLogin(String userEmail, String userPassword);

}
