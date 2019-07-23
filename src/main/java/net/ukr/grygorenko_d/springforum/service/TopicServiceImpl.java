package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.repository.BoardRepository;
import net.ukr.grygorenko_d.springforum.repository.MessageRepository;
import net.ukr.grygorenko_d.springforum.repository.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {

	private MessageRepository messageRepository;
	private BoardRepository boardRepository;
	private TopicRepository topicRepository;
	private static Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

	public TopicServiceImpl() {
		super();
	}

	@Autowired
	public TopicServiceImpl(MessageRepository messageRepository, BoardRepository boardRepository,
			TopicRepository topicRepository) {
		super();
		this.messageRepository = messageRepository;
		this.boardRepository = boardRepository;
		this.topicRepository = topicRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Message> getMessagesWithAuthorsByTopicId(int topicId) {
		Topic tempTopic = getTopicById(topicId);
		return messageRepository.findWithAuthorByTopic(tempTopic);
	}

	@Override
	@Transactional(readOnly = true)
	public Topic getTopicById(int topicId) {
		Topic topic = null;
		Optional<Topic> findById = topicRepository.findById(topicId);
		if (findById.isPresent()) {
			topic = findById.get();
		}
		return topic;
	}

	@Override
	@Transactional
	public void saveNewTopic(int boardId, String topicName, Message message, ForumMember userRef) {
		Board board = boardRepository.findWithTopicsById(boardId);
		Topic tempTopic = new Topic();
		Topic topic = prepareTopic(tempTopic, topicName, userRef);

		topic.addMessage(message);
		board.addTopic(topic);

		boardRepository.save(board);
		LOGGER.info("New topic created: " + topic);
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

	@Override
	@Transactional
	public void deleteTopicById(int topicId) {
		topicRepository.deleteById(topicId);
		LOGGER.info("Topic deleted, ID was " + topicId);

	}

	@Override
	@Transactional
	public void updateTopic(Topic topic) {
		topicRepository.save(topic);
		LOGGER.info("Topic renamed: " + topic);

	}

	@Override
	@Transactional(readOnly = true)
	public Topic getFullTopicById(int topicId) {
		return topicRepository.findFullById(topicId);
	}

}
