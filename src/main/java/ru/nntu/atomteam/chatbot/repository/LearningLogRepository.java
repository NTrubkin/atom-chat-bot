package ru.nntu.atomteam.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nntu.atomteam.chatbot.model.entity.LearningLog;

public interface LearningLogRepository extends JpaRepository<LearningLog, Long> {
}
