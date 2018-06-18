package net.ukr.grygorenko_d.springforum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

@Service
public class ForumMemberServiceImpl implements ForumMemberService {

	private ForumMemberDAO forumMemberDAO;
	private RoleService roleService;
	private PasswordEncoder passwordEncoder;
	private static Logger LOGGER = LoggerFactory.getLogger(ForumMemberServiceImpl.class);

	

	@Autowired
	public ForumMemberServiceImpl(ForumMemberDAO forumMemberDAO, RoleService roleService,
			PasswordEncoder passwordEncoder) {
		super();
		this.forumMemberDAO = forumMemberDAO;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	public ForumMemberServiceImpl() {
		super();
	}

	public void createProfile(ForumMember forumMember) {
		String hashedPassword = passwordEncoder.encode(forumMember.getPassword());
		forumMember.setPassword(hashedPassword);
		forumMember.setConfirmPassword(hashedPassword);
		Role memberRole = roleService.getRoleRefByType(RoleTypes.MEMBER);
		forumMember.addRole(memberRole);
		this.saveProfile(forumMember);
	}
	
	@Override
	@Transactional
	public void saveProfile(ForumMember forumMember) {

		forumMemberDAO.saveMember(forumMember);
		LOGGER.info("Member profile created: " + forumMember);

	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getUserByUsername(String username) {
		return forumMemberDAO.getMemberByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getUserAndRolesByUsername(String username) {
		return forumMemberDAO.getUserAndRolesByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getUserRefferenceById(int id) {
		return forumMemberDAO.getUserRefferenceById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getCurrentUserRef() {
		User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = tempUser.getUsername();
		ForumMember userRef = forumMemberDAO.getMemberRefByUsername(login);
		return userRef;
	}

}
