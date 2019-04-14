package ru.nntu.atomteam.chatbot.service.bot.action;

import ru.nntu.atomteam.chatbot.model.dto.MessageDto;

import java.util.Map;

public class ActionService {
	private final Map<String, Action> actions;

	public ActionService(Map<String, Action> actions) {
		this.actions = actions;
	}

	public void handleReply(MessageDto reply) {
		var currentPair = actions.entrySet()
				.stream()
				.filter(
						pair -> reply.getText().contains(pair.getKey()))
				.findFirst();
		if (currentPair.isPresent()) {
			try {
				var replacement = currentPair.get().getValue().run();
				reply.setText(reply.getText().replace(currentPair.get().getKey(), replacement));
				handleReply(reply);
			} catch (Exception e) {
				reply.setText(currentPair.get().getValue().failMessage());
				handleReply(reply);
			}
		}
	}
}
