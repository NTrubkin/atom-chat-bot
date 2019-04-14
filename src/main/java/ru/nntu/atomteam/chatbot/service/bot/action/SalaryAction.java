package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.stereotype.Component;

@Component
public class SalaryAction implements Action {
	@Override
	public String run() {
		return "999";
	}

	@Override
	public String failMessage() {
		return "Не удалось получить текущую зарплату.";
	}
}
