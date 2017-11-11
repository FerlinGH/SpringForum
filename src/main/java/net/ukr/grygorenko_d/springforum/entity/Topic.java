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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "topics")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User author;
	
	@OneToMany(mappedBy="topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> messages;
	
	
	// consider if we need to have the topic's size: 
	// private int topicSize;
	
	public Topic() {
		super();
	}



	public Topic(String title, User author) {
		super();
		this.title = title;
		this.author = author;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public User getAuthor() {
		return author;
	}



	public void setAuthor(User author) {
		this.author = author;
	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
	
	public void addMessage(Message message) {
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		message.setTopic(this);
		messages.add(message);
	}



	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + "]";
	}
	
	
	
	

}
