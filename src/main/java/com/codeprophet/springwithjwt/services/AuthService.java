package com.codeprophet.springwithjwt.services;

import com.codeprophet.springwithjwt.dtos.SignUpDto;
import com.codeprophet.springwithjwt.entities.User;
import com.codeprophet.springwithjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User signUp(SignUpDto data) throws UsernameNotFoundException {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new UsernameNotFoundException("Login already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(),
                encryptedPassword,
                data.role());
        return userRepository.save(user);
    }
}
