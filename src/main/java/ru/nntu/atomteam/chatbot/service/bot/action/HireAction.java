package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.service.RosatomResourceClient;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HireAction implements Action {
	private final RosatomResourceClient client;

	public HireAction(RosatomResourceClient client) {
		this.client = client;
	}

	@Override
	public String run(String message) {
		long val = client.getHireDate(SecurityContextHolder.getContext().getAuthentication().getName());
		Date date = new Date(val);
		SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
		return df2.format(date);
	}

	@Override
	public String failMessage() {
		return "Не удалось получить дату найма.";
	}
}
