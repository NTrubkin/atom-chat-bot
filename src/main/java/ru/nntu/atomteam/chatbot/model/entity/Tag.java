package ru.nntu.atomteam.chatbot.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column(name = "idf_value")
	private float idfValue;
	@ManyToMany(mappedBy = "tags")
	private Set<Command> commands= new HashSet<>();

	public Tag() {
	}

	public Tag(String name, float idfValue) {
		this.name = name;
		this.idfValue = idfValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getIdfValue() {
		return idfValue;
	}

	public void setIdfValue(float idfValue) {
		this.idfValue = idfValue;
	}

	public Set<Command> getCommands() {
		return commands;
	}

	public void setCommands(Set<Command> commands) {
		this.commands = commands;
	}
}
