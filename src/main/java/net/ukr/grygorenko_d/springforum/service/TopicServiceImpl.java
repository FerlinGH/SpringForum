package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.springforum.dao.MessageDAO;
import net.ukr.grygorenko_d.springforum.dao.TopicDAO;
import net.ukr.grygorenko_d.springforum.entity.Message;

@Service
public class TopicServiceImpl implements TopicService {

	private TopicDAO topicDAO;
	private MessageDAO messageDAO;

	public TopicServiceImpl() {
		super();
	}

	@Autowired
	public TopicServiceImpl(TopicDAO topicDAO, MessageDAO messageDAO) {
		super();
		this.topicDAO = topicDAO;
		this.messageDAO = messageDAO;
	}

	@Override
	@Transactional
	public List<Message> listMessagesByTopicId(int topicId) {

		return topicDAO.getAllMessagesByTopicId(topicId);
	}

	@Override
	@Transactional
	public Map<Integer, String> generateMessagesMap(List<Message> topicMessagesList) {

		Map<Integer, String> topicMessagesMap = new HashMap<>();
		for (Message tempMessage : topicMessagesList) {
			topicMessagesMap.put(tempMessage.getId(), messageDAO.getMessageCreatorNickname(tempMessage));
		}

		return topicMessagesMap;
	}

	@Override
	@Transactional
	public Object getTopicById(int topicId) {
		return topicDAO.getTopicById(topicId);
	}

}
