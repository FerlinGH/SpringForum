package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveBoard(Board board) {
		entityManager.merge(board);

	}

	@Override
	public List<Board> getAllBoards() {
		TypedQuery<Board> query = entityManager
				.createQuery("SELECT b from Board b ORDER BY id", Board.class);
		List<Board> boards = query.getResultList();
		return boards;
	}

	@Override
	public List<Topic> getAllTopics(int boardId) {
		TypedQuery<Board> query = entityManager
				.createQuery("SELECT b FROM Board b JOIN FETCH b.topics WHERE b.id = :param", Board.class);
		query.setParameter("param", boardId);
		Board board = query.getSingleResult();

		return board.getTopics();
	}

	@Override
	public Board getBoardById(int boardId) {
		TypedQuery<Board> query = entityManager
				.createQuery("SELECT b from Board b WHERE ( id = :param )", Board.class);
		query.setParameter("param", boardId);
		Board board = query.getSingleResult();
		return board;
	}

}
