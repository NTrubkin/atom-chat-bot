package ru.nntu.atomteam.chatbot.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nntu.atomteam.chatbot.controller.page.PageController;
import ru.nntu.atomteam.chatbot.model.dto.LearningMessageDto;
import ru.nntu.atomteam.chatbot.model.dto.TagDto;
import ru.nntu.atomteam.chatbot.service.LearningService;

import java.util.Set;

@RestController
@RequestMapping("api/learning")
public class LearningController {
	private static final Logger LOG = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private LearningService service;

	@PostMapping
	public void sendLearningMessage(@RequestBody LearningMessageDto message) {
		LOG.info(String.format("Call sendLearningMessage() with payload: %s", message));
		service.sendMessage(message);
	}

	@GetMapping("command/{command}")
	public Set<TagDto> getTagsOfCommand(@PathVariable("command") String commandCode) {
		LOG.info(String.format("Call getTagsOfCommand(), command = %s", commandCode));
		var result = service.getTagsOfCommand(commandCode);
		LOG.info(String.format("getTagsOfCommand() returns: %s", result));
		return result;
	}

	@PostMapping("recalculate-idf")
	public void recalculateTagIdf() {
		LOG.info("Call recalculateTagIdf()");
		service.recalculateTagIdf();
	}
}
