package net.ukr.grygorenko_d.springforum.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.helpers.TextFormatter;
import net.ukr.grygorenko_d.springforum.service.BoardService;
import net.ukr.grygorenko_d.springforum.service.MessageService;
import net.ukr.grygorenko_d.springforum.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {

	private TopicService topicService;
	private BoardService boardService;
	private MessageService messageService;

	@Autowired
	public TopicController(TopicService topicService, BoardService boardService, MessageService messageService) {
		super();
		this.topicService = topicService;
		this.boardService = boardService;
		this.messageService = messageService;
	}

	@GetMapping("/show")
	public String showTopic(@RequestParam("topicId") int topicId, Model model) {
		List<Message> topicMessagesList = topicService.getMessagesWithAuthorsByTopicId(topicId);
		model.addAttribute("topic", topicService.getTopicById(topicId));
		model.addAttribute("messageList", topicMessagesList);
		return "list-messages";

	}

	@GetMapping("/new")
	public String createMessage(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("board", boardService.getBoardById(boardId));
		Message tempMessage = new Message();
		model.addAttribute("message", tempMessage);
		return "topic-form";
	}

	@PostMapping("/validateTopic")
	public String validateTopic(@RequestParam("topicName") String topicName, @RequestParam("boardId") String boardId,
			@ModelAttribute("message") Message tempMessage, Model model) {
		topicName = TextFormatter.formatString(topicName);
		tempMessage.setMessageBody(TextFormatter.formatString(tempMessage.getMessageBody()));
		int id = Integer.parseInt(boardId);
		if (topicName.equals("")) {
			model.addAttribute("board", boardService.getBoardById(id));
			model.addAttribute("topicName", topicName);
			model.addAttribute("message", tempMessage);
			model.addAttribute("validationStutus", "Topic name cannot be emtpy!");
			return "topic-form";
		} else if (tempMessage.getMessageBody().equals("")) {
			model.addAttribute("board", boardService.getBoardById(id));
			model.addAttribute("topicName", topicName);
			model.addAttribute("message", tempMessage);
			model.addAttribute("validationStutus", "Empty message not allowed!");
			return "topic-form";
		} else {
			Message message = messageService.prepareMessage(tempMessage);
			topicService.saveNewTopic(id, topicName, message);
			return "redirect:/";
		}
	}
}
