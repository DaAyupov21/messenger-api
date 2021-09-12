package ru.damirayupov.messenger.api.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.damirayupov.messenger.api.exceptions.UserDeactivatedException;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Component
public class AccountValidityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal != null){
            User user = userRepository.findUserByUsername(principal.getName())
                    .orElseThrow(IllegalArgumentException::new);
            if (user.getAccountStatus().equals("deactivated")) {
                throw new UserDeactivatedException("The account of this user has been deactivated");
            }
        }
        return super.preHandle(request, response, handler);
    }
}
