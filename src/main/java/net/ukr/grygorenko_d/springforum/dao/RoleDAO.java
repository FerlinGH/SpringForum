package net.ukr.grygorenko_d.springforum.dao;

import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

public interface RoleDAO {

	public void createRole(Role role);

	public Role getRoleRefByType(RoleTypes roleType);

}
