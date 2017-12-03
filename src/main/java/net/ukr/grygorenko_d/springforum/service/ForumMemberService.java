package net.ukr.grygorenko_d.springforum.service;

import java.util.Map;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

public interface ForumMemberService {

	 public Map<Boolean, String> validateProfile(ForumMember forumMember, String firstName, String lastName, String email);

	public void saveProfile(ForumMember forumMember);

}
