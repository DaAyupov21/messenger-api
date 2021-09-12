package ru.damirayupov.messenger.api.services;

import ru.damirayupov.messenger.api.models.Conversation;
import ru.damirayupov.messenger.api.models.User;

import java.util.List;
import java.util.Optional;

public interface ConversationService {
    Conversation createConversation(User userA, User userB);
    Boolean conversationExists(User userA, User userB);
    Optional<Conversation> getConversation(User userA, User userB);
    Conversation retrieveThread(Long conversationId);
    List<Conversation> listUserConversation(Long userId);
    String nameSecondParty(Conversation conversation, Long userId);
}
