package ru.nntu.atomteam.chatbot.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nntu.atomteam.chatbot.model.dto.MessageDto;
import ru.nntu.atomteam.chatbot.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("api/message")
public class MessageController {
	private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService service;

	@PostMapping
	public void sendMessage(@RequestBody MessageDto message) {
		LOG.info(String.format("Call sendMessage() with payload: %s", message));
		service.sendMessage(message);
	}

	@GetMapping
	public List<MessageDto> getMessages() {
		return service.getMessages();
	}

	@GetMapping("unread")
	public List<MessageDto> getUnreadMessages() {

		return service.getUnreadMessages();
	}
}
