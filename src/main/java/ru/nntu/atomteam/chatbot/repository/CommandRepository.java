package ru.nntu.atomteam.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nntu.atomteam.chatbot.model.entity.Command;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
	Optional<Command> findByCode(String code);

	Set<Command> findAllByParentCommand(Command parentCommand);
}
