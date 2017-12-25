package net.ukr.grygorenko_d.springforum.dao;

import net.ukr.grygorenko_d.springforum.entity.Message;

public interface MessageDAO {

	public String getMessageCreatorNickname(Message message);

	public void saveMessage(Message message);

}
