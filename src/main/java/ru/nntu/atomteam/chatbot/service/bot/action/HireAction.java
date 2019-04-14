package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.stereotype.Component;

@Component
public class HireAction implements Action {
	@Override
	public String run() {
		return "01-01-1970";
	}

	@Override
	public String failMessage() {
		return "Не удалось получить дату найма.";
	}
}
