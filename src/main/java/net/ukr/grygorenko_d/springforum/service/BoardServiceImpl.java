package net.ukr.grygorenko_d.springforum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;
import net.ukr.grygorenko_d.springforum.repository.BoardRepository;
import net.ukr.grygorenko_d.springforum.repository.TopicRepository;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardRepository boardRepository;
	private TopicRepository topicRepository;

	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository, TopicRepository topicRepository) {
		super();
		this.boardRepository = boardRepository;
		this.topicRepository = topicRepository;
	}

	@Override
	@Transactional
	public void saveBoard(Board board) {
		boardRepository.save(board);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Board> getBoards() {
		return boardRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Topic> listTopicsWithAuthorsByBoardId(int boardId) {
		Board board = getBoardById(boardId);
		return topicRepository.findWithAuthorByBoard(board);
	}

	@Override
	@Transactional(readOnly = true)
	public Board getBoardById(int boardId) {
		Board board = null;
		Optional<Board> findById = boardRepository.findById(boardId);
		if (findById.isPresent()) {
			board = findById.get();
		}
		return board;
	}

}
