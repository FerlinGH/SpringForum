package net.ukr.grygorenko_d.springforum.service;

import java.util.Map;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

public interface ForumMemberService {

	public Map<Boolean, String> validateProfile(ForumMember forumMember, String passwordCandidate1,
			String passwordCandidate2, String firstName, String lastName, String email);

	public void saveProfile(ForumMember forumMember);

	public ForumMember getUserByUsername(String username);

	public ForumMember getUserAndRolesByUsername(String username);

	public ForumMember getUserRefferenceById(int id);

	public ForumMember getCurrentUserRef();

}
