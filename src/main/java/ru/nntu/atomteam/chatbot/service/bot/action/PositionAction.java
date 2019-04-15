package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.service.RosatomResourceClient;

@Component
public class PositionAction implements Action {
	private final RosatomResourceClient client;

	public PositionAction(RosatomResourceClient client) {
		this.client = client;
	}

	@Override
	public String run(String message) {
		return client.getPosition(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@Override
	public String failMessage() {
		return "Не удалось получить текущую должность.";
	}
}
