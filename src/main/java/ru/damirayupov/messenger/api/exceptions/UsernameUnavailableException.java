package ru.damirayupov.messenger.api.exceptions;

public class UsernameUnavailableException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Username Unavailable";
    }
}
