package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

@Repository
public class TopicDAOImpl implements TopicDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String getCreatorsNicknameByTopic(Topic topic) {
		int id = topic.getId();
		TypedQuery<Topic> query = entityManager
				.createQuery("SELECT t FROM Topic t JOIN FETCH t.author WHERE t.id = :param", Topic.class);
		query.setParameter("param", id);
		Topic tempTopic = query.getSingleResult();
		return tempTopic.getAuthor().getNickname();
	}

	@Override
	public List<Message> getAllMessagesByTopicId(int topicId) {
		TypedQuery<Topic> query = entityManager
				.createQuery("SELECT t FROM Topic t JOIN FETCH t.messages WHERE t.id = :param", Topic.class);
		query.setParameter("param", topicId);
		Topic tempTopic = query.getSingleResult();
		return tempTopic.getMessages();
	}

	@Override
	public Topic getTopicById(int topicId) {
		TypedQuery<Topic> query = entityManager
				.createQuery("SELECT t FROM Topic t WHERE t.id = :param", Topic.class);
		query.setParameter("param", topicId);
		Topic topic = query.getSingleResult();
		return topic;
	}

	@Override
	public void saveTopic(Topic topic) {
		entityManager.merge(topic);
		
	}

}
