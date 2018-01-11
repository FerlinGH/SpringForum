package net.ukr.grygorenko_d.springforum.service;

import net.ukr.grygorenko_d.springforum.entity.Message;

public interface MessageService {

	public void saveMessage(Message message, int topicId, String action);

	Message prepareMessage(Message tempMessage);

	public Message getMessageById(int messageId);

	public void updateMessage(Message message);

	public Message getMessageAndTopicByMessageId(int messageId);

	

}
