package com.demo.rpafile.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class User_Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "role")
	private Role role; 
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User_Role [id=" + id + ", user=" + user + ", role=" + role + "]";
	}
	
	
}
 