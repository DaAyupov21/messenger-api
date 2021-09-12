package ru.damirayupov.messenger.api.services;

import ru.damirayupov.messenger.api.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User attemptRegistration(User userDetails);
    List<User> listUsers(User currentUser);
    User retrieveUserData(String username);
    User retrieveUserData(Long id);
    boolean usernameExists(String username);


}
