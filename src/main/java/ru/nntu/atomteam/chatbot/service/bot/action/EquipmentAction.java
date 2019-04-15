package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.model.BotState;
import ru.nntu.atomteam.chatbot.util.AtomChatBotException;

@Component
public class EquipmentAction implements Action {
	private final ApplicationContext context;

	public EquipmentAction(ApplicationContext context) {
		this.context = context;
	}


	@Override
	public String run(String message) {
		String equip = message.substring(message.indexOf("\"") + 1, message.lastIndexOf("\""));
		if (equip.equals("")) throw new AtomChatBotException("Cannot find equipment type");
		var botState = context.getBean(BotState.class);
		botState.getMemory().put("equip", equip);
		return "";
	}

	@Override
	public String failMessage() {
		return "Не удалось записать тип оборудования. Его нужно указать \"в кавычках\".";
	}
}
