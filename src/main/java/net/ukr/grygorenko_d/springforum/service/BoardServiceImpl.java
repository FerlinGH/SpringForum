package net.ukr.grygorenko_d.springforum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Transactional
	public List<Board> listBoards() {
		return boardDAO.getAllBoards();
	}

	@Override
	@Transactional()
	public List<Topic> listTopics(int boardId) {
		return boardDAO.getAllTopics(boardId);
	}

	@Override
	@Transactional
	public Board getBoardById(int boardId) {
		return boardDAO.getBoardById(boardId);
	}

	@Override
	@Transactional
	public Map<Integer, String> generateTopicsMap(List<Topic> topicsList) {
		Map<Integer, String> topicsMap = new HashMap<>();
		for (Topic tempTopic : topicsList) {
			topicsMap.put(tempTopic.getId(), topicDAO.getCreatorsNicknameByTopic(tempTopic));
		}

		return topicsMap;
	}

}
