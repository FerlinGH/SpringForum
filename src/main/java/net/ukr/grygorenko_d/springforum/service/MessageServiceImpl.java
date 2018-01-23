package net.ukr.grygorenko_d.springforum.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.dao.MessageDAO;
import net.ukr.grygorenko_d.springforum.dao.TopicDAO;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Role;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.helpers.LocalDateTimeAdapter;

@Service
public class MessageServiceImpl implements MessageService {

	private TopicDAO topicDAO;
	private ForumMemberDAO forumMemberDAO;
	private MessageDAO messageDAO;
	private static Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	public MessageServiceImpl(TopicDAO topicDAO, ForumMemberDAO forumMemberDAO, MessageDAO messageDAO) {
		super();
		this.topicDAO = topicDAO;
		this.forumMemberDAO = forumMemberDAO;
		this.messageDAO = messageDAO;
	}

	@Override
	public Message prepareMessage(Message tempMessage, ForumMember userRef) {
		tempMessage.setAuthor(userRef);
		LocalDateTime currentTime = LocalDateTime.now();
		ZoneOffset zoneOffset = ZoneId.of("UTC+2").getRules().getOffset(currentTime);
		tempMessage.setCreationTimeSec(currentTime.toEpochSecond(zoneOffset));
		tempMessage.setCreationTime(LocalDateTimeAdapter.describeTime(currentTime));
		return tempMessage;
	}

	@Override
	@Transactional
	public void saveMessage(Message tempMessage, int topicId, String action) {
		if (action.equals("new")) {
			System.out.println("Creating new message...");
			User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = tempUser.getUsername();
			ForumMember userRef = forumMemberDAO.getMemberRefByUsername(login);
			Message message = prepareMessage(tempMessage, userRef);

			Topic topic = topicDAO.getTopicById(topicId);
			topic.addMessage(message);

			topicDAO.saveTopic(topic);
			LOGGER.info("New message: " + message);
		} else {
			System.out.println("Updating the message...");
			int messageId = tempMessage.getId();
			User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = tempUser.getUsername();
			ForumMember forumMemberRef = forumMemberDAO.getUserRefferenceByMessageId(messageId);
			Topic topicRef = topicDAO.getTopicRefferenceByTopicId(topicId);
			tempMessage.setAuthor(forumMemberRef);
			tempMessage.setTopic(topicRef);
			tempMessage.setEditInfo(String.format("This message was last edited by %s at %s.", login,
					LocalDateTimeAdapter.describeTime(LocalDateTime.now())));
			messageDAO.saveMessage(tempMessage);
			LOGGER.info("Message updated: " + tempMessage);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Message getMessageById(int messageId) {
		return messageDAO.getMessageById(messageId);
	}

	@Override
	@Transactional
	public void updateMessage(Message message) {
		messageDAO.saveMessage(message);

	}

	@Override
	@Transactional(readOnly = true)
	public Message getMessageAndTopicByMessageId(int messageId) {
		return messageDAO.getMessageAndTopicByMessageId(messageId);
	}

	@Override
	@Transactional
	public void deleteMessage(int topicId, int messageId) {
		Topic topic = topicDAO.getTopicById(topicId);
		messageDAO.removeMessageById(messageId);
		LOGGER.info("Message removed, ID was " + messageId);
		topic.setSize((topic.getSize()) - 1);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkEditAuthority(Message tempMessage) {
		User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = tempUser.getUsername();
		ForumMember member = forumMemberDAO.getUserAndRolesByUsername(login);
		System.out.println("User extracted: " + member);
		Set<String> roles = new HashSet<>();
		for (Role role : member.getRoles()) {
			roles.add(role.getRoleType().toString());
		}
		System.out.println("User roles are " + roles);
		System.out.println("First is " + login);
		System.out.println("Message author is " + tempMessage.getAuthor().getUsername());

		if (roles.contains("MODERATOR")) {
			System.out.println("We have moderator role");
			return true;
		} else if (login.equalsIgnoreCase(tempMessage.getAuthor().getUsername())) {
			System.out.println("We are message creator");
			return true;
		} else {
			System.out.println("No permission to edit");
			return false;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Message getMessageAndAuthorAndTopicByMessageId(int messageId) {
		return messageDAO.getMessageAndAuthorAndTopicByMessageId(messageId);
	}

}
