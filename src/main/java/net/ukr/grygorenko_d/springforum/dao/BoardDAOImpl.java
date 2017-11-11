package net.ukr.grygorenko_d.springforum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		Query query = entityManager.createQuery("SELECT b from Board b ORDER BY id");
		List<Board> boards = new ArrayList<Board>();
		List<?> queryResults = query.getResultList();
		for (Object result : queryResults) {
			boards.add((Board) result);
		}
		return boards;
	}

	@Override
	public List<Topic> getAllTopics(int boardId) {
		TypedQuery<Board> query = entityManager.createQuery("SELECT b FROM Board b JOIN FETCH b.topics WHERE b.id = :param", Board.class);
		query.setParameter("param", boardId);
		Board board = query.getSingleResult();
		
		return board.getTopics();
	}

	@Override
	public Board getBoardById(int boardId) {
		Query query = entityManager.createQuery("SELECT b from Board b WHERE ( id = :param )");
		query.setParameter("param", boardId);
		Board board =(Board)query.getSingleResult();
		return board;
	}

}
