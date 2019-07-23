package net.ukr.grygorenko_d.springforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;

public interface ForumMemberRepository extends JpaRepository<ForumMember, Integer> {

	public ForumMember findByUsername(String username);

	@Query("SELECT f FROM ForumMember f JOIN FETCH f.roles WHERE f.username = :username")
	public ForumMember findWithRolesByUsername(@Param("username") String username);

}
