package net.ukr.grygorenko_d.springforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;

@Repository
public class ForumMemberDAOImpl implements ForumMemberDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public ForumMemberDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public ForumMemberDAOImpl() {
		super();
	}

	@Override
	public ForumMember getMemberByUsername(String username) {
		TypedQuery<ForumMember> query = entityManager
				.createQuery("SELECT f FROM ForumMember f WHERE f.username = :param", ForumMember.class);
		query.setParameter("param", username);
		ForumMember member;
		try {
			member = query.getSingleResult();
		} catch (NoResultException ex) {
			member = null;
		}
		return member;
	}

	@Override
	public void saveMember(ForumMember forumMember) {
		entityManager.merge(forumMember);

	}

	@Override
	public ForumMember getUserAndRolesByUsername(String username) {
		TypedQuery<ForumMember> query = entityManager.createQuery(
				"SELECT f FROM ForumMember f JOIN FETCH f.roles WHERE f.username = :param", ForumMember.class);
		query.setParameter("param", username);
		ForumMember member;
		try {
			member = query.getSingleResult();
		} catch (NoResultException ex) {
			member = null;
		}
		return member;
	}

	@Override
	public boolean existsByUsername(String username) {
		TypedQuery<Boolean> query = entityManager.createQuery(
				"SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM ForumMember f WHERE f.username = :param",
				Boolean.class);
		query.setParameter("param", username);
		Boolean result = query.getSingleResult();
		return result;
	}

	@Override
	public ForumMember getUserRefferenceById(int id) {
		ForumMember result = entityManager.getReference(ForumMember.class, id);
		return result;
	}

	@Override
	public ForumMember getUserRefferenceByMessageId(int messageId) {
		TypedQuery<Message> query = entityManager.createQuery(
				"SELECT m FROM Message m JOIN FETCH m.author WHERE m.id = :param", Message.class);
		query.setParameter("param", messageId);
		Message tempMessage = query.getSingleResult();
		ForumMember forumMemberRef = entityManager.getReference(ForumMember.class, tempMessage.getAuthor().getId());
		return forumMemberRef;
	}

}
