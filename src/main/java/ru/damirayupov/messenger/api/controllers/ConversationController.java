package ru.damirayupov.messenger.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.damirayupov.messenger.api.dto.ConversationDto;
import ru.damirayupov.messenger.api.models.Conversation;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.UserRepository;
import ru.damirayupov.messenger.api.services.ConversationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    ResponseEntity<List<ConversationDto>> list (HttpServletRequest request) {
        User user = userRepository.findUserByUsername(request.getUserPrincipal().getName())
                .orElseThrow(IllegalArgumentException::new);
        List<Conversation> conversations = conversationService.listUserConversation(user.getId());
        return ResponseEntity.ok(ConversationDto.from(conversations));
    }

    @GetMapping
    @RequestMapping("/{conversation_id}")
    ResponseEntity<ConversationDto> show(@PathVariable(name = "conversation_id") Long conversationId,
                                         HttpServletRequest request){
        User user = userRepository.findUserByUsername(request.getUserPrincipal().getName())
                .orElseThrow(IllegalArgumentException::new);
        var conversationThread = conversationService.retrieveThread(conversationId);
        return ResponseEntity.ok(ConversationDto.from(conversationThread));
    }
}
