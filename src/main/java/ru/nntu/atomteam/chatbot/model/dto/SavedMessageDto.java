package ru.nntu.atomteam.chatbot.model.dto;

public class SavedMessageDto {
	private String text;
	private long timestamp;
	private boolean bot;

	public SavedMessageDto() {
	}

	public SavedMessageDto(String text, long timestamp, boolean bot) {
		this.text = text;
		this.timestamp = timestamp;
		this.bot = bot;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	@Override
	public String toString() {
		return "SavedMessageDto{" +
				"text='" + text + '\'' +
				", timestamp=" + timestamp +
				", bot=" + bot +
				'}';
	}
}
