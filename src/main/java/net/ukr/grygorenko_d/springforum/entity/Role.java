package net.ukr.grygorenko_d.springforum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "role_type")
	@Enumerated(EnumType.STRING)
	private RoleTypes roleType;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
	private List<ForumMember> members;

	public Role() {
		super();
		members = new ArrayList<>();
	}

	public Role(RoleTypes roleType) {
		super();
		this.roleType = roleType;
		members = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMembers(List<ForumMember> members) {
		this.members = members;
	}

	public RoleTypes getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleTypes roleType) {
		this.roleType = roleType;
	}

	public List<ForumMember> getMembers() {
		return members;
	}

	public void addUser(ForumMember member) {
		members.add(member);
	}

	@Override
	public String toString() {
		return "ROLE_" + roleType.toString();
	}

}
