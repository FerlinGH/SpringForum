package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;

@Service
public class ForumMemberServiceImpl implements ForumMemberService {

	private ForumMemberDAO forumMemberDAO;

	@Autowired
	public ForumMemberServiceImpl(ForumMemberDAO forumMemberDAO) {
		super();
		this.forumMemberDAO = forumMemberDAO;
	}

	public ForumMemberServiceImpl() {
		super();
	}

	@Override
	@Transactional
	public Map<Boolean, String> validateProfile(ForumMember forumMember, String firstName, String lastName,
			String email) {
		Map<Boolean, String> validationStatus = new HashMap<>();
		String nickname = forumMember.getNickname();
		ForumMember candidate = forumMemberDAO.getMemberByNickname(nickname);
		if (candidate != null) {
			validationStatus.put(false, "Validation error: that nickname alredy exist, pick up another one!");
			return validationStatus;
		}
		if (firstName.equals("")) {
			validationStatus.put(false, "Validation error: Fist name is required!");
			return validationStatus;
		}
		if (lastName.equals("")) {
			validationStatus.put(false, "Validation error: Last name is required!");
			return validationStatus;
		}
		if (email.equals("")) {
			validationStatus.put(false, "Validation error: Email is required!");
			return validationStatus;
		}
		if (!email.contains("@")) {
			validationStatus.put(false, "Validation error: incorrect email.");
			return validationStatus;
		}
		validationStatus.put(true, "Validate profile: profile validated!");
		return validationStatus;
	}

	@Override
	@Transactional
	public void saveProfile(ForumMember forumMember) {
		forumMemberDAO.saveMember(forumMember);

	}

}
