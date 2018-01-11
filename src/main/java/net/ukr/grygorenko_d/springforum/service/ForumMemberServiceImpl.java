package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public Map<Boolean, String> validateProfile(ForumMember forumMember, String passwordCandidate1,
			String passwordCandidate2, String firstName, String lastName,
			String email) {
		Map<Boolean, String> validationStatus = new HashMap<>();
		String username = forumMember.getUsername();
//		ForumMember candidate = forumMemberDAO.getMemberByUsername(username);
//		if (candidate != null) {
//			validationStatus.put(false, "Validation error: that nickname alredy exist, pick up another one!");
//			return validationStatus;
//		}
		 if(forumMemberDAO.existsByUsername(username)) {
			 validationStatus.put(false, "Validation error: that nickname alredy exist, pick up another one!");
				return validationStatus;
		 }
		if(!passwordCandidate1.equals(passwordCandidate2)) {
			validationStatus.put(false, "Validation error: Your passwords do no match, please re-enter!");
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

}
