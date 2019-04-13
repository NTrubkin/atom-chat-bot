package ru.nntu.atomteam.chatbot.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String text;
	@Column
	private long timestamp;
	@Column
	private boolean read;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User owner;

	public Message() {
	}

	public Message(long id, String text, long timestamp, boolean read, User owner) {
		this.id = id;
		this.text = text;
		this.timestamp = timestamp;
		this.read = read;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
