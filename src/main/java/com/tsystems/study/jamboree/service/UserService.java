package com.tsystems.study.jamboree.service;

import com.tsystems.study.jamboree.model.User;
import com.tsystems.study.jamboree.repository.UserRepository;
import com.tsystems.study.jamboree.security.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        return new UserDetailsWrapper(user);
    }
}
