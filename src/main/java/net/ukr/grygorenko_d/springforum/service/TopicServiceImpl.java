package net.ukr.grygorenko_d.springforum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.BoardDAO;
import net.ukr.grygorenko_d.springforum.dao.ForumMemberDAO;
import net.ukr.grygorenko_d.springforum.dao.MessageDAO;
import net.ukr.grygorenko_d.springforum.dao.TopicDAO;
import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

@Service
public class TopicServiceImpl implements TopicService {

	private TopicDAO topicDAO;
	private MessageDAO messageDAO;
	private ForumMemberDAO forumMemberDAO;
	private BoardDAO boardDAO;

	public TopicServiceImpl() {
		super();
	}

	@Autowired
	public TopicServiceImpl(TopicDAO topicDAO, MessageDAO messageDAO, ForumMemberDAO forumMemberDAO,
			BoardDAO boardDAO) {
		super();
		this.topicDAO = topicDAO;
		this.messageDAO = messageDAO;
		this.forumMemberDAO = forumMemberDAO;
		this.boardDAO = boardDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Message> getMessagesWithAuthorsByTopicId(int topicId) {
		Topic tempTopic = topicDAO.getTopicById(topicId);
		return messageDAO.getMessagesWithAuthorsByTopic(tempTopic);
	}

	@Override
	@Transactional(readOnly = true)
	public Topic getTopicById(int topicId) {
		return topicDAO.getTopicById(topicId);
	}

	@Override
	@Transactional
	public void saveNewTopic(int boardId, String topicName, Message message) {
		Board board = boardDAO.getBoardAndTopicsByBoarId(boardId);
		Topic tempTopic = new Topic();
		Topic topic = prepareTopic(tempTopic, topicName);

		topic.addMessage(message);
		message.setTopic(topic);
		board.addTopic(topic);
		topic.setBoard(board);

		boardDAO.saveBoard(board);
	}

	@Override
	@Transactional(readOnly = true)
	public Topic prepareTopic(Topic tempTopic, String topicName) {
		User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = tempUser.getUsername();
		ForumMember messageCreator = forumMemberDAO.getMemberByUsername(login);
		tempTopic.setAuthor(messageCreator);
		tempTopic.setTitle(topicName);
		return tempTopic;
	}

}
