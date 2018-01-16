package net.ukr.grygorenko_d.springforum.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.helpers.TextFormatter;
import net.ukr.grygorenko_d.springforum.service.BoardService;
import net.ukr.grygorenko_d.springforum.service.ForumMemberService;
import net.ukr.grygorenko_d.springforum.service.MessageService;
import net.ukr.grygorenko_d.springforum.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {

	private TopicService topicService;
	private BoardService boardService;
	private MessageService messageService;
	private ForumMemberService forumMemberService;

	@Autowired
	public TopicController(TopicService topicService, BoardService boardService, MessageService messageService,
			ForumMemberService forumMemberService) {
		super();
		this.topicService = topicService;
		this.boardService = boardService;
		this.messageService = messageService;
		this.forumMemberService = forumMemberService;
	}

	@GetMapping("/show")
	public String showTopic(@RequestParam("topicId") int topicId, Model model) {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			model.addAttribute("anonymousUser", true);
		}
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
	public String validateTopic(@RequestParam("topicName") String topicName, @RequestParam("boardId") int boardId,
			@ModelAttribute("message") Message tempMessage, Model model) {
		topicName = TextFormatter.formatString(topicName);
		tempMessage.setMessageBody(TextFormatter.formatString(tempMessage.getMessageBody()));
		Map<Boolean, String> validationStatus = topicService.validatetopic(topicName, tempMessage);
		if (validationStatus.containsKey(false)) {
			model.addAttribute("board", boardService.getBoardById(boardId));
			model.addAttribute("topicName", topicName);
			model.addAttribute("message", tempMessage);
			model.addAttribute("validationStutus", validationStatus.get(false));
			return "topic-form";
		} else {
			ForumMember creatorRef = forumMemberService.getCurrentUserRef();
			Message message = messageService.prepareMessage(tempMessage, creatorRef);
			topicService.saveNewTopic(boardId, topicName, message, creatorRef);
			return "redirect:/";
		}
	}
	
	@GetMapping("/delete")
	public String deleteTopic(@RequestParam("topicId") int topicId, @RequestParam("boardId") int boardId) {
		topicService.deleteTopicById(topicId);
		Board board = boardService.getBoardById(boardId);
		board.setSize(board.getSize()-1);
		boardService.saveBoard(board);
		return "redirect:/";
	}
	
	@GetMapping("/rename")
	public String renameTopic(@RequestParam("topicId") int topicId, Model model) {
		Topic topic =  topicService.getTopicById(topicId);
		model.addAttribute("topicId", topicId);
		model.addAttribute("topicName", topic.getTitle());
		return "rename-topic";
		
	}
	
	@PostMapping("/setNewTopicName")
	public String setNewTopicName(@RequestParam("topicId") int topicId, @RequestParam("topicName") String topicName) {
		Topic topic = topicService.getFullTopicById(topicId);
		topic.setTitle(topicName);
		topicService.updateTopic(topic);
		return "redirect:/";
	}
}
