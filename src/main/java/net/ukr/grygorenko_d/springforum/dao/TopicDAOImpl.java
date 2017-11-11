package net.ukr.grygorenko_d.springforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Topic;

@Repository
public class TopicDAOImpl implements TopicDAO {

	@PersistenceContext
	private EntityManager entitymanager;

	@Override
	public String getCreatorsNicknameByTopic(Topic topic) {
		int id = topic.getId();
		TypedQuery<Topic> query = entitymanager
				.createQuery("SELECT t FROM Topic t JOIN FETCH t.author WHERE t.id = :param", Topic.class);
		query.setParameter("param", id);
		Topic tempTopic = query.getSingleResult();
		return tempTopic.getAuthor().getNickname();
	}

}
