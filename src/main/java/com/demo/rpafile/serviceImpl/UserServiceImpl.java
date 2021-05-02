package com.demo.rpafile.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.hibernate.engine.jdbc.spi.ResultSetWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.rpafile.dao.RoleRepo;
import com.demo.rpafile.dao.UserRepo;
import com.demo.rpafile.dao.UserRoleRepo;
import com.demo.rpafile.model.Result;
import com.demo.rpafile.model.Role;
import com.demo.rpafile.model.User;
import com.demo.rpafile.model.User_Role;
import com.demo.rpafile.service.UserService;
import com.demo.rpafile.utility.DemoHubUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("UserService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserRoleRepo uRoleRepo;

	@Autowired
	private RoleRepo roleRepp;
	
	@Autowired
	private DemoHubUtil demoUtil;

	@Override
	public Result registerUser(User user) {

		Result result = new Result();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User_Role uRole = new User_Role();
		Role role = new Role();
		role.setId(2);
		try {
			String password = encoder.encode(user.getPassword());
			user.setPassword(password);
			User check = userRepo.findByusername(user.getUsername());
			if (check != null) {
				result.setSuccess(Boolean.FALSE);
				result.setSuccessMessage("USERNAME IS ALREADY PRESENT");
			} else {
				User u = userRepo.save(user);
				if (u != null) {
					uRole.setRole(role);
					uRole.setUser(u);
					User_Role urole = uRoleRepo.save(uRole);
					System.out.println("uRole:" + uRoleRepo.findAll());
					result.setSuccess(Boolean.TRUE);
					result.setSuccessMessage("USER SAVED SUCCESSFULLY");
				} else {
					result.setError("NOT SAVED SUCCESSFULLY");
					result.setSuccess(Boolean.FALSE);
				}
			}

		} catch (Exception e) {
			logger.error(e.toString());
			result.setError("NOT SAVED SUCCESSFULLY");
			result.setSuccess(Boolean.FALSE);
		}

		return result;
	}

	public User getByUserName(String username) {
		return userRepo.findByusername(username);
	}

	public Map<String, Object> loginUser(User user) throws LoginException {
		Result result = new Result();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User b = this.getByUserName(user.getUsername());
		String uname = user.getUsername();
		String pass = user.getPassword();
		UserDetails ut = null;
		String token = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (b != null) {
			ut = this.getUserVerification(uname, pass, b, encoder);

		} else {
			logger.error("please check your username or passowrd again and correctly");
			throw new LoginException("please check your username or password");
		}

		if (ut != null) {
			int addMinuteTime = 100;
			LocalDateTime d = LocalDateTime.now();
			int CCC = d.getDayOfYear() + 4;
			Date ExpirationTime = null; // now
			ExpirationTime = new Date(System.currentTimeMillis() + 3600000);
			token = Jwts.builder().setSubject(user.getUsername()).claim("roles", ut.getAuthorities())
					.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey")
					.setExpiration(ExpirationTime).compact();
			User user1 = userRepo.findByusername(user.getUsername());
			user1.setJwtToken(token);
			userRepo.save(user1);
			map.put("tokens", token);
			map.put("userDetails", ut);

		}

		return map;
	}
	// result.setSuccess(Boolean.TRUE); // instead of
	// using password matcher method //
	// result.setSuccessMessage("USER LOGIN
	// SUCCESSFULLY"); // the Bcrypt class// use matcher
	// method inside
	// After sign in we just send the token and the userDetail object it contain
	// username password and the token
	// at frontend token save in cookie and if username and password is correct then
	// use

	public UserDetails getUserVerification(String username, String password, User user, PasswordEncoder encoder) {
		UserDetails userDetails = null;
		if (username.equals(user.getUsername()) && encoder.matches(password, user.getPassword())) {

			userDetails = this.addUserDetails(user);
		}
		return userDetails;

	}

	public UserDetails addUserDetails(User user) {
		Set<GrantedAuthority> authority = new HashSet<GrantedAuthority>();
		User_Role urole = uRoleRepo.findById(user.getId()).get();
		logger.info(urole.toString());
		String userName = user.getUsername();
		String password = user.getPassword();
		Role role = roleRepp.findById(urole.getRole().getId()).get();
		GrantedAuthority grantedAuth = new SimpleGrantedAuthority(role.getName());
		authority.add(grantedAuth);
		return new org.springframework.security.core.userdetails.User(userName, password, authority);
	}

	public String passowordMatcher(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	// ResponseEntity<ResultWrapper<T>> s = new ResponseEntity<ResultSetWrapper<T>>;

	@Override
	public List<User> getAllUsers() {
		List<User> list = userRepo.findAll();
		List<User> user = demoUtil.getModifiedUser(list);
		return user;
	}

	@Override
	public Result logOutCurrentUser() {
		String uName = SecurityContextHolder.getContext().getAuthentication().getName();
		Result result = new Result();
		User user = userRepo.findByusername(uName);
		if(user != null) {
			user.setJwtToken(null);
			userRepo.save(user);
			result.setSuccess(Boolean.TRUE);
			Jwts.builder().setExpiration(new Date());
			
			result.setSuccessMessage("LOGGED OUT SUCCESSFULLY");
		}
		else {
			result.setError(uName+" "+"USER NOT FOUND");
			result.setSuccess(Boolean.FALSE);
		}
		
		return result;
	}

	@Override
	public User getUserById(int id) {
		User user = userRepo.findById(id).get();
		return user;
	}

	@Override
	public Result udpateUser(User user) {
		User er = userRepo.findById(user.getId()).get(); 
		Result result = new Result();
		if((er != null)) {
			er.setUsername(user.getUsername()); 
			userRepo.save(er);
			result.setSuccess(Boolean.TRUE);
			result.setError("no error occured");
			result.setSuccessMessage("USER UPDATED SUCCEESSFULLY");
		}else {
			result.setSuccess(Boolean.FALSE);
			result.setError("error occured");
			result.setSuccessMessage("USER NOT UPDATED");
			
		}
		return result;
	}
}
