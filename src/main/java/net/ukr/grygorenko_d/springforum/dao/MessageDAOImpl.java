package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveMessage(Message message) {
		entityManager.merge(message);

	}

	@Override
	public List<Message> getMessagesWithAuthorsByTopic(Topic topic) {
		TypedQuery<Message> query = entityManager
				.createQuery("SELECT m FROM Message m JOIN FETCH m.author WHERE m.topic = :param", Message.class);
		query.setParameter("param", topic);
		List<Message> messageList = query.getResultList();
		return messageList;
	}

	@Override
	public Message getMessageById(int messageId) {
		Message message = entityManager.find(Message.class, messageId);
		return message;
	}

	@Override
	public Message getMessageAndTopicByMessageId(int messageId) {
		TypedQuery<Message> query = entityManager
				.createQuery("SELECT m FROM Message m JOIN FETCH m.topic WHERE m.id = :param", Message.class);
		query.setParameter("param", messageId);
		Message message = query.getSingleResult();
		return message;
	}

	@Override
	public void removeMessageById(int messageId) {
		Message message = entityManager.find(Message.class, messageId);
		entityManager.remove(message);
	}

	@Override
	public Message getMessageAndAuthorAndTopicByMessageId(int messageId) {
		TypedQuery<Message> query = entityManager.createQuery(
				"SELECT m FROM Message m JOIN FETCH m.author JOIN FETCH m.topic WHERE m.id = :param", Message.class);
		query.setParameter("param", messageId);
		Message message = query.getSingleResult();
		return message;
	}

}
