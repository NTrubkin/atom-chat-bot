package ru.nntu.atomteam.chatbot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nntu.atomteam.chatbot.model.dto.UserCredentialsDto;
import ru.nntu.atomteam.chatbot.model.entity.User;
import ru.nntu.atomteam.chatbot.repository.UserRepository;
import ru.nntu.atomteam.chatbot.service.mapper.Mapper;

@Service
@Transactional
public class UserService {
	private final UserRepository repository;
	private final Mapper<UserCredentialsDto, User> mapper;

	public UserService(UserRepository repository, Mapper<UserCredentialsDto, User> mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public void register(UserCredentialsDto credentials) {
		repository.save(mapper.map(credentials));
	}
}
