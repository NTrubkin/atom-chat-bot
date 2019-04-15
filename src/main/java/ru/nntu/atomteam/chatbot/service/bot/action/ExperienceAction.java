package ru.nntu.atomteam.chatbot.service.bot.action;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.nntu.atomteam.chatbot.service.RosatomResourceClient;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class ExperienceAction implements Action {
	private final RosatomResourceClient client;

	public ExperienceAction(RosatomResourceClient client) {
		this.client = client;
	}

	@Override
	public String run(String message) {
		long val = client.getHireDate(SecurityContextHolder.getContext().getAuthentication().getName());
		Date hireDate = new Date(val);
		Date today = new Date();
		long diff = today.getTime() - hireDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + "";
	}

	@Override
	public String failMessage() {
		return "Не удалось вычислить текущий стаж.";
	}
}
