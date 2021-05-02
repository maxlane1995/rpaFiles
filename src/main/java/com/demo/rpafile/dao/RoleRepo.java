package com.demo.rpafile.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.rpafile.model.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
