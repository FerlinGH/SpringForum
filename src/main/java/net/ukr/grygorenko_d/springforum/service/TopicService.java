package net.ukr.grygorenko_d.springforum.service;

import java.util.List;
import java.util.Map;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface TopicService {

	public List<Message> listMessagesByTopicId(int topicId);

	public Map<Integer, String> generateMessagesMap(List<Message> topicMessagesList);

	public Topic getTopicById(int topicId);

	public void saveNewTopic(int boardId, String topicName, Message message);

	public Topic prepareTopic(Topic tempTopic, String topicName);

}
