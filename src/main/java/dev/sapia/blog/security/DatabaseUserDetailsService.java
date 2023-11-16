package dev.sapia.blog.security;

import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userResult = userRepository.findByEmail(username);
        if (userResult.isPresent()) {
            User user = userResult.get();
            return new DatabaseUserDetails(user);
        } else {
            throw new UsernameNotFoundException("Username with email " + username + " not found");
        }

    }
}
