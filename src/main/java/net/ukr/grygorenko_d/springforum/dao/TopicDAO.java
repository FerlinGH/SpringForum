package net.ukr.grygorenko_d.springforum.dao;

import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface TopicDAO {

	public String getCreatorsNicknameByTopic(Topic topic);

}
