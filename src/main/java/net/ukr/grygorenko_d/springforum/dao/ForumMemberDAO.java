package net.ukr.grygorenko_d.springforum.dao;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

public interface ForumMemberDAO {

	public ForumMember getMemberByUsername(String username);

	public void saveMember(ForumMember forumMember);

	public ForumMember getUserAndRolesByUsername(String username);

	public boolean existsByUsername(String username);

	public ForumMember getUserRefferenceById(int id);

	public ForumMember getUserRefferenceByMessageId(int messageId);

}
