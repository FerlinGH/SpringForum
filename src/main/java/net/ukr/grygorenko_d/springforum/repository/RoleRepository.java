package net.ukr.grygorenko_d.springforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRoleType(RoleTypes roleType); 

}
