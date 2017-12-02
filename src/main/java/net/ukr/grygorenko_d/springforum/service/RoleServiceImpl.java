package net.ukr.grygorenko_d.springforum.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.springforum.dao.RoleDAO;
import net.ukr.grygorenko_d.springforum.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;

	@Autowired
	public RoleServiceImpl(RoleDAO roleDAO) {
		super();
		this.roleDAO = roleDAO;
	}

	@Override
	@Transactional
	public void createRole(Role role) {
		roleDAO.createRole(role);

	}

	@Override
	@Transactional
	public Role getRoleByName(String roleName) {
		return roleDAO.getRoleByName(roleName);
	}

}
