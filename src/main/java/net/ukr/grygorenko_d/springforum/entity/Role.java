package net.ukr.grygorenko_d.springforum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "role_type")
	private String roleType;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "visotor_role", 
				joinColumns = @JoinColumn(name = "role_id"), 
				inverseJoinColumns = @JoinColumn(name = "visitor_id"))
	private List<ForumMember> visitors;

	public Role() {
		super();
	}

	public Role(String roleType) {
		super();
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<ForumMember> getVisitors() {
		return visitors;
	}

	public void setUsers(List<ForumMember> visitors) {
		this.visitors = visitors;
	}

	public void addUser(ForumMember visitor) {
		if (visitors == null) {
			visitors = new ArrayList<ForumMember>();
		}
		visitors.add(visitor);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleType=" + roleType + "]";
	}

}
