package com.demo.rpafile.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.rpafile.model.User_Role;

@Repository
public interface UserRoleRepo extends JpaRepository<User_Role, Integer> {
//	@Query("from user_role where user =: user_id")
	User_Role findRoleByUserId(int id);

}
