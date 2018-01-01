package net.ukr.grygorenko_d.springforum.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.RoleTypes;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.helpers.LocalDateTimeAdapter;
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
		roleService.createRole(new Role(RoleTypes.ADMIN));
		roleService.createRole(new Role(RoleTypes.MODERATOR));
		roleService.createRole(new Role(RoleTypes.MEMBER));

		Board board1 = new Board("Main board");
		LOGGER.info("Board created: " + board1);
		Board board2 = new Board("Alternative board");
		LOGGER.info("Board created: " + board2);

		ForumMember user1 = new ForumMember("Admin", "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
				null);
		LOGGER.info("User created: " + user1);
		ForumMember user2 = new ForumMember("Moderator",
				"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", null);
		LOGGER.info("User created: " + user2);

		Role adminRole = roleService.getRoleByType(RoleTypes.ADMIN);
		Role moderatorRole = roleService.getRoleByType(RoleTypes.MODERATOR);

		user1.addRole(adminRole);
		user1.addRole(moderatorRole);
		user2.addRole(moderatorRole);

		Topic topic1 = new Topic("General announcement", user1);
		LOGGER.info("Topic created: " + topic1);
		Topic topic2 = new Topic("To whom it may concern", user2);
		LOGGER.info("Topic created: " + topic2);
		Topic topic3 = new Topic("First topic in Alternative board", user1);
		LOGGER.info("Topic created: " + topic3);

		Message message1 = new Message(user1, "Testing new topic");
		message1.setCreationTime(LocalDateTimeAdapter.describeCurrentTime());
		LOGGER.info("New message: " + message1);
		Message message2 = new Message(user2, "It's showtime!");
		message2.setCreationTime(LocalDateTimeAdapter.describeCurrentTime());
		LOGGER.info("New message: " + message2);
		Message message3 = new Message(user1, "Second message");
		message3.setCreationTime(LocalDateTimeAdapter.describeCurrentTime());
		LOGGER.info("New message: " + message3);
		Message message4 = new Message(user2, "Message example");
		message4.setCreationTime(LocalDateTimeAdapter.describeCurrentTime());
		LOGGER.info("New message: " + message4);

		topic1.addMessage(message1);
		topic2.addMessage(message2);
		topic1.addMessage(message3);
		topic3.addMessage(message4);

		board1.addTopic(topic1);
		board1.addTopic(topic2);
		board2.addTopic(topic3);

		boardService.saveBoard(board1);
		boardService.saveBoard(board2);

	}

}
