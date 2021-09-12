package ru.damirayupov.messenger.api.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.damirayupov.messenger.api.constans.ErrorResponse;
import ru.damirayupov.messenger.api.constans.ResponseConstants;
import ru.damirayupov.messenger.api.exceptions.UserDeactivatedException;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(UserDeactivatedException.class)
    ResponseEntity<ErrorResponse> userDeactivated(UserDeactivatedException userDeactivatedException){
        var res = new ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.getValue(), userDeactivatedException.getMessage());
        return new ResponseEntity(res, HttpStatus.UNAUTHORIZED);
    }
}
