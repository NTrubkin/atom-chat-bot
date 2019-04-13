package ru.nntu.atomteam.chatbot.model.dto;

public class LearningMessageDto {
	private String commandCode;
	private String text;

	public LearningMessageDto() {
	}

	public LearningMessageDto(String commandCode, String text) {
		this.commandCode = commandCode;
		this.text = text;
	}

	public String getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "LearningMessageDto{" +
				"commandCode='" + commandCode + '\'' +
				", text='" + text + '\'' +
				'}';
	}
}
