package org.example.onlineshop.service.security;

import lombok.RequiredArgsConstructor;
import org.example.onlineshop.entity.user.User;
import org.example.onlineshop.repository.UserRepository;
import org.example.onlineshop.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userService.findByEmail(username);
        if (byEmail.isPresent()) {
            User userFromDb = byEmail.get();
            return new CurrentUser(userFromDb);
        }
        throw new UsernameNotFoundException("User with " + username + " does not exists");
    }
}