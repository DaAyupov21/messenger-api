package ru.damirayupov.messenger.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.damirayupov.messenger.api.dto.UserDto;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.UserRepository;
import ru.damirayupov.messenger.api.services.UserService;

import javax.servlet.http.HttpServletRequest;

import static ru.damirayupov.messenger.api.dto.UserDto.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @RequestMapping("/registrations")
    public ResponseEntity<UserDto> create(@RequestBody User userDetails){
        var user = userService.attemptRegistration(userDetails);
        return ResponseEntity.ok(from(user));
    }

    @GetMapping
    @RequestMapping("/{user_id}")
    ResponseEntity<UserDto> show (@PathVariable("user_id") Long userId){
        User user = userService.retrieveUserData(userId);
        return ResponseEntity.ok(from(user));
    }

    @GetMapping
    @RequestMapping("/details")
    ResponseEntity<UserDto> echoDetails(HttpServletRequest request) {
        User user = userRepository.findUserByUsername(request.getUserPrincipal().getName())
                .orElseThrow(IllegalArgumentException::new);
        return ResponseEntity.ok(from(user));
    }

}
