package ru.nntu.atomteam.chatbot.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PageController {
	private static final Logger LOG = LoggerFactory.getLogger(PageController.class);

	// todo: add logging
	@GetMapping("/")
	public String getChatPage() {
		return "chat";
	}

	@GetMapping("login")
	public String getLoginPage() {
		return "login";
	}

	@GetMapping("registration")
	public String getRegistrationPage() {
		return "registration";
	}

	@GetMapping("learning")
	public String getLearningPage() {
		return "learning";
	}
}
