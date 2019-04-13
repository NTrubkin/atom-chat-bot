package ru.nntu.atomteam.chatbot.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "learning_log")
public class LearningLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "command_code")
	private String commandCode;
	@Column
	private String text;
	@Column
	private long date;

	public LearningLog() {
	}

	public LearningLog(String commandCode, String text, long date) {
		this.commandCode = commandCode;
		this.text = text;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
}
