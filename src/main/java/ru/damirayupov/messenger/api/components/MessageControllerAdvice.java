package ru.damirayupov.messenger.api.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.damirayupov.messenger.api.constans.ErrorResponse;
import ru.damirayupov.messenger.api.constans.ResponseConstants;
import ru.damirayupov.messenger.api.exceptions.MessageEmptyException;
import ru.damirayupov.messenger.api.exceptions.MessageRecipientInvalidException;

@ControllerAdvice
public class MessageControllerAdvice {

    @ExceptionHandler(MessageEmptyException.class)
    ResponseEntity<ErrorResponse> messageEmpty(MessageEmptyException messageEmptyException){
        var res = new ErrorResponse(ResponseConstants.MESSAGE_EMPTY.getValue(), messageEmptyException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }

    @ExceptionHandler(MessageRecipientInvalidException.class)
    ResponseEntity<ErrorResponse> messageRecipientInvalid(MessageRecipientInvalidException messageRecipientInvalidException){
        var res = new ErrorResponse((ResponseConstants.MESSAGE_RECIPIENT_INVALID.getValue()), messageRecipientInvalidException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }
}
