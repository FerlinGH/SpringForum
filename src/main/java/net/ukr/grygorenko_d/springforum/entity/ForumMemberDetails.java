package net.ukr.grygorenko_d.springforum.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "member_details")
public class ForumMemberDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToOne(mappedBy = "memberDetails", fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private ForumMember forumMember;

	public ForumMemberDetails() {
		super();
	}

	public ForumMemberDetails(String firstName, String lastName, String email, ForumMember forumMember) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.forumMember = forumMember;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ForumMember getForumMember() {
		return forumMember;
	}

	public void setVisitor(ForumMember forumMember) {
		this.forumMember = forumMember;
	}

	@Override
	public String toString() {
		return "ForumMemberDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", forumMember=" + forumMember + "]";
	}

}
