package net.ukr.grygorenko_d.springforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("action", "create");
		return "profile-form";
	}

	@PostMapping("/validateProfile")
	public String validateTransaction(@Valid @ModelAttribute("forumMember") ForumMember forumMember,
			BindingResult bindingResult, @RequestParam("action") String action, Model model) {

		if (!forumMember.getPassword().equals(forumMember.getConfirmPassword())) {
			bindingResult.rejectValue("password", "password.mismatch", "Your passwords do not match.");
		}

		if (bindingResult.hasErrors()) {
			if (action.equals("create")) {
				model.addAttribute("forumMember", forumMember);
				model.addAttribute("action", action);
				System.out.println(bindingResult.getAllErrors().toString());
				return "profile-form";
			} else {
				model.addAttribute("forumMember", forumMember);
				model.addAttribute("action", action);
				System.out.println(bindingResult.getAllErrors().toString());
				return "list-user";		
			}
		} else {
			forumMemberService.setProfile(forumMember,action);
			return "redirect:/";
		}
	}
	
	@GetMapping("/showUser")
	public String showUser (@RequestParam("userLogin") String userLogin, Model model) {
		User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = tempUser.getUsername();
		if (login.equals(userLogin)) {
			ForumMember forumMember = forumMemberService.getUserAndRolesByUsername(userLogin);
			model.addAttribute("forumMember", forumMember);
			model.addAttribute("action", "edit");
			return "list-user";
		} else {
			return "access-denied";
		}
		
	}

}
