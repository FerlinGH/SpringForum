package net.ukr.grygorenko_d.springforum.dao;

import net.ukr.grygorenko_d.springforum.entity.Role;

public interface RoleDAO {

	public void createRole(Role role);

	public Role getRoleByName(String roleName);

}
