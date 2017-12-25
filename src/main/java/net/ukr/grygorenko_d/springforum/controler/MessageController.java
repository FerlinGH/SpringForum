package net.ukr.grygorenko_d.springforum.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.service.MessageService;
import net.ukr.grygorenko_d.springforum.service.TopicService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private MessageService messageService;
	private TopicService topicService;

	@Autowired
	public MessageController(MessageService messageService, TopicService topicService) {
		super();
		this.messageService = messageService;
		this.topicService = topicService;
	}

	@GetMapping("/new")
	public String createMessage(@RequestParam("topicId") int topicId, Model model) {
		model.addAttribute("topic", topicService.getTopicById(topicId));
		Message tempMessage = new Message();
		model.addAttribute("message", tempMessage);
		return "message-form";
	}

	@PostMapping("/validateMessage")
	public String validateMessage(@ModelAttribute("message") Message tempMessage, @RequestParam("topicId") int topicId, Model model){
		if(!tempMessage.getMessageBody().equals("")) {
			messageService.saveMessage(tempMessage, topicId);
			return "redirect:/";
		}else {
			model.addAttribute("topic", topicService.getTopicById(topicId));
			model.addAttribute("message", tempMessage);
			model.addAttribute("validationStutus", "Empty message not allowed!");
			return "message-form";
			
		}
	}

}
