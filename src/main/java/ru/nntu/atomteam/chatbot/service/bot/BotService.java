package ru.nntu.atomteam.chatbot.service.bot;

import ru.nntu.atomteam.chatbot.model.entity.Message;

public interface BotService {
	void reply(Message message);
}
