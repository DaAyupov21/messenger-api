package ru.damirayupov.messenger.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import ru.damirayupov.messenger.api.security.AccountCredentials;
import ru.damirayupov.messenger.api.services.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private String url;
    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(url, authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        var credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        new TokenAuthenticationService().addAuthentication(response, authResult.getName());
    }
}
