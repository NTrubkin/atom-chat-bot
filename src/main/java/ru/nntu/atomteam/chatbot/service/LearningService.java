package ru.nntu.atomteam.chatbot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nntu.atomteam.chatbot.model.dto.LearningMessageDto;
import ru.nntu.atomteam.chatbot.model.dto.TagDto;
import ru.nntu.atomteam.chatbot.model.entity.LearningLog;
import ru.nntu.atomteam.chatbot.model.entity.Tag;
import ru.nntu.atomteam.chatbot.repository.CommandRepository;
import ru.nntu.atomteam.chatbot.repository.LearningLogRepository;
import ru.nntu.atomteam.chatbot.repository.TagRepository;
import ru.nntu.atomteam.chatbot.service.bot.YandexStemmerService;
import ru.nntu.atomteam.chatbot.util.AtomChatBotException;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class LearningService {
	private final YandexStemmerService stemmerService;
	private final TagRepository tagRepository;
	private final CommandRepository commandRepository;
	private final LearningLogRepository learningLogRepository;

	public LearningService(YandexStemmerService stemmerService,
	                       TagRepository tagRepository,
	                       CommandRepository commandRepository,
	                       LearningLogRepository learningLogRepository) {
		this.stemmerService = stemmerService;
		this.tagRepository = tagRepository;
		this.commandRepository = commandRepository;
		this.learningLogRepository = learningLogRepository;
	}

	public void sendMessage(LearningMessageDto messageDto) {
		learningLogRepository.save(new LearningLog(messageDto.getCommandCode(), messageDto.getText(), Instant.now().toEpochMilli()));

		var command = commandRepository.findByCode(messageDto.getCommandCode())
				.orElseThrow(() -> new AtomChatBotException(
						String.format("command \"%s\" not found", messageDto.getCommandCode())));
		var oldTags = command.getTags()
				.stream()
				.map(Tag::getName)
				.collect(Collectors.toSet());
		var newTags = stemmerService.normilize(messageDto.getText());
		newTags.stream()
				.filter(o -> !oldTags.contains(o))
				.map(this::findTagOrCreate)
				.forEach(tag -> command.getTags().add(tag));
	}

	private Tag findTagOrCreate(String tagName) {
		var tag = tagRepository.findByName(tagName);
		return tag.orElse(new Tag(tagName, 0));
	}

	public Set<TagDto> getTagsOfCommand(String commandCode) {
		return commandRepository.findByCode(commandCode)
				.orElseThrow(() -> new AtomChatBotException(String.format("command \"%s\" not found", commandCode)))
				.getTags()
				.stream()
				.map(tag -> new TagDto(tag.getName(), tag.getIdfValue()))
				.collect(Collectors.toSet());
	}

	public void recalculateTagIdf() {
		var totalCount = tagRepository.count();
		tagRepository.findAll()
				.forEach(tag -> tag.setIdfValue(calculateIdf(tag.getCommands().size(), totalCount)));
	}

	private float calculateIdf(long docsUseCount, long totalDocsCount) {
		return docsUseCount != 0
				? (float) Math.log(((float) totalDocsCount) / docsUseCount)
				: 0;
	}
}
