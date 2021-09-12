package ru.damirayupov.messenger.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.damirayupov.messenger.api.exceptions.ConversationIdInvalidException;
import ru.damirayupov.messenger.api.models.Conversation;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.ConversationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation createConversation(User userA, User userB) {
        Conversation conversation = Conversation.builder().
                sender(userA)
                .recipient(userB)
                .build();
        conversationRepository.save(conversation);
        return conversation;
    }

    @Override
    public Boolean conversationExists(User userA, User userB) {
        return conversationRepository.findConversationBySenderIdAndRecipientId(userA.getId(), userB.getId()).isPresent()
                || conversationRepository.findConversationBySenderIdAndRecipientId(userB.getId(), userA.getId()).isPresent();
    }

    @Override
    public Optional<Conversation> getConversation(User userA, User userB) {
        return conversationRepository.findConversationBySenderIdAndRecipientId(userA.getId(), userB.getId()).isPresent() ?
                conversationRepository.findConversationBySenderIdAndRecipientId(userA.getId(), userB.getId()) :
                conversationRepository.findConversationBySenderIdAndRecipientId(userB.getId(), userA.getId()).isPresent() ?
                conversationRepository.findConversationBySenderIdAndRecipientId(userB.getId(), userA.getId()) : Optional.empty();
    }

    @Override
    public Conversation retrieveThread(Long conversationId) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if(optionalConversation.isPresent()){
            return optionalConversation.get();
        }
        else {
            throw new ConversationIdInvalidException();
        }
    }

    @Override
    public List<Conversation> listUserConversation(Long userId) {
        List<Conversation> conversations = new ArrayList<>();
        conversations.addAll(conversationRepository.findConversationBySenderId(userId));
        conversations.addAll(conversationRepository.findConversationByRecipientId(userId));
        return conversations;
    }

    @Override
    public String nameSecondParty(Conversation conversation, Long userId) {
        return conversation.getSender().getId().equals(userId) ?
                conversation.getRecipient().getUsername() :
                conversation.getSender().getUsername();
    }
}
