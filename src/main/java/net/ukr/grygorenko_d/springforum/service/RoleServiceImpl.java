package net.ukr.grygorenko_d.springforum.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.dao.RoleDAO;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;
	private ForumMemberDAO forumMemberDAO;
	private static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	public RoleServiceImpl(RoleDAO roleDAO, ForumMemberDAO forumMemberDAO) {
		super();
		this.forumMemberDAO = forumMemberDAO;
		this.roleDAO = roleDAO;
	}

	@Override
	@Transactional
	public void createRole(Role role) {
		roleDAO.createRole(role);
		LOGGER.info("Role created: " + role);

	}

	@Override
	@Transactional(readOnly = true)
	public Role getRoleRefByType(RoleTypes roleType) {
		return roleDAO.getRoleRefByType(roleType);
	}

	@Override
	public Set<Role> getRolesByUsername(String username) {
		ForumMember forumMember = forumMemberDAO.getUserAndRolesByUsername(username);
		Set<Role> roles = forumMember.getRoles();
		return roles;
	}

}
