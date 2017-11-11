package net.ukr.grygorenko_d.springforum.service;

import net.ukr.grygorenko_d.springforum.entity.Role;

public interface RoleService {

	public void createRole(Role role);

	public Role getRoleByName(String roleName);

}