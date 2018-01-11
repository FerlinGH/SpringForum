package net.ukr.grygorenko_d.springforum.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ForumMemberService forumMemberService;

	@Autowired
	public UserDetailsServiceImpl(ForumMemberService forumMemberService) {
		super();
		this.forumMemberService = forumMemberService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ForumMember member = forumMemberService.getUserAndRolesByUsername(username);
		if (member == null)
			throw new UsernameNotFoundException(username + " not found");

		Set<GrantedAuthority> userRoles = new HashSet<>();
		for (Role role : member.getRoles()) {
			userRoles.add(new SimpleGrantedAuthority(role.toString()));
		}
		return new User(member.getUsername(), member.getPassword(), userRoles);
	}

}
