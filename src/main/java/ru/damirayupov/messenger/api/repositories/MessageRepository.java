package ru.damirayupov.messenger.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.damirayupov.messenger.api.models.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageByConversationId(Long conversationId);
}
