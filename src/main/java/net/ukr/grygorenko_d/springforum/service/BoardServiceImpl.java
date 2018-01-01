package net.ukr.grygorenko_d.springforum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.dao.BoardDAO;
import net.ukr.grygorenko_d.springforum.dao.TopicDAO;
import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;
	private TopicDAO topicDAO;

	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO, TopicDAO topicDAO) {
		super();
		this.boardDAO = boardDAO;
		this.topicDAO = topicDAO;
	}

	@Override
	@Transactional
	public void saveBoard(Board board) {
		boardDAO.saveBoard(board);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Board> listBoards() {
		return boardDAO.getAllBoards();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Topic> listTopicsWithAuthorsByBoardId(int boardId) {
		Board board = boardDAO.getBoardById(boardId);
		return topicDAO.getTopicsWithAuthorsByBoard(board);
	}

	@Override
	@Transactional(readOnly = true)
	public Board getBoardById(int boardId) {
		return boardDAO.getBoardById(boardId);
	}

}
