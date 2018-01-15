package net.ukr.grygorenko_d.springforum.service;

import net.ukr.grygorenko_d.springforum.entity.ForumMember;
import net.ukr.grygorenko_d.springforum.entity.Message;

public interface MessageService {

	public void saveMessage(Message message, int topicId, String action);

	public Message prepareMessage(Message tempMessage, ForumMember userRef);

	public Message getMessageById(int messageId);

	public void updateMessage(Message message);

	public Message getMessageAndTopicByMessageId(int messageId);

	

}
