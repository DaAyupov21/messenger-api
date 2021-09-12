package ru.damirayupov.messenger.api.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class TokenAuthenticationService {
    private final long TOKEN_EXPIRY = 864000000;
    private final String SECRET = "$78gr43g7g8feb8we";
    private final String TOKEN_PREFIX = "Bearer";
    private final String AUTHORIZATION_HEADER_KEY = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username){
        var jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY))
                .signWith(SignatureAlgorithm.HS512, SECRET);
        response.addHeader(AUTHORIZATION_HEADER_KEY, "" + TOKEN_PREFIX + " " + jwt);
    }
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if(token != null) {
            String user = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();
            return user != null ? Optional.of(new UsernamePasswordAuthenticationToken(user,
                    null,
                    new ArrayList<GrantedAuthority>())) :
                    Optional.empty();
        }
        return Optional.empty();
    }
}
