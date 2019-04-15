package ru.nntu.atomteam.chatbot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nntu.atomteam.chatbot.model.dto.MessageDto;
import ru.nntu.atomteam.chatbot.model.entity.Message;
import ru.nntu.atomteam.chatbot.repository.CommandRepository;
import ru.nntu.atomteam.chatbot.repository.MessageRepository;
import ru.nntu.atomteam.chatbot.service.bot.BotService;
import ru.nntu.atomteam.chatbot.service.bot.SimpleBotService;
import ru.nntu.atomteam.chatbot.service.mapper.Mapper;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {
	private final MessageRepository repository;
	private final Mapper<MessageDto, Message> dtoMapper;
	private final Mapper<Message, MessageDto> entityMapper;
	private final CommandRepository commandRepository;
	private final BotService botService;
	private final UserService userService;

	public MessageService(MessageRepository repository,
	                      Mapper<MessageDto, Message> dtoMapper,
	                      Mapper<Message, MessageDto> entityMapper,
	                      CommandRepository commandRepository,
	                      SimpleBotService botService,
	                      UserService userService) {
		this.repository = repository;
		this.dtoMapper = dtoMapper;
		this.entityMapper = entityMapper;
		this.commandRepository = commandRepository;
		this.botService = botService;
		this.userService = userService;
	}

	public void sendMessage(MessageDto messageDto) {
		Message message = dtoMapper.map(messageDto);
		saveMessage(message);
		botService.reply(message);
	}

	public void sendMessageFromBot(MessageDto messageDto) {
		Message message = dtoMapper.map(messageDto);
		saveMessage(message);
	}

	public void sendMessageFromBot(MessageDto messageDto, String to) {
		Message message = dtoMapper.map(messageDto);
		saveMessage(message, to);
	}

	private void saveMessage(Message message) {
		message.setTimestamp(Instant.now().toEpochMilli());
		message.setOwner(userService.getAuthentiticatedUser());
		repository.save(message);
	}

	private void saveMessage(Message message, String to) {
		message.setTimestamp(Instant.now().toEpochMilli());
		message.setOwner(userService.getUser(to));
		repository.save(message);
	}

	// todo: add order by time
	public List<MessageDto> getMessages() {
		return repository
				.findByOwnerOrderByTimestampAsc(userService.getAuthentiticatedUser())
				.stream()
				.peek(message -> message.setRead(true))
				.map(entityMapper::map)
				.collect(Collectors.toList());
	}

	public List<MessageDto> getUnreadMessages() {
		return repository
				.findByReadAndOwnerOrderByTimestampAsc(false, userService.getAuthentiticatedUser())
				.stream()
				.peek(message -> message.setRead(true))
				.map(entityMapper::map)
				.collect(Collectors.toList());
	}
}
