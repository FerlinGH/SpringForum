package net.ukr.grygorenko_d.springforum.service;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

public interface ForumMemberService {

	public void saveProfile(ForumMember forumMember);

	public ForumMember getUserByUsername(String username);

	public ForumMember getUserAndRolesByUsername(String username);

	public ForumMember getUserRefferenceById(int id);

	public ForumMember getCurrentUserRef();

	public void setProfile(ForumMember forumMember, String action);

}
