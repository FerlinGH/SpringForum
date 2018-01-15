package net.ukr.grygorenko_d.springforum.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.RoleDAO;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

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
	@Transactional(readOnly = true)
	public Role getRoleRefByType(RoleTypes roleType) {
		return roleDAO.getRoleRefByType(roleType);
	}

}
