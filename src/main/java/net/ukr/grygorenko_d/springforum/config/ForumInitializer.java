package net.ukr.grygorenko_d.springforum.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.entity.User;
import net.ukr.grygorenko_d.springforum.service.BoardService;
import net.ukr.grygorenko_d.springforum.service.RoleService;

@Component
public class ForumInitializer {

	private BoardService boardService;
	private RoleService roleService;
	private static Logger LOGGER = LoggerFactory.getLogger(ForumInitializer.class);


	@Autowired
	public ForumInitializer(BoardService boardService, RoleService roleService) {
		super();
		this.boardService = boardService;
		this.roleService = roleService;
	}

	@PostConstruct
	public void initDatabase() {
		roleService.createRole(new Role("ADMIN"));
		roleService.createRole(new Role("MODERATOR"));
		
		Board board1 = new Board("Main board");
		LOGGER.info("Board created: " + board1);
		Board board2 = new Board("Alternative board");
		LOGGER.info("Board created: " + board2);

		User user1 = new User("Admin", null);
		LOGGER.info("User created: " + user1);
		User user2 = new User("Moderator", null);
		LOGGER.info("User created: " + user2);
		
		Role adminRole = roleService.getRoleByName("ADMIN");
		Role moderatorRole = roleService.getRoleByName("MODERATOR");

		user1.addRole(adminRole);
		user1.addRole(moderatorRole);
		user2.addRole(moderatorRole);
		
		Topic topic1 = new Topic("General announcement", user1);
		LOGGER.info("Topic created: " + topic1);
		Topic topic2 = new Topic("To whom it may concern", user2);
		LOGGER.info("Topic created: " + topic2);

		Message message1 = new Message(user1, "Testing new topic");
		LOGGER.info("New message: " + message1);
		Message message2 = new Message(user2, "It's showtime!");
		LOGGER.info("New message: " + message2);

		topic1.addMessage(message1);
		topic2.addMessage(message2);

		board1.addTopic(topic1);
		board2.addTopic(topic2);

		boardService.saveBoard(board1);
		boardService.saveBoard(board2);

	}

}
