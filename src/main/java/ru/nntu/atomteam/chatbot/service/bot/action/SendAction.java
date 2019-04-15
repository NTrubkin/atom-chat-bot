package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.model.BotState;
import ru.nntu.atomteam.chatbot.model.dto.MessageDto;
import ru.nntu.atomteam.chatbot.service.MessageService;
import ru.nntu.atomteam.chatbot.service.UserService;

@Component
public class SendAction implements Action {
	private MessageService messageService;
	private final ApplicationContext context;
	private final UserService userService;

	public SendAction(ApplicationContext context, UserService userService) {
		this.context = context;
		this.userService = userService;
	}

	@Override
	public String run(String message) {
		var botState = context.getBean(BotState.class);
		messageService.sendMessageFromBot(
				new MessageDto(String.format("Заявка на оборудование \"%s\" от \"%s\".",
						botState.getMemory().get("equip"),
						userService.getAuthentiticatedUser().getLogin())),
				botState.getMemory().get("receiver"));
		return "";
	}

	@Override
	public String failMessage() {
		return "Не удалось отправить сообщение";
	}

	@Autowired
	public void setMessageService(@Lazy MessageService messageService) {
		this.messageService = messageService;
	}
}
