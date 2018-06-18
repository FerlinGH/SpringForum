package net.ukr.grygorenko_d.springforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.service.ForumMemberService;

@Controller
@RequestMapping("/forumMember")
public class ForumMemberController {

	private ForumMemberService forumMemberService;

	@Autowired
	public ForumMemberController(ForumMemberService forumMemberService) {
		super();
		this.forumMemberService = forumMemberService;
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
	public String validateTransaction(@Valid @ModelAttribute("forumMember") ForumMember forumMember,
			BindingResult bindingResult, Model model) {

		if (!forumMember.getPassword().equals(forumMember.getConfirmPassword())) {
			bindingResult.rejectValue("password", "password.mismatch", "Your passwords do not match.");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("forumMember", forumMember);
			System.out.println(bindingResult.getAllErrors().toString());
			return "profile-form";
		} else {
			forumMemberService.createProfile(forumMember);
			return "redirect:/";
		}
	}

}
