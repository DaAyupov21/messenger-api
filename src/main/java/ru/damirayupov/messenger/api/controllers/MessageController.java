package ru.damirayupov.messenger.api.controllers;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.damirayupov.messenger.api.dto.MessageDto;
import ru.damirayupov.messenger.api.models.Message;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.UserRepository;
import ru.damirayupov.messenger.api.services.MessageService;

import static ru.damirayupov.messenger.api.dto.MessageDto.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    ResponseEntity<MessageDto> create(@RequestBody MessageRequest messageRequest, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User sender = userRepository.findUserByUsername(principal.getName()).orElseThrow(IllegalArgumentException::new);
        Message message = messageService.sendMessage(sender,
                messageRequest.getRecipientId(),
                messageRequest.getMessage());
        return ResponseEntity.ok(from(message));
    }


   @Data
   @Builder
   private static class MessageRequest {
        private Long recipientId;
        private String message;
   }
}
