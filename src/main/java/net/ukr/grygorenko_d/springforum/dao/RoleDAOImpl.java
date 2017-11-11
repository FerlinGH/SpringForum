package net.ukr.grygorenko_d.springforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createRole(Role role) {
		entityManager.merge(role);

	}

	@Override
	public Role getRoleByName(String roleName) {
		Query query = entityManager.createQuery("SELECT r FROM Role r WHERE (r.roleType = :role)", Role.class);
		query.setParameter("role", roleName);
		Role dbRole = (Role)query.getSingleResult();
		return dbRole;
	}

}