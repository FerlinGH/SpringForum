package net.ukr.grygorenko_d.springforum.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.helpers.LocalDateTimeAdapter;

@Service
public class MessageServiceImpl implements MessageService {

	private TopicDAO topicDAO;
	private ForumMemberDAO forumMemberDAO;
	private MessageDAO messageDAO;

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
		topic.setSize((topic.getSize())-1);
	}

}
