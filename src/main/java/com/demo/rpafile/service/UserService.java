package com.demo.rpafile.service;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import org.springframework.security.core.userdetails.UserDetails;

import com.demo.rpafile.model.Result;
import com.demo.rpafile.model.User;



public interface UserService {

	public Result registerUser(User user);

	public Map<String, Object> loginUser(User user) throws LoginException;

	public List<User> getAllUsers();

	public Result logOutCurrentUser();

	public User getUserById(int id);

	public Result udpateUser(User user);
}
 