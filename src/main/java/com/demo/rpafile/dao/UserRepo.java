package com.demo.rpafile.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.rpafile.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

	User findByusername(String username);

}
