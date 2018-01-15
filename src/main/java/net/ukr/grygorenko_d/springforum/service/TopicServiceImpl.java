package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.BoardDAO;
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
	private BoardDAO boardDAO;

	public TopicServiceImpl() {
		super();
	}

	@Autowired
	public TopicServiceImpl(TopicDAO topicDAO, MessageDAO messageDAO, BoardDAO boardDAO) {
		super();
		this.topicDAO = topicDAO;
		this.messageDAO = messageDAO;
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
	public void saveNewTopic(int boardId, String topicName, Message message, ForumMember userRef) {
		Board board = boardDAO.getBoardAndTopicsByBoarId(boardId);
		Topic tempTopic = new Topic();
		Topic topic = prepareTopic(tempTopic, topicName, userRef);

		topic.addMessage(message);
		board.addTopic(topic);

		boardDAO.saveBoard(board);
	}

	@Override
	public Topic prepareTopic(Topic tempTopic, String topicName, ForumMember userRef) {
		tempTopic.setAuthor(userRef);
		tempTopic.setTitle(topicName);
		return tempTopic;
	}

	@Override
	public Map<Boolean, String> validatetopic(String topicName, Message message) {
		Map<Boolean, String> validationStatus = new HashMap<Boolean, String>();
		if (topicName.equals("")) {
			validationStatus.put(false, "Topic name cannot be emtpy!");
			return validationStatus;
		} else if (message.getMessageBody().equals("")) {
			validationStatus.put(false, "Empty message not allowed!");
			return validationStatus;
		} else {
			validationStatus.put(true, "Successfull validation.");
			return validationStatus;
		}
	}

}
