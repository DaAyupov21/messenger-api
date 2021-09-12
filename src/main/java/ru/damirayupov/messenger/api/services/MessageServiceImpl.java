package ru.damirayupov.messenger.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.damirayupov.messenger.api.exceptions.MessageEmptyException;
import ru.damirayupov.messenger.api.exceptions.MessageRecipientInvalidException;
import ru.damirayupov.messenger.api.models.Conversation;
import ru.damirayupov.messenger.api.models.Message;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.ConversationRepository;
import ru.damirayupov.messenger.api.repositories.MessageRepository;
import ru.damirayupov.messenger.api.repositories.UserRepository;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ConversationService conversationService;


    @Override
    public Message sendMessage(User sender, Long recipientId, String messageText) {
        Optional<User> recipientOptional = userRepository.findById(recipientId);
        if (recipientOptional.isPresent()) {
            User recipient = recipientOptional.get();
            if (!messageText.isEmpty()) {
                Conversation conversation = !conversationService.conversationExists(sender, recipient) ?
                        conversationService.createConversation(sender, recipient) :
                        conversationService.getConversation(sender, recipient).isPresent() ?
                                conversationService.getConversation(sender, recipient).get() : new Conversation();

                conversationRepository.save(conversation);

                Message message = Message.builder()
                        .sender(sender)
                        .recipient(recipient)
                        .body(messageText)
                        .conversation(conversation)
                        .build();
                messageRepository.save(message);
                return message;
            }
        } else {
            throw new MessageRecipientInvalidException();
        }
        throw new MessageEmptyException();
    }
}
