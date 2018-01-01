package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface TopicDAO {

	public Topic getTopicById(int topicId);

	public void saveTopic(Topic topic);

	public List<Topic> getTopicsWithAuthorsByBoard(Board board);

}
