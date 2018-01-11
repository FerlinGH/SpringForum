package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

@Repository
public class TopicDAOImpl implements TopicDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Topic getTopicById(int topicId) {
		Topic topic = entityManager.find(Topic.class, topicId);
		return topic;
	}

	@Override
	public void saveTopic(Topic topic) {
		entityManager.merge(topic);

	}

	@Override
	public List<Topic> getTopicsWithAuthorsByBoard(Board board) {
		TypedQuery<Topic> query = entityManager.createQuery(
				"SELECT t FROM Topic t JOIN FETCH t.author WHERE t.board = :param ORDER BY t.lastMessageTimeSec DESC",
				Topic.class);
		query.setParameter("param", board);
		List<Topic> topicsList = query.getResultList();

		return topicsList;
	}

	@Override
	public Topic getTopicRefferenceByTopicId(int topicId) {
		Topic topicRef = entityManager.getReference(Topic.class, topicId);
		return topicRef;
	}

}
