package ru.damirayupov.messenger.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.damirayupov.messenger.api.models.User;
import ru.damirayupov.messenger.api.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(userOptional.isPresent()){
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return new ArrayList<GrantedAuthority>();
                }

                @Override
                public String getPassword() {
                    return userOptional.get().getPassword();
                }

                @Override
                public String getUsername() {
                    return userOptional.get().getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return false;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return false;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return false;
                }
            };

        }
        else {
            throw new UsernameNotFoundException("A user with the " + username + " doesn't exist");
        }
    }
}
