package ru.nntu.atomteam.chatbot.service.bot.action;

public interface Action {
	String run(String message);

	String failMessage();
}
