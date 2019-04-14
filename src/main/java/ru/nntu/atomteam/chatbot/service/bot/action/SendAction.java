package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.stereotype.Component;

@Component
public class SendAction implements Action {
	@Override
	public String run() {
		return "{send!}";
	}

	@Override
	public String failMessage() {
		return "Не удалось отправить сообщение";
	}
}
