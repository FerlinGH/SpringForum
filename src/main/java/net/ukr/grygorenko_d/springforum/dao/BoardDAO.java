package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import net.ukr.grygorenko_d.springforum.entity.Board;

public interface BoardDAO {

	public void saveBoard(Board board);

	public List<Board> getAllBoards();

	public Board getBoardById(int boardId);

	public Board getBoardAndTopicsByBoarId(int boardId);

}
