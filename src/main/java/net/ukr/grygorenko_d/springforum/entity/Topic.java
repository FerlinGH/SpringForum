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

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private ForumMember author;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "board_id")
	private Board board;

	@OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> messages;

	@Column(name = "size")
	private int size;

	@Column(name = "last_message")
	private String lastMessageTime;
	
	@Column(name = "last_message_sec")
	private long lastMessageTimeSec;

	public Topic() {
		super();
		messages = new ArrayList<Message>();
	}

	public Topic(String title, ForumMember author) {
		super();
		this.title = title;
		this.author = author;
		messages = new ArrayList<Message>();
	}

	// public Topic(String title, User author, Board board) {
	// super();
	// this.title = title;
	// this.author = author;
	// this.board = board;
	// }

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

	public ForumMember getAuthor() {
		return author;
	}

	public void setAuthor(ForumMember author) {
		this.author = author;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getLastMessageTime() {
		return lastMessageTime;
	}

	public void setLastMessageTime(String lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}
	
	

	public long getLastMessageTimeSec() {
		return lastMessageTimeSec;
	}

	public void setLastMessageTimeMilis(long lastMessageTimeSec) {
		this.lastMessageTimeSec = lastMessageTimeSec;
	}

	public void addMessage(Message message) {
		if ((message.getTopicTitle() == null)) {
			message.setTopicTitle(this.getTitle());
		} else if (!message.getTopicTitle().equals(this.getTitle())) {
			message.setTopicTitle(this.getTitle());
		}
		message.setTopic(this);
		messages.add(message);
		size++;
		lastMessageTime = message.getCreationTime();
		lastMessageTimeSec = message.getCreationTimeSec();
	}

	@Override
	public String toString() {
		return "Topic [title=" + title + ", size=" + size + ", lastMessageTime=" + lastMessageTime
				+ ", lastMessageTimeSec=" + lastMessageTimeSec + "]";
	}

	
}
