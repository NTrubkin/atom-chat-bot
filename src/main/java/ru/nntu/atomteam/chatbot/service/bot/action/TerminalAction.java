package ru.nntu.atomteam.chatbot.service.bot.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.model.BotState;

@Component
public class TerminalAction implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(TerminalAction.class);

	private final ApplicationContext context;

	public TerminalAction(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public String run() {
		var botState = context.getBean(BotState.class);
		botState.setCurrentCommand(botState.getRootCommand());
		return "";
	}

	@Override
	public String failMessage() {
		LOG.error("problem with terminal action");
		return "Внутренняя ошибка";
	}
}
