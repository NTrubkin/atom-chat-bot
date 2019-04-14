package ru.nntu.atomteam.chatbot.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.model.entity.Command;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(SCOPE_SESSION)
public class BotState {
	private Command currentCommand;
	private Command previousCommand;
	private Command rootCommand;
	private Map<String, String> memory = new HashMap<>();

	public BotState(@Qualifier("rootCommand") Command rootCommand) {
		previousCommand = currentCommand = this.rootCommand = rootCommand;
	}

	public Command getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(Command currentCommand) {
		previousCommand = this.currentCommand;
		this.currentCommand = currentCommand;
	}

	public Map<String, String> getMemory() {
		return memory;
	}

	public Command getPreviousCommand() {
		return previousCommand;
	}

	public Command getRootCommand() {
		return rootCommand;
	}
}
