package com.demo.rpafile.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.rpafile.model.User;


@Component
public class DemoHubUtil {

	public List<User> getModifiedUser(List<User> users) {
		List<User> listUser = new ArrayList<User>();
		for (User user : users) {
			User u = new User();
			u.setId(user.getId());
			u.setPassword(null);
			u.setFullName(user.getFullName());
			u.setUsername(user.getUsername());
			u.setEmail(user.getEmail());
			u.setPhone(user.getPhone());
			listUser.add(u);
		}
		return listUser;
	}
	
}
