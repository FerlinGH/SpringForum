package net.ukr.grygorenko_d.springforum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.dao.TopicDAO;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.helpers.LocalDateTimeAdapter;

@Service
public class MessageServiceImpl implements MessageService {

	private TopicDAO topicDAO;
	private ForumMemberDAO forumMemberDAO;

	@Autowired
	public MessageServiceImpl(TopicDAO topicDAO, ForumMemberDAO forumMemberDAO) {
		super();
		this.topicDAO = topicDAO;
		this.forumMemberDAO = forumMemberDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public Message prepareMessage(Message tempMessage) {
		User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = tempUser.getUsername();
		ForumMember messageCreator = forumMemberDAO.getMemberByNickname(login);
		tempMessage.setAuthor(messageCreator);
		tempMessage.setCreationTime(LocalDateTimeAdapter.describeCurrentTime());
		return tempMessage;
	}

	@Override
	@Transactional
	public void saveMessage(Message tempMessage, int topicId) {
		Topic topic = topicDAO.getTopicById(topicId);
		Message message = prepareMessage(tempMessage);
		message.setTopic(topic);
		topic.addMessage(message);

		topicDAO.saveTopic(topic);
	}

}
