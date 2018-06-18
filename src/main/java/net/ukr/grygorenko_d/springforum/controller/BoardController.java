package net.ukr.grygorenko_d.springforum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.service.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {

	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@GetMapping("/")
	public String showBoards(Model model) {
		model.addAttribute("boards", boardService.listBoards());
		return "list-boards";
	}

	@GetMapping("/showBoard")
	public String showTopics(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("board", boardService.getBoardById(boardId));
		List<Topic> topicsList = boardService.listTopicsWithAuthorsByBoardId(boardId);
		model.addAttribute("topicsList", topicsList);
		return "list-topics";
	}
	
}
