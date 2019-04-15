package ru.nntu.atomteam.chatbot.model.dto;

public class RosatomResourceUserDto {
	private long hireDate;
	private float salary;
	private String position;

	public RosatomResourceUserDto() {
	}

	public RosatomResourceUserDto(long hireDate, float salary, String position) {
		this.hireDate = hireDate;
		this.salary = salary;
		this.position = position;
	}

	public long getHireDate() {
		return hireDate;
	}

	public void setHireDate(long hireDate) {
		this.hireDate = hireDate;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
