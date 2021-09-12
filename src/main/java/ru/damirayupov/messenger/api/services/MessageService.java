package ru.damirayupov.messenger.api.services;

import ru.damirayupov.messenger.api.models.Message;
import ru.damirayupov.messenger.api.models.User;

public interface MessageService {
    Message sendMessage(User sender, Long recipientId, String messageText);
}
