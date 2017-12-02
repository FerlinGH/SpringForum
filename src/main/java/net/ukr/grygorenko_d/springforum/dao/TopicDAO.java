package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface TopicDAO {

	public String getCreatorsNicknameByTopic(Topic topic);

	public List<Message> getAllMessagesByTopicId(int topicId);

	public Topic getTopicById(int topicId);

}
