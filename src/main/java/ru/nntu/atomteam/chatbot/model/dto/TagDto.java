package ru.nntu.atomteam.chatbot.model.dto;

public class TagDto {
	private String name;
	private float idfValue;

	public TagDto() {
	}

	public TagDto(String name, float idfValue) {
		this.name = name;
		this.idfValue = idfValue;
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
}
