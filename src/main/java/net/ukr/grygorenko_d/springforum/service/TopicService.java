package net.ukr.grygorenko_d.springforum.service;

import java.util.List;
import java.util.Map;

import net.ukr.grygorenko_d.springforum.entity.Message;

public interface TopicService {

	public List<Message> listMessagesByTopicId(int topicId);

	public Map<Integer, String> generateMessagesMap(List<Message> topicMessagesList);

	public Object getTopicById(int topicId);

}
