package net.ukr.grygorenko_d.springforum.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.springforum.dao.BoardDAO;
import net.ukr.grygorenko_d.springforum.entity.Board;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;

	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO) {
		super();
		this.boardDAO = boardDAO;
	}

	@Override
	@Transactional
	public void saveBoard(Board board) {
		boardDAO.saveBoard(board);

	}

}
