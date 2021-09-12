package ru.damirayupov.messenger.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.damirayupov.messenger.api.exceptions.InvalidUserIdException;
import ru.damirayupov.messenger.api.exceptions.UserStatusEmptyException;
import ru.damirayupov.messenger.api.exceptions.UsernameUnavailableException;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User attemptRegistration(User userDetails) {
        if(!usernameExists(userDetails.getUsername())){
            User user = User.builder()
                    .username(userDetails.getUsername())
                    .phoneNumber(userDetails.getPhoneNumber())
                    .password(passwordEncoder.encode(userDetails.getPassword()))
                    .createAt(LocalDateTime.now())
                    .status("available")
                    .accountStatus("activated")
                    .build();
            userRepository.save(user);
            return user;
        }
        else {
            throw new UsernameUnavailableException();
        }
    }

    User updateUserStatus(User currentUser, User updateDetails) {
        if(updateDetails.getStatus().isEmpty()){
            currentUser.setStatus(updateDetails.getStatus());
            userRepository.save(currentUser);
            return currentUser;
        }
        else {
            throw new UserStatusEmptyException();
        }
    }

    @Override
    public List<User> listUsers(User currentUser) {
        return Stream.of(userRepository.findAll()).filter(it -> it != currentUser)
                .collect(ArrayList::new, List::addAll, List::addAll);
    }

    @Override
    public User retrieveUserData(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new UsernameUnavailableException();
        }
    }

    @Override
    public User retrieveUserData(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else {
            throw new InvalidUserIdException();
        }
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }
}
