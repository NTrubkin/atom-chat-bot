package ru.nntu.atomteam.chatbot.service.mapper;

import org.springframework.stereotype.Service;
import ru.nntu.atomteam.chatbot.model.dto.MessageDto;
import ru.nntu.atomteam.chatbot.model.entity.Message;

@Service
public class MessageDtoMapper implements Mapper<MessageDto, Message> {
	@Override
	public Message map(MessageDto source) {
		return new Message(0,
				source.getText(),
				0,
				false,
				null);
	}
}
