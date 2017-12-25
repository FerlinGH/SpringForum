package net.ukr.grygorenko_d.springforum.service;

import net.ukr.grygorenko_d.springforum.entity.Message;

public interface MessageService {

	public void saveMessage(Message message, int topicId);

	Message prepareMessage(Message tempMessage);

	

}
