package ru.damirayupov.messenger.api.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.damirayupov.messenger.api.constans.ErrorResponse;
import ru.damirayupov.messenger.api.constans.ResponseConstants;
import ru.damirayupov.messenger.api.exceptions.InvalidUserIdException;
import ru.damirayupov.messenger.api.exceptions.UserStatusEmptyException;
import ru.damirayupov.messenger.api.exceptions.UsernameUnavailableException;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UsernameUnavailableException.class)
    ResponseEntity<ErrorResponse> usernameUnavailable(UsernameUnavailableException usernameUnavailableException){
        var res = new ErrorResponse(ResponseConstants.USERNAME_UNAVAILABLE.getValue(), usernameUnavailableException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    ResponseEntity<ErrorResponse> invalidId(InvalidUserIdException invalidUserIdException) {
        var res = new ErrorResponse(ResponseConstants.INVALID_USER_ID.getValue(), invalidUserIdException.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    ResponseEntity<ErrorResponse> statusEmpty(UserStatusEmptyException userStatusEmptyException) {
        var res = new ErrorResponse(ResponseConstants.EMPTY_STATUS.getValue(), userStatusEmptyException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }

}
