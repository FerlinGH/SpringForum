package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface BoardDAO {

	public void saveBoard(Board board);

	public List<Board> getAllBoards();

	public List<Topic> getAllTopics(int boardId);

	public Board getBoardById(int boardId);

}
