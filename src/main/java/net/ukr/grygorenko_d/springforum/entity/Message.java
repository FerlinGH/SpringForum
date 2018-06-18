package net.ukr.grygorenko_d.springforum.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "topic_title")
	private String topicTitle;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private ForumMember author;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@Column(name = "created")
	private String creationTime;

	@Column(name = "created_sec")
	private long creationTimeSec;

	@Column(name = "message_body", length = 15000)
	private String messageBody;

	@Column(name = "edit_info")
	private String editInfo;

	public Message() {
		super();
	}

	public Message(ForumMember author, String messageBody) {
		super();
		this.author = author;
		this.messageBody = messageBody;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public ForumMember getAuthor() {
		return author;
	}

	public void setAuthor(ForumMember author) {
		this.author = author;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public long getCreationTimeSec() {
		return creationTimeSec;
	}

	public void setCreationTimeSec(long creationTimeSec) {
		this.creationTimeSec = creationTimeSec;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getEditInfo() {
		return editInfo;
	}

	public void setEditInfo(String editInfo) {
		this.editInfo = editInfo;
	}

	@Override
	public String toString() {
		return "Message [topicTitle=" + topicTitle + ", creationTime=" + creationTime + ", creationTimeSec="
				+ creationTimeSec + ", messageBody=" + messageBody + "]";
	}

	

}