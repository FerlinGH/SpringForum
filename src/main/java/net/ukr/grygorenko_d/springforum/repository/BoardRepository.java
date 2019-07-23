package net.ukr.grygorenko_d.springforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.grygorenko_d.springforum.entity.Board;


public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	@Query(value="SELECT b FROM Board b LEFT JOIN FETCH b.topics WHERE b.id = :param")
	public Board findWithTopicsById(@Param("param") int id);

}
