package ru.damirayupov.messenger.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.damirayupov.messenger.api.models.Conversation;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findConversationBySenderId(Long id);
    List<Conversation> findConversationByRecipientId(Long id);
    Optional<Conversation> findConversationBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
