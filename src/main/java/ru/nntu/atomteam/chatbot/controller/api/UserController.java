package ru.nntu.atomteam.chatbot.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nntu.atomteam.chatbot.model.dto.UserCredentialsDto;
import ru.nntu.atomteam.chatbot.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping
	public void registerUser(@RequestBody UserCredentialsDto credentials) {
		service.register(credentials);
	}
}
