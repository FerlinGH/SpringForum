package net.ukr.grygorenko_d.springforum.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {

	private TopicService topicService;

	public TopicController() {
		super();
	}

	@Autowired
	public TopicController(TopicService topicService) {
		super();
		this.topicService = topicService;
	}

	@GetMapping("/show")
	public String showTopic(@RequestParam("topicId") int topicId, Model model) {
		List<Message> topicMessagesList = topicService.listMessagesByTopicId(topicId);
		model.addAttribute("topic", topicService.getTopicById(topicId));
		model.addAttribute("messageList", topicMessagesList);
		model.addAttribute("messagesMap", topicService.generateMessagesMap(topicMessagesList));
		return "messages-agregator";

	}

}
