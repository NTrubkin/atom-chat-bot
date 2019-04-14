package ru.nntu.atomteam.chatbot.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "command")
public class Command {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String reply;
	@Column
	private String code;
	@Column
	private String description;
	@Column(name = "user_command")
	private boolean userCommand;

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "command_tag",
			joinColumns = { @JoinColumn(name = "command_id") },
			inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	private Set<Tag> tags = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_command_id", referencedColumnName = "id")
	private Command parentCommand;

	public Command() {
	}

	public Command(String reply, String code, String description, boolean userCommand) {
		this.reply = reply;
		this.code = code;
		this.description = description;
		this.userCommand = userCommand;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUserCommand() {
		return userCommand;
	}

	public void setUserCommand(boolean userCommand) {
		this.userCommand = userCommand;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Command getParentCommand() {
		return parentCommand;
	}

	public void setParentCommand(Command parentCommand) {
		this.parentCommand = parentCommand;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Command command = (Command) o;
		return id == command.id &&
				userCommand == command.userCommand &&
				Objects.equals(reply, command.reply) &&
				Objects.equals(code, command.code) &&
				Objects.equals(description, command.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reply, code, description);
	}

	@Override
	public String toString() {
		return "Command{" +
				"id=" + id +
				", reply='" + reply + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				", userCommand=" + userCommand +
				", tags=" + tags +
				'}';
	}
}
