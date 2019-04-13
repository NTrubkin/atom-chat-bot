package ru.nntu.atomteam.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.nntu.atomteam.chatbot.model.entity.Tag;

import java.util.Optional;

@Service
public interface TagRepository extends JpaRepository<Tag, Long> {
	Optional<Tag> findByName(String name);
}
