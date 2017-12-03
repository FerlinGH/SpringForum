package net.ukr.grygorenko_d.springforum.dao;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

public interface ForumMemberDAO {

	public ForumMember getMemberByNickname(String nickname);

	public void saveMember(ForumMember forumMember);

}
