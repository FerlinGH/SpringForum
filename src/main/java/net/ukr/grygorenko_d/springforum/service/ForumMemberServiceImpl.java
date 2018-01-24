package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;

@Service
public class ForumMemberServiceImpl implements ForumMemberService {

	private ForumMemberDAO forumMemberDAO;
	private static Logger LOGGER = LoggerFactory.getLogger(ForumMemberServiceImpl.class);

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
		
		if (username.equals("")) {
			validationStatus.put(false, "Error: Please enter your nickname!");
			return validationStatus;
		}

		if(forumMemberDAO.existsByUsername(username)) {
			 validationStatus.put(false, "Error: that nickname alredy exist, pick up another one!");
				return validationStatus;
		}

		if (passwordCandidate1.equals("")) {
			validationStatus.put(false, "Error: Password is required!");
			return validationStatus;
		}

		if(!passwordCandidate1.equals(passwordCandidate2)) {
			validationStatus.put(false, "Error: Your passwords do no match, please re-enter!");
			return validationStatus;
		}
		
		if (firstName.equals("")) {
			validationStatus.put(false, "Error: Fist name is required!");
			return validationStatus;
		}
		if (lastName.equals("")) {
			validationStatus.put(false, "Error: Last name is required!");
			return validationStatus;
		}
		if (email.equals("")) {
			validationStatus.put(false, "Error: Email is required!");
			return validationStatus;
		}
		if (!email.contains("@")) {
			validationStatus.put(false, "Error: incorrect email.");
			return validationStatus;
		}
		validationStatus.put(true, "Validate profile: profile validated!");
		return validationStatus;
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
