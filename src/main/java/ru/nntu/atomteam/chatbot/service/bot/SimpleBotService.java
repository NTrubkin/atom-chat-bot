package ru.nntu.atomteam.chatbot.service.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.nntu.atomteam.chatbot.model.dto.MessageDto;
import ru.nntu.atomteam.chatbot.model.entity.Command;
import ru.nntu.atomteam.chatbot.model.entity.Message;
import ru.nntu.atomteam.chatbot.model.entity.Tag;
import ru.nntu.atomteam.chatbot.repository.CommandRepository;
import ru.nntu.atomteam.chatbot.repository.TagRepository;
import ru.nntu.atomteam.chatbot.service.MessageService;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SimpleBotService implements BotService {
	private MessageService messageService;
	private final YandexStemmerService stemmerService;
	private final TagRepository tagRepository;
	private final CommandRepository commandRepository;

	public SimpleBotService(YandexStemmerService stemmerService, TagRepository tagRepository, CommandRepository commandRepository) {
		this.stemmerService = stemmerService;
		this.tagRepository = tagRepository;
		this.commandRepository = commandRepository;
	}

	public void reply(Message message) {
		var tags = stemmerService.normilize(message.getText());
		var commandWeights = commandRepository.findAll()
				.stream()
				.collect(Collectors.toMap(command -> command, command -> getIdfSum(tags, command)));

		if (commandWeights.size() == 0) {
			MessageDto reply = new MessageDto("Простите, не могу понять запрос.");
			messageService.sendMessageFromBot(reply);
			return;
		}

		var selectedPair = Collections.max(commandWeights.entrySet(), Map.Entry.comparingByValue());

		if (selectedPair.getValue() == 0) {
			MessageDto reply = new MessageDto("Простите, не могу понять запрос.");
			messageService.sendMessageFromBot(reply);
			return;
		}

		MessageDto reply = new MessageDto(selectedPair.getKey().getReply());
		messageService.sendMessageFromBot(reply);
	}

	@Autowired
	@Lazy
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	private float getIdfSum(Set<String> tags, Command command) {
		return (float) command.getTags()
				.stream()
				.filter(o -> tags.contains(o.getName()))
				.mapToDouble(Tag::getIdfValue)
				.sum();
	}
}
