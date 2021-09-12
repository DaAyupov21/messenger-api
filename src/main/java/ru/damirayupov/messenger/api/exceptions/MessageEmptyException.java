package ru.damirayupov.messenger.api.exceptions;

public class MessageEmptyException extends RuntimeException {
    @Override
    public String getMessage(){
        return "A message cannot be empty";
    }
}
