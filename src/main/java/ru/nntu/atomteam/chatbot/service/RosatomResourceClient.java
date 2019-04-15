package ru.nntu.atomteam.chatbot.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nntu.atomteam.chatbot.model.dto.RosatomResourceUserDto;
import ru.nntu.atomteam.chatbot.util.AtomChatBotException;

@Service
public class RosatomResourceClient {

	private String server = "http://localhost:8081";
	private String uri = "/api/rosatom-user/";
	private RestTemplate rest;
	private HttpHeaders headers;

	public RosatomResourceClient() {
		this.rest = new RestTemplate();
		this.headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "*/*");
	}

	public ResponseEntity<RosatomResourceUserDto> get(String login) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<RosatomResourceUserDto> responseEntity = rest.exchange(server + uri + login, HttpMethod.GET, requestEntity, RosatomResourceUserDto.class);
		return responseEntity;
	}

	public long getHireDate(String login) {
		var response = get(login);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AtomChatBotException("Rosatom resource not allowed");
		}
		return response.getBody().getHireDate();
	}

	public float getSalary(String login) {
		var response = get(login);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AtomChatBotException("Rosatom resource not allowed");
		}
		return response.getBody().getSalary();
	}

	public String getPosition(String login) {
		var response = get(login);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AtomChatBotException("Rosatom resource not allowed");
		}
		return response.getBody().getPosition();
	}
}
