package ru.nntu.atomteam.chatbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.nntu.atomteam.chatbot.model.entity.Command;
import ru.nntu.atomteam.chatbot.repository.CommandRepository;
import ru.nntu.atomteam.chatbot.service.bot.action.*;

import java.util.HashMap;
import java.util.Map;

import static ru.nntu.atomteam.chatbot.config.ConstantProvider.ROOT_COMMAND_ID;

@Configuration
public class BeanConfig {
	@Autowired
	private ApplicationContext context;

	@Bean
	public ActionService actionService() {
		Map<String, Action> actions = new HashMap<>();
		actions.put("{terminal}", context.getBean(TerminalAction.class));
		actions.put("{pos}", context.getBean(PositionAction.class));
		actions.put("{sal}", context.getBean(SalaryAction.class));
		actions.put("{hire}", context.getBean(HireAction.class));
		actions.put("{exp}", context.getBean(ExperienceAction.class));
		actions.put("{send}", context.getBean(SendAction.class));
		return new ActionService(actions);
	}

	@Bean
	@Scope("session")
	public Command rootCommand() {
		return context.getBean(CommandRepository.class).getOne(ROOT_COMMAND_ID);
	}
}
