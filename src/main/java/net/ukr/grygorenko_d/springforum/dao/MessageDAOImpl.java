package net.ukr.grygorenko_d.springforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String getMessageCreatorNickname(Message message) {
		int id = message.getId();
		TypedQuery<Message> query = entityManager
				.createQuery("SELECT m FROM Message m JOIN FETCH m.author WHERE m.id = :param", Message.class);
		query.setParameter("param", id);
		Message tempMessage = query.getSingleResult();
		return tempMessage.getAuthor().getNickname();
	}

	@Override
	public void saveMessage(Message message) {
		entityManager.merge(message);
		
	}

}
