package net.ukr.grygorenko_d.springforum.service;

import java.util.Set;

import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

public interface RoleService {

	public void createRole(Role role);

	public Role getRoleRefByType(RoleTypes roleType);

	public Set<Role> getRolesByUsername(String username);

}
