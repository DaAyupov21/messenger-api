package ru.damirayupov.messenger.api.filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.damirayupov.messenger.api.services.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional<Authentication> authenticationOptional = new TokenAuthenticationService()
                .getAuthentication((HttpServletRequest) servletRequest);
        authenticationOptional.ifPresent(authentication ->
                SecurityContextHolder.getContext().setAuthentication(authentication));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
