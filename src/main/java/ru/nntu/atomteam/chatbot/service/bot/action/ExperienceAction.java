package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.stereotype.Component;

@Component
public class ExperienceAction implements Action {
	@Override
	public String run() {
		return "42";
	}

	@Override
	public String failMessage() {
		return "Не удалось вычислить текущий стаж.";
	}
}
