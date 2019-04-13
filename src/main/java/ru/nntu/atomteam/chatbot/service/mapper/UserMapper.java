package ru.nntu.atomteam.chatbot.service.mapper;

import org.springframework.stereotype.Service;
import ru.nntu.atomteam.chatbot.model.dto.UserCredentialsDto;
import ru.nntu.atomteam.chatbot.model.entity.User;

@Service
public class UserMapper implements Mapper<UserCredentialsDto, User>{

	@Override
	public User map(UserCredentialsDto source) {
		return new User(source.getLogin(),
				source.getPassword());
	}
}
