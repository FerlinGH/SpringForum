package net.ukr.grygorenko_d.springforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.grygorenko_d.springforum.entity.Board;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

	@Query("SELECT t FROM Topic t JOIN FETCH t.author WHERE t.board = :board ORDER BY t.lastMessageTimeSec DESC")
	public List<Topic> findWithAuthorByBoard(@Param("board") Board board);

	@Query("SELECT t FROM Topic t JOIN FETCH t.author JOIN FETCH t.board JOIN FETCH t.messages WHERE t.id = :id")
	public Topic findFullById(@Param("id") int topicId);

}
