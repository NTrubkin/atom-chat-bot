package ru.nntu.atomteam.chatbot.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nntu.atomteam.chatbot.model.dto.UserCredentialsDto;
import ru.nntu.atomteam.chatbot.model.dto.UserDetailsDto;
import ru.nntu.atomteam.chatbot.model.entity.User;
import ru.nntu.atomteam.chatbot.repository.UserRepository;
import ru.nntu.atomteam.chatbot.service.mapper.Mapper;

@Service
@Transactional
public class UserService implements UserDetailsService {
	private final UserRepository repository;
	private final Mapper<UserCredentialsDto, User> mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserService(UserRepository repository, Mapper<UserCredentialsDto, User> mapper, BCryptPasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}

	public void register(UserCredentialsDto credentials) {
		var user = new User(credentials.getLogin(),
				passwordEncoder.encode(credentials.getPassword()));
		repository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = repository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User \"%s\" not found", login)));
		return new UserDetailsDto(user.getId(), user.getLogin(), user.getPassword());
	}


	public User getAuthentiticatedUser() {
		UserDetailsDto detailsDto = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repository.findById(detailsDto.getId())
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User \"%s\" not found", detailsDto.getUsername())));
	}

	public User getUser(String login) throws UsernameNotFoundException {
		return repository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User \"%s\" not found", login)));
	}
}
