package ru.nntu.atomteam.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nntu.atomteam.chatbot.model.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByRead(boolean read);
}
