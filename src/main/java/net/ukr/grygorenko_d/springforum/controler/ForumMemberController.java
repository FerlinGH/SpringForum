package net.ukr.grygorenko_d.springforum.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.ForumMemberDetails;
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
	public String validateTransaction(@ModelAttribute("forumMember") ForumMember forumMember,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, Model model) {
		forumMember.setNickname(forumMember.getNickname().replaceAll("\\s+", ""));
		firstName = firstName.replaceAll("\\s+", "");
		lastName = lastName.replaceAll("\\s+", "");
		email = email.replaceAll("\\s+", "");

		Map<Boolean, String> validationStatus = forumMemberService.validateProfile(forumMember, firstName, lastName,
				email);
		if (validationStatus.containsKey(true)) {
			System.out.println(validationStatus.get(true));
			ForumMemberDetails newDetails = new ForumMemberDetails(firstName, lastName, email, forumMember);
			forumMember.setMemberDetails(newDetails);
			forumMemberService.saveProfile(forumMember);
			return "redirect:/";
		} else {
			System.out.println(validationStatus.get(false));
			model.addAttribute("forumMember", forumMember);
			model.addAttribute("firstName", firstName);
			model.addAttribute("lastName", lastName);
			model.addAttribute("email", email);
			model.addAttribute("message", validationStatus.get(false));
			return "profile-form";
		}

	}

}
