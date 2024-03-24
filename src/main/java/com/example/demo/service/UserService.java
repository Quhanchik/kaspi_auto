package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.UserLoginExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) throws UserLoginExistException {
        Optional<User> userOptional = userRepository.findUserByLogin(user.getLogin());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(userOptional.isEmpty()) {
            userRepository.save(user);
        } else {
            throw new UserLoginExistException();
        }
    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<User> userOptional = userRepository.findUserByLogin(username);
        System.out.println(userOptional);
        if(userOptional.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userOptional.get().getLogin())
                    .password(userOptional.get().getPassword())
                    .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                    .build();
        } else {
            throw new UsernameNotFoundException("user with this login not found");
        }
    }
}
