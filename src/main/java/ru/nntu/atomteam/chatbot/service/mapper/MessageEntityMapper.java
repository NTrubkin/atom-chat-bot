package ru.nntu.atomteam.chatbot.service.mapper;

import org.springframework.stereotype.Service;
import ru.nntu.atomteam.chatbot.model.dto.MessageDto;
import ru.nntu.atomteam.chatbot.model.entity.Message;

@Service
public class MessageEntityMapper implements Mapper<Message, MessageDto> {
	@Override
	public MessageDto map(Message source) {
		return new MessageDto(source.getText());
	}
}
