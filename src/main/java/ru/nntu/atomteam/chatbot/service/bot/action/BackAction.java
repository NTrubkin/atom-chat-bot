package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.model.BotState;

@Component
public class BackAction implements Action {
	private final ApplicationContext context;

	public BackAction(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public String run(String message) {
		var botState = context.getBean(BotState.class);
		botState.setCurrentCommand(botState.getPreviousCommand());
		return "";
	}

	@Override
	public String failMessage() {
		return "Внутренняя ошибка. Тег {back}";
	}
}
