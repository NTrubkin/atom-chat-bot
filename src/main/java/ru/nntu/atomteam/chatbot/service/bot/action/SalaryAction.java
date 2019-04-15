package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.service.RosatomResourceClient;

@Component
public class SalaryAction implements Action {
	private final RosatomResourceClient client;

	public SalaryAction(RosatomResourceClient client) {
		this.client = client;
	}

	@Override
	public String run(String message) {
		return client.getSalary(SecurityContextHolder.getContext().getAuthentication().getName()) + "";
	}

	@Override
	public String failMessage() {
		return "Не удалось получить текущую зарплату.";
	}
}
