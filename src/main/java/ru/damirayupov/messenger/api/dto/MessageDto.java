package ru.damirayupov.messenger.api.dto;

import lombok.Builder;
import lombok.Data;
import ru.damirayupov.messenger.api.models.Message;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class MessageDto {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private Long conversationId;
    private String body;
    private String createAt;

    public static MessageDto from (Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .senderId(message.getSender().getId())
                .recipientId(message.getRecipient().getId())
                .conversationId(message.getConversation().getId())
                .body(message.getBody())
                .createAt(message.getCreatedAt().toString())
                .build();
    }

    public static List<MessageDto> from (List<Message> messages){
        return messages.stream().map(MessageDto::from).collect(Collectors.toList());
    }
}
