package net.ukr.grygorenko_d.springforum.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;
import net.ukr.grygorenko_d.springforum.repository.ForumMemberRepository;
import net.ukr.grygorenko_d.springforum.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	private ForumMemberRepository forumMemberRepository;
	private static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository, ForumMemberRepository forumMemberRepository) {
		super();
		this.roleRepository = roleRepository;
		this.forumMemberRepository = forumMemberRepository;
	}

	@Override
	@Transactional
	public void createRole(Role role) {
		roleRepository.save(role);
		LOGGER.info("Role created: " + role);

	}

	@Override
	@Transactional(readOnly = true)
	public Role getRoleRefByType(RoleTypes roleType) {
		return roleRepository.findByRoleType(roleType);
	}

	@Override
	public Set<Role> getRolesByUsername(String username) {
		ForumMember forumMember = forumMemberRepository.findWithRolesByUsername(username);
		Set<Role> roles = forumMember.getRoles();
		return roles;
	}

}
