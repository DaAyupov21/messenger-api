package ru.damirayupov.messenger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.damirayupov.messenger.api.models.Conversation;
import ru.damirayupov.messenger.api.services.ConversationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.damirayupov.messenger.api.dto.MessageDto.from;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDto {

    @Autowired
    private static ConversationService conversationService;

    private Long conversationId;
    private String recipientUsername;
    private List<MessageDto> messages;

    public static ConversationDto from(Conversation conversation){
        return ConversationDto.builder()
                .conversationId(conversation.getId())
                .recipientUsername(conversation.getRecipient().getUsername())
                .messages(MessageDto.from(conversation.getMessages()))
                .build();
    }

    public static List<ConversationDto> from(List<Conversation> conversations) {
        return conversations.stream().map(ConversationDto::from).collect(Collectors.toList());
    }
}
