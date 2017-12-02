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

	@Column(name = "nickname")
	private String nickname;

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
	}

	public ForumMember(String nickname, ForumMemberDetails memberDetails) {
		super();
		this.nickname = nickname;
		this.memberDetails = memberDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ForumMemberDetails getMemberDetails() {
		return memberDetails;
	}

	public void setUserDetails(ForumMemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}

	@Override
	public String toString() {
		return "Forum Member [id=" + id + ", nickname=" + nickname + "]";
	}
}