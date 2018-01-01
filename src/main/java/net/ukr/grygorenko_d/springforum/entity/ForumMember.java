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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class ForumMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "member_details_id")
	private ForumMemberDetails memberDetails;

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Topic> topics;

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Message> messages;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
		@JoinTable(name = "member_role", 
			joinColumns = @JoinColumn(name = "member_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	public ForumMember() {
		super();
		roles = new ArrayList<Role>();
	}

	public ForumMember(String username, String password, ForumMemberDetails memberDetails) {
		super();
		this.username = username;
		this.password = password;
		this.memberDetails = memberDetails;
		roles = new ArrayList<Role>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ForumMemberDetails getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(ForumMemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	@Override
	public String toString() {
		return "Forum Member [id=" + id + ", username=" + username + "]";
	}
}