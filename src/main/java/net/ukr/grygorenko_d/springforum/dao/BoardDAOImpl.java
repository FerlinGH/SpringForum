package net.ukr.grygorenko_d.springforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveBoard(Board board) {
		entityManager.merge(board);

	}

}
