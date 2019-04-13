package ru.nntu.atomteam.chatbot.util;

public class AtomChatBotException extends RuntimeException {
	public AtomChatBotException(String message) {
		super(message);
	}

	public AtomChatBotException(Throwable cause) {
		super(cause);
	}

	public AtomChatBotException(String message, Throwable cause) {
		super(message, cause);
	}
}
