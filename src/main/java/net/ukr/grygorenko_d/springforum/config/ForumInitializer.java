package net.ukr.grygorenko_d.springforum.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
import net.ukr.grygorenko_d.springforum.service.ForumMemberService;
import net.ukr.grygorenko_d.springforum.service.RoleService;

//@Component
public class ForumInitializer {

	private BoardService boardService;
	private RoleService roleService;
	private ForumMemberService forumMemberService;
	private static Logger LOGGER = LoggerFactory.getLogger(ForumInitializer.class);

	

	@Autowired
	public ForumInitializer(BoardService boardService, RoleService roleService, ForumMemberService forumMemberService) {
		super();
		this.boardService = boardService;
		this.roleService = roleService;
		this.forumMemberService = forumMemberService;
	}

//	@PostConstruct
	public void initDatabase() {
		LocalDateTime currentTime;
		
		roleService.createRole(new Role(RoleTypes.ADMIN));
		roleService.createRole(new Role(RoleTypes.MODERATOR));
		roleService.createRole(new Role(RoleTypes.MEMBER));

		Board board1 = new Board("Main board");
		LOGGER.info("Board created: " + board1);
		Board board2 = new Board("Alternative board");
		LOGGER.info("Board created: " + board2);

		ForumMember user1 = new ForumMember("Admin", "$2a$10$ggzn.kG4nDHZGcMXVW0JweMMdfT41blJwLdP5vnYVw1EbYazJjgl6",
				null);
		LOGGER.info("User created: " + user1);
		ForumMember user2 = new ForumMember("Moderator",
				"$2a$10$ggzn.kG4nDHZGcMXVW0JweMMdfT41blJwLdP5vnYVw1EbYazJjgl6", null);
		LOGGER.info("User created: " + user2);

		
		Role adminRoleRef = roleService.getRoleRefByType(RoleTypes.ADMIN);
		Role moderatorRoleRef = roleService.getRoleRefByType(RoleTypes.MODERATOR);
		Role memberRoleRef = roleService.getRoleRefByType(RoleTypes.MEMBER);

		user1.addRole(memberRoleRef);
		user1.addRole(adminRoleRef);
		user2.addRole(memberRoleRef);
		user2.addRole(moderatorRoleRef);
		
		forumMemberService.saveProfile(user1);
		forumMemberService.saveProfile(user2);

		ForumMember adminRef = forumMemberService.getUserRefferenceById(1);
		ForumMember moderatorRef = forumMemberService.getUserRefferenceById(2);
		
		Topic topic1 = new Topic("General announcement", adminRef);
		LOGGER.info("Topic created: " + topic1);
		Topic topic2 = new Topic("To whom it may concern", moderatorRef);
		LOGGER.info("Topic created: " + topic2);
		Topic topic3 = new Topic("First topic in Alternative board", adminRef);
		LOGGER.info("Topic created: " + topic3);

		
	
		ZoneOffset zoneOffset = ZoneId.of("UTC+2").getRules().getOffset(LocalDateTime.now());
		Message message1 = new Message(adminRef, "Testing new topic");
		currentTime = LocalDateTime.now();
		message1.setCreationTimeSec(currentTime.toEpochSecond(zoneOffset));
		message1.setCreationTime(LocalDateTimeAdapter.describeTime(currentTime));
		LOGGER.info("New message: " + message1);
		Message message2 = new Message(adminRef, "Second message");
		currentTime = LocalDateTime.now();
		message2.setCreationTimeSec(currentTime.toEpochSecond(zoneOffset));
		message2.setCreationTime(LocalDateTimeAdapter.describeTime(currentTime));
		LOGGER.info("New message: " + message2);
		Message message3 = new Message(moderatorRef, "It's showtime!");
		currentTime = LocalDateTime.now();
		message3.setCreationTimeSec(currentTime.toEpochSecond(zoneOffset));
		message3.setCreationTime(LocalDateTimeAdapter.describeTime(currentTime));
		LOGGER.info("New message: " + message3);
		Message message4 = new Message(moderatorRef, "Message example");
		currentTime = LocalDateTime.now();
		message4.setCreationTimeSec(currentTime.toEpochSecond(zoneOffset));
		message4.setCreationTime(LocalDateTimeAdapter.describeTime(currentTime));
		LOGGER.info("New message: " + message4);

		topic1.addMessage(message1);
		topic1.addMessage(message2);
		topic2.addMessage(message3);
		topic3.addMessage(message4);

		board1.addTopic(topic1);
		board1.addTopic(topic2);
		board2.addTopic(topic3);

		boardService.saveBoard(board1);
		boardService.saveBoard(board2);

	}

}