package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.model.BotState;

@Component
public class ItAction implements Action {
	private final ApplicationContext context;

	public ItAction(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public String run(String message) {
		var botState = context.getBean(BotState.class);
		botState.getMemory().put("receiver", "it");
		return "";
	}

	@Override
	public String failMessage() {
		return "Внутренняя ошибка. Тэг {it}";
	}
}
