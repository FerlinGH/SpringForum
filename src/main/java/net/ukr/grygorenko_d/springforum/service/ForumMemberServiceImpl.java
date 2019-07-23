package net.ukr.grygorenko_d.springforum.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;
import net.ukr.grygorenko_d.springforum.repository.ForumMemberRepository;

@Service
public class ForumMemberServiceImpl implements ForumMemberService {

	private ForumMemberRepository forumMemberRepository;
	private RoleService roleService;
	private PasswordEncoder passwordEncoder;
//	private static Logger LOGGER = LoggerFactory.getLogger(ForumMemberServiceImpl.class);

	@Autowired
	public ForumMemberServiceImpl(ForumMemberRepository forumMemberRepository, RoleService roleService,
			PasswordEncoder passwordEncoder) {
		super();
		this.forumMemberRepository = forumMemberRepository;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	public ForumMemberServiceImpl() {
		super();
	}

	@Override
	@Transactional
	public void setProfile(ForumMember forumMember, String action) {
		if (action.equals("create")) {
			String hashedPassword = passwordEncoder.encode(forumMember.getPassword());
			forumMember.setPassword(hashedPassword);
			forumMember.setConfirmPassword(hashedPassword);
			Role memberRole = roleService.getRoleRefByType(RoleTypes.MEMBER);
			forumMember.addRole(memberRole);
			this.saveProfile(forumMember);
		} else {
			Set<Role> memberRoles = roleService.getRolesByUsername(forumMember.getUsername());
			forumMember.setRoles(memberRoles);
			this.saveProfile(forumMember);
		}
	}

	@Override
	@Transactional
	public void saveProfile(ForumMember forumMember) {
		forumMemberRepository.save(forumMember);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getUserByUsername(String username) {
		return forumMemberRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getUserAndRolesByUsername(String username) {
		return forumMemberRepository.findWithRolesByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getUserRefferenceById(int id) {
		return forumMemberRepository.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ForumMember getCurrentUserRef() {
		User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = tempUser.getUsername();
		ForumMember user = forumMemberRepository.findByUsername(login);
		return forumMemberRepository.getOne(user.getId());
	}

}
