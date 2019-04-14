package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.stereotype.Component;

@Component
public class PositionAction implements Action {
	@Override
	public String run() {
		return "engineer";
	}

	@Override
	public String failMessage() {
		return "Не удалось получить текущую должность.";
	}
}
