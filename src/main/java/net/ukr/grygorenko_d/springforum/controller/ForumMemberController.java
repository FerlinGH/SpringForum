package net.ukr.grygorenko_d.springforum.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.ForumMemberDetails;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;
import net.ukr.grygorenko_d.springforum.helpers.TextFormatter;
import net.ukr.grygorenko_d.springforum.service.ForumMemberService;
import net.ukr.grygorenko_d.springforum.service.RoleService;



@Controller
@RequestMapping("/forumMember")
public class ForumMemberController {

	private ForumMemberService forumMemberService;
	private RoleService roleService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public ForumMemberController(ForumMemberService forumMemberService, RoleService roleService,
			PasswordEncoder passwordEncoder) {
		super();
		this.forumMemberService = forumMemberService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	public ForumMemberController() {
		super();
	}

	@GetMapping("/create")
	public String createProfile(Model model) {
		model.addAttribute("forumMember", new ForumMember());
		return "profile-form";
	}

	@PostMapping("/validateProfile")
	public String validateTransaction(@ModelAttribute("forumMember") ForumMember forumMember,
			@RequestParam("passwordCandidate1") String passwordCandidate1,
			@RequestParam("passwordCandidate2") String passwordCandidate2, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email, Model model) {
		forumMember.setUsername(TextFormatter.formatString(forumMember.getUsername()));
		firstName = TextFormatter.formatString(firstName);
		lastName = TextFormatter.formatString(lastName);
		email = TextFormatter.formatString(email);

		Map<Boolean, String> validationStatus = forumMemberService.validateProfile(forumMember, passwordCandidate1,
				passwordCandidate2, firstName, lastName, email);
		if (validationStatus.containsKey(true)) {
			String hashedPassword = passwordEncoder.encode(passwordCandidate1);
			forumMember.setPassword(hashedPassword);
			Role memberRole = roleService.getRoleRefByType(RoleTypes.MEMBER);
			forumMember.addRole(memberRole);
			ForumMemberDetails newDetails = new ForumMemberDetails(firstName, lastName, email, forumMember);
			forumMember.setMemberDetails(newDetails);
			forumMemberService.saveProfile(forumMember);
			return "redirect:/";
		} else {
			model.addAttribute("forumMember", forumMember);
			model.addAttribute("firstName", firstName);
			model.addAttribute("lastName", lastName);
			model.addAttribute("email", email);
			model.addAttribute("message", validationStatus.get(false));
			return "profile-form";
		}

	}

}
