package ru.damirayupov.messenger.api.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.damirayupov.messenger.api.constans.ErrorResponse;
import ru.damirayupov.messenger.api.exceptions.ConversationIdInvalidException;

@ControllerAdvice
public class ConversationControllerAdvice {
    ResponseEntity<ErrorResponse> conversationIdInvalidException(ConversationIdInvalidException conversationIdInvalidException){
        var res = new ErrorResponse("", conversationIdInvalidException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }
}
