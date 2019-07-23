package net.ukr.grygorenko_d.springforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("SELECT m FROM Message m JOIN FETCH m.author WHERE m.topic = :topic")
	public List<Message> findWithAuthorByTopic(@Param("topic") Topic topic);

	@Query("SELECT m FROM Message m JOIN FETCH m.topic WHERE m.id = :id")
	public Message findWithTopicById(@Param("id") int messageId);

	@Query("SELECT m FROM Message m JOIN FETCH m.author JOIN FETCH m.topic WHERE m.id = :id")
	public Message findFullById(@Param("id") int messageId);

}
