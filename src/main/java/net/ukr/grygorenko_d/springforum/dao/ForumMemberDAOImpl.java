package net.ukr.grygorenko_d.springforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

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
		}catch (NoResultException ex) {
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
		TypedQuery<ForumMember> query = entityManager
				.createQuery("SELECT f FROM ForumMember f JOIN FETCH f.roles WHERE f.username = :param", ForumMember.class);
		query.setParameter("param", username);
		ForumMember member;
		try {
		member = query.getSingleResult();
		}catch (NoResultException ex) {
			member = null;
		}
		return member;
	}

}
