package ru.damirayupov.messenger.api.exceptions;

public class UserDeactivatedException extends RuntimeException{

    private final String message;

    public UserDeactivatedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
