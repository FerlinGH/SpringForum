package net.ukr.grygorenko_d.springforum.service;

import java.util.List;
import java.util.Map;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface BoardService {

	public void saveBoard(Board board);

	public List<Board> listBoards();

	public List<Topic> listTopics(int boardId);

	public Board getBoardById(int boardId);

	public Map<Integer, String> generateTopicsMap(List<Topic> topicsList);

}