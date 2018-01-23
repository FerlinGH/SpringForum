package net.ukr.grygorenko_d.springforum.dao;

import java.util.List;

import net.ukr.grygorenko_d.springforum.entity.Message;
import net.ukr.grygorenko_d.springforum.entity.Topic;

public interface MessageDAO {

	public List<Message> getMessagesWithAuthorsByTopic(Topic topic);
	
	public void saveMessage(Message message);

	public Message getMessageById(int messageId);

	public Message getMessageAndTopicByMessageId(int messageId);

	public void removeMessageById(int messageId);

	public Message getMessageAndAuthorAndTopicByMessageId(int messageId);

}
